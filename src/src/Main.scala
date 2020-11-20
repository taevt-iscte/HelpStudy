import java.nio.file.Paths
import java.time.{LocalDate, LocalTime}

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
      case "REMINDERS" => remindersLoop()
      case "SCHEDULE" => scheduleLoop(Schedule(Nil, 50))
      case "SUBJECTS" => //subjectsLoop()
      case "QUIT" => exit(0)
      case _ =>
    }
    mainLoop()
  }

  def scheduleLoop(schedule: Schedule): Unit = {
    print("\nESCOLHA UMA DAS SEGUINTES OPÇÕES:")
    print("\nADD_BLOCK, BLOCKS_BY_DAY, TIME_CUNIT_L7DAYS, TIME_CUNIT_L30DAYS, " +
      "TIME_SPENT_TODAY, FATIGUE_ALERT OU BACK")
    print("\nINPUT: ")
    val input = getUserInput
    input match {
      case "ADD_BLOCK" => {
        print("\nINSIRA A DATA (EX:. 2020-11-20): ")
        val date = LocalDate.parse(getUserInput)
        print("\nINSIRA A HORA DE INÍCIO (EX:. 10:30): ")
        val start_time = LocalTime.parse(getUserInput)
        print("\nINSIRA A HORA DE FIM (EX:. 11:30): ")
        val end_time = LocalTime.parse(getUserInput)
        print("\nINSIRA O TÍTULO: ")
        val title = getUserInput
        print("\nINSIRA A UNIDADE CURRICULAR: ")
        val cunit = getUserInput
        scheduleLoop(schedule.addSBlock(SBlock(date,start_time,end_time,title,cunit)))
      }
      case "BLOCKS_BY_DAY" => {
        print("\nINSIRA A DATA (EX:. 2020-11-20): ")
        val date = LocalDate.parse(getUserInput)
        schedule.blocksByDay(date)
      }
      case "TIME_CUNIT_L7DAYS" => {
        print("\nINSIRA A UNIDADE CURRICULAR: ")
        val cunit = getUserInput
        print("\nNOS ÚLTIMOS 7 DIAS INVESTIU "+schedule.timebyCUnitL7Days(cunit)+" MINUTOS")
      }
      case "TIME_CUNIT_L30DAYS" => {
        print("\nINSIRA A UNIDADE CURRICULAR: ")
        val cunit = getUserInput
        print("\nNOS ÚLTIMOS 30 DIAS INVESTIU "+schedule.timebyCUnitL30Days(cunit)+" MINUTOS")
      }
      case "TIME_SPENT_TODAY" => {
        print("\nHOJE VAI INVESTIR "+schedule.timeSpentBySchoolToday()+" MINUTOS DO SEU DIA")
      }
      case "FATIGUE_ALERT" => {
        print("\n"+schedule.fatigueAlert())
      }
      case "BACK" => mainLoop()
      case "QUIT" => exit(0)
      case _ =>
    }
    scheduleLoop(schedule)
  }

  def remindersLoop(): Unit = {
    /*print("\nESCOLHA UMA DAS SEGUINTES OPÇÕES:")
    print("\nADD, DELETE, SEARCH, SORT BY PRIORITY, SORT BY DATE OU BACK")
    print("\nINPUT: ")
    val input = getUserInput
    input match {



      case "BACK" => mainLoop()
      case _ =>
    }*/
    val rems: RemindersManager = RemindersManager(List(("Titulo1", "Body1", 3, LocalDate.now(), 0.0),
      ("Titulo2", "Body2", 1,LocalDate.parse("2020-11-20") , 0.0), ("Titulo3", "Body3", 1, LocalDate.parse("2020-11-23"), 0.0),
      ("Titulo4", "Body4", 4, LocalDate.parse("2020-11-30"), 0.0), ("Titulo5", "Body5", 4, LocalDate.parse("2020-11-24"), 0.0)))
    RemindersMenu.mainLoop_Reminders(rems)
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
