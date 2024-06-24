package state




class Context {

    def changeState(state: State): Unit = {
      state.run()
    }

}
