package wasp

enum WaspType(val hitPoints:Int, val damage: Int) {
  case WORKER extends WaspType(68, 10)
  case DRONE extends WaspType( 60, 12)
  case QUEEN extends WaspType(80, 7)
}
