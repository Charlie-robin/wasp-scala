package wasp


import utils.StringUtils

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
    val newWasp = (key, wasp.hit)
    new WaspNest(nest + newWasp)
  }

  override def print(): Unit = {
    val (q, w, d): (String, String, String) = nest.values.foldLeft(("", "", ""))((acc, wasp) => wasp match {
      case q: Queen => (acc(0) + q, acc(1), acc(2))
      case w: Worker => (acc(0), acc(1) + w, acc(2))
      case d: Drone => (acc(0), acc(1), acc(2) + d)
    })

    val maxLength = Set(q.length, w.length, d.length).max

    println(StringUtils.centre(q, maxLength))
    println(StringUtils.centre(d, maxLength))
    println(StringUtils.centre(w, maxLength))
  }

}