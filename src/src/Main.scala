import About.about

import scala.annotation.tailrec
import scala.io.StdIn.readLine
import scala.sys.exit

object Main {

  def getUserInput: String = {
    readLine.trim.toUpperCase
  }

  /*@tailrec
  def mainLoop(): Unit = {
    val input = getUserInput()
    input match {
    case "ABOUT" => print (about () );
    case "ASK" =>
    case "QUIT" => exit(0)
    case _ =>
  }
    mainLoop()
  }*/

  def main(args: Array[String]): Unit = {
//    mainLoop()
    //TESTING

    val nb = Notebook(List())
    val newnb = nb.importFromFile("Testing.txt", "Cunit")
    newnb.printNotes()
    val secondNote = ("Title2", "RandomText", "PPM")
    val thirdnb = newnb.addNote(secondNote)
    thirdnb.exportToFile(0)
  }
}
