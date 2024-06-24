package wasp

object Wasp {

  val QUEEN_ID: String = WaspType.QUEEN.toString + "1"

  def apply(waspType: WaspType): Wasp = waspType match {
    case WaspType.WORKER => new Worker()
    case WaspType.DRONE => new Drone()
    case WaspType.QUEEN => new Queen()
  }

  def getWasps(waspType: WaspType, quantity: Int = 1): Map[String, Wasp] = {
    waspType match {
      case WaspType.QUEEN => Map(QUEEN_ID -> Wasp(WaspType.QUEEN))
      case _ => (for (i <- 1 to quantity) yield (s"$waspType-$i", Wasp(waspType))).toMap
    }
  }

}

abstract class Wasp(protected val hitPoints: Int, protected val damage: Int) {
  def isAlive: Boolean = hitPoints > 0

  protected def nextHitPoints: Int = Math.max(0, hitPoints - damage)

  def hit: Wasp

  protected def getHitPoints: String = hitPoints match
    case hp if hitPoints <= 10 => Console.RED + f"$hp%02d" + Console.RESET
    case hp if hitPoints <= 30 => Console.YELLOW + f"$hp%02d" + Console.RESET
    case hp => f"$hp%02d"

  override def toString: String = s"[${getClass.getSimpleName}:${getHitPoints}]"
}

class Worker(hitPoints: Int, damage: Int) extends Wasp(hitPoints, damage) {
  override def hit: Wasp = new Worker(nextHitPoints, damage)

  override def this() = this(WaspType.WORKER.hitPoints, WaspType.WORKER.damage)
}

class Drone(hitPoints: Int, damage: Int) extends Wasp(hitPoints, damage) {
  override def hit: Wasp = new Drone(nextHitPoints, damage)

  override def this() = this(WaspType.DRONE.hitPoints, WaspType.DRONE.damage)
}

class Queen(hitPoints: Int, damage: Int) extends Wasp(hitPoints, damage) {
  override def hit: Wasp = new Queen(nextHitPoints, damage)

  override def this() = this(WaspType.QUEEN.hitPoints, WaspType.QUEEN.damage)
}
