package state

import utils.{Commands, Printer}
import wasp.{Nest, WaspNest}

// TODO: MOVE INTO SEPARATE FILES

sealed trait State {
  def run(): Unit
}

abstract class StateWithContext(protected val context: Context) extends State

class Start(context: Context) extends StateWithContext(context) {
  Printer.printTitle()

  def message: String = "Press enter to start"

  def inputPredicate: String => Boolean = s => s.isEmpty

  val commands = new Commands(message, inputPredicate)

  override def run(): Unit = {
    commands.getInput
    context.changeState(new Play(context))
  }

  override def this() = this(new Context)
}

class Play(nest: Nest, context: Context, auto: Boolean = false) extends StateWithContext(context) {
  def message: String = "Enter fire or f to shoot"

  def inputPredicate: String => Boolean = s => Set("auto", "a", "q", "quit", "f", "fire").contains(s)

  val commands = new Commands(message, inputPredicate)

  override def run(): Unit = {
    Thread.sleep(1000)
    nest.print()
    val userInput = if (auto) "f" else commands.getInput
    // TODO: REPETITIVE
    userInput match {
      case "auto" | "a" => {
        val nextNest = nest.fire
        if (nextNest.isDefeated) context.changeState(new Play(nextNest, context, true))
        else context.changeState(new Winner())
      }
      case "fire" | "f" => {
        val nextNest = nest.fire
        if (nextNest.isDefeated) context.changeState(new Play(nextNest, context, auto))
        else context.changeState(new Winner())
      }
      case _ => context.changeState(new Quit(context))
    }
  }

  def this(context: Context) = this(WaspNest(), context)
}

class Quit(context: Context) extends StateWithContext(context) {
  def message: String = "Are you sure you want to quit? y/n"

  def inputPredicate: String => Boolean = s => s == "y" || s == "n"

  val commands = new Commands(message, inputPredicate)

  override def run(): Unit = {
    commands.getInput match
      case "n" => context.changeState()
      case _ => context.changeState(new GameEnd())
  }
}

class GameEnd extends State {
  override def run(): Unit = {
    println("Shutting down, Good Bye...")
  }
}

class Winner extends State {
  override def run(): Unit = {
    println("Good Going you won")
  }
}

