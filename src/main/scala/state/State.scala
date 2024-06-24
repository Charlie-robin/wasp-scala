package state

import utils.Commands
import wasp.{Nest, WaspNest}

sealed trait State {
  def run(): Unit
}

abstract class StateWithContext(protected val context: Context) extends State

class GameEnd extends State {
  override def run(): Unit = println("Good Bye")
}

class Winner extends State {
  override def run(): Unit = println("Good Going")
}

class Quit(context: Context) extends StateWithContext(context) {
  def message: String = "Are you sure you want to quit? y/n"

  def inputValidator: String => Boolean = s => s == "y" || s == "n"

  val commands = new Commands(message, inputValidator)

  override def run(): Unit = {
    commands.getInput match
      case "n" => context.changeState(new Play(context))
      case _ => context.changeState(new GameEnd())
  }
}

class Play(nest: Nest, context: Context) extends StateWithContext(context) {
  def message: String = "Enter fire or f to shoot"

  def inputValidator: String => Boolean = s => s == "fire" || s == "f" || s == "quit"

  val commands = new Commands(message, inputValidator)

  override def run(): Unit = {
    nest.print()
    commands.getInput match
      case "fire" | "f" => {
        val nextNest = nest.fire
        if (nextNest.isDefeated) context.changeState(new Play(nextNest, context))
        else context.changeState(new Winner())
      }
      case _ => context.changeState(new Quit(context))
  }

  def this(context: Context) = this(WaspNest(), context)
}


