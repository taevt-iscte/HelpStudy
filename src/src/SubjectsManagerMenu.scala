
import Main.mainLoop

import scala.io.StdIn.readLine

object SubjectsManagerMenu {

  def showPrompt(): Unit = {
    print("\nESCOLHA UMA DAS SEGUINTES OPÇÕES:")
    print("\nADD, SHOW, OPEN OU BACK")
    print("\nINPUT: ")
  }

  def getUserInput: String = {
    readLine.trim.toUpperCase
  }

  def getUserInput_Original: String = {
    readLine.trim
  }



  def mainLoop_Subjects(subj_man: SubjectsManager): Unit = {
    showPrompt()
    val input = getUserInput
    input match {
      case "ADD" => addSubject_menu(subj_man)
      case "SHOW" => showSubjects_menu(subj_man)
      case "OPEN" => openSubject_menu(subj_man)
      case "BACK" => mainLoop()
      case _ =>
    }
    mainLoop_Subjects(subj_man)
  }


  def addSubject_menu(subj_man: SubjectsManager): Unit = {

  }

  def showSubjects_menu(subj_man: SubjectsManager): Unit = {

  }

  def openSubject_menu(subj_man: SubjectsManager): Unit = {

  }
}
