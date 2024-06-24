package wasp

enum WaspType(val hitPoints:Int, val damage: Int) {
  case WORKER extends WaspType(5, 10)
  case DRONE extends WaspType( 5, 12)
  case QUEEN extends WaspType(80, 7)
}
