import java.nio.file.Paths

import About.about

import scala.annotation.tailrec
import scala.io.StdIn.readLine
import scala.sys.exit

object Main {

  def showPrompt(): Unit = {
    print("\nBEM-VINDO À APLICAÇÃO HELP STUDY!")
    print("\nESCOLHA UMA DAS SEGUINTES OPÇÕES:")
    print("\nABOUT, DECK, NOTEBOOK, REMINDERS, SCHEDULE, SUBJECTS OU QUIT")
  }

  def getUserInput: String = {
    readLine.trim.toUpperCase
  }

  @tailrec
  def mainLoop(): Unit = {
    print("\nINPUT: ")
    val input = getUserInput
    input match {
      case "ABOUT" => print(about())
      case "DECK" => //deckLoop()
      case "NOTEBOOK" => //notebookLoop()
      case "REMINDERS" => //remindersLoop()
      case "SCHEDULE" => //scheduleLoop()
      case "SUBJECTS" => //subjectsLoop()
      case "QUIT" => exit(0)
      case _ =>
    }
    mainLoop()
  }

  def remindersLoop(): Unit = {
    print("\nESCOLHA UMA DAS SEGUINTES OPÇÕES:")
    print("\nADD, DELETE, SEARCH, SORT BY PRIORITY, SORT BY DATE OU BACK")
    print("\nINPUT: ")
    val input = getUserInput
    input match {



      case "BACK" => mainLoop()
      case _ =>
    }
  }

  //Load hs.state file
  def loadState(): Unit = {

  }

  //Initialize all the needed variables for the program and generate the hs.state file
  def initHS(): Unit = {

  }

  def main(args: Array[String]): Unit = {
    showPrompt()
    mainLoop()
    if (Paths.get(System.getProperty("user.dir", "hs.state")).toFile.exists())
      loadState()
    else
      initHS()
  }
}
