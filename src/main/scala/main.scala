import state.{Context, Play}


@main
def main(): Unit = {
  val context = new Context()
  context.changeState(new Play(context))
}