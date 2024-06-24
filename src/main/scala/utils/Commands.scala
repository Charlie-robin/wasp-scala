package utils

import scala.annotation.tailrec
import scala.io.StdIn.readLine

sealed trait InputReader {
  def getInput:String
}

class Commands(message : String, inputPredicate : (String) => Boolean) extends InputReader {
  @tailrec
  final def getInput: String = {
    println(message)
    val userInput = readLine().trim.toLowerCase()
    if (inputPredicate(userInput)) userInput
    else {
      println("Incorrect Input try again...")
      getInput
    }
  }
}

