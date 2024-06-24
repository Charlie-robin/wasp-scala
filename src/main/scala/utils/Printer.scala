package utils

import wasp.{Wasp, Queen, Drone, Worker}

import scala.collection.immutable.Map

object Printer {

  def printTitle(): Unit = {
    println(" __      __                              ")
    println("/  \\    /  \\_____    ____________  ______")
    println("\\   \\/\\/   /\\__  \\  /  ___/\\____ \\/  ___/")
    println(" \\        /  / __ \\_\\___ \\ |  |_> >___ \\ ")
    println("  \\__/\\  /  (____  /____  >|   __/____  >")
    println("       \\/        \\/     \\/ |__|       \\/ ")
  }

  def printWasp(wasp: Wasp): Unit = println(s"You hit a : ${wasp.getClass.getSimpleName}")

  def printNest(nest: Map[String, Wasp]): Unit = {
    val (q, w, d): (String, String, String) = nest.values.foldLeft(("", "", ""))((acc, wasp) => wasp match {
      case q: Queen => (acc(0) + q, acc(1), acc(2))
      case w: Worker => (acc(0), acc(1) + w, acc(2))
      case d: Drone => (acc(0), acc(1), acc(2) + d)
    })

    val maxLength = Set(q.length, w.length, d.length).max

    println("\n".repeat(2))
    println(StringUtils.centre(q, maxLength))
    println()
    println(StringUtils.centre(d, maxLength))
    println()
    println(StringUtils.centre(w, maxLength))
    println("\n".repeat(2))
  }
}
