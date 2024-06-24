package state


class Context {

  var currentState: Option[State] = None

  def changeState(state: State): Unit = {
    state match
      case s: Play => currentState = Option(s)
      case _ => Nil

    state.run()
  }

  def changeState(): Unit = {
    currentState match
      case state: Some[State] => state.foreach(_.run())
      case _ => new Context().changeState(new Start())
  }

}
