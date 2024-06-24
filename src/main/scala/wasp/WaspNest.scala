package wasp


import utils.{Printer, StringUtils}

import scala.util.Random

object WaspNest {
  def apply(): Nest = {
    val workers = Wasp.getWasps(WaspType.WORKER, 8)
    val drones = Wasp.getWasps(WaspType.DRONE, 5)
    val queen = Wasp.getWasps(WaspType.QUEEN, 1)
    new WaspNest(workers ++ drones ++ queen)
  }
}

trait Nest {
  def isDefeated: Boolean

  def fire: Nest

  def print(): Unit
}

class WaspNest(nest: Map[String, Wasp]) extends Nest {

  val toHit: Seq[(String, Wasp)] = nest.filter(_._2.isAlive).toSeq

  override def isDefeated: Boolean = {
    nest.get(Wasp.QUEEN_ID) match
      case queen: Some[Wasp] => queen.get.isAlive
      case _ => false
  }

  override def fire: Nest = {
    val randomIndex = Random.nextInt(toHit.length)
    val (key, wasp) = toHit(randomIndex)
    Printer.printWasp(wasp)
    val newWasp = (key, wasp.hit)
    new WaspNest(nest + newWasp)
  }

  override def print(): Unit = Printer.printNest(nest)

}