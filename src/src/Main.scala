import java.nio.file.Paths

import About.about

import scala.annotation.tailrec
import scala.io.StdIn.readLine
import scala.sys.exit

object Main {

  def getUserInput: String = {
    readLine.trim.toUpperCase
  }

  @tailrec
  def mainLoop(): Unit = {
    val input = getUserInput
    input match {
    case "ABOUT" => print (about () );
    case "ASK" =>
    case "QUIT" => exit(0)
    case _ =>
  }
    mainLoop()
  }

  def loadState(): Unit = {

  }

  def initHS(): Unit = {

  }

  def main(args: Array[String]): Unit = {
    if (Paths.get(System.getProperty("user.dir", "hs.state")).toFile.exists())
      loadState()
    else
      initHS()
  }
}
