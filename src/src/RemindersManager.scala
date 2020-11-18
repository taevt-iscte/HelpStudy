import java.time.{LocalDate, LocalDateTime, LocalTime, Period}
import java.time.temporal.ChronoUnit
import scala.math._

import RemindersManager.{Reminder, Reminder_List}

import scala.annotation.tailrec

case class RemindersManager(val lst_rem: Reminder_List) {

  def addReminder(new_rem: Reminder): RemindersManager = RemindersManager.addReminder(this, new_rem)

  def delReminder(title: String): RemindersManager = RemindersManager.delReminder(this, title)

  def searchReminder(title: String): Option[Reminder] = RemindersManager.searchReminder(this, title)

  def sort_by_priority(): RemindersManager = RemindersManager.sort_by_priority(this)

  def sort_by_date(): RemindersManager = RemindersManager.sort_by_date(this)
}

object RemindersManager {
  type Title = String
  type Body = String
  type Priority = Int
  type Date = LocalDate
  type ID = Int
  type Points = Double
  type Reminder = (Title, Body, Priority, Date, ID, Points)
  type Reminder_List = List[Reminder]



  //PRINT DOS LEMBRETES
  @tailrec
  def printReminders(lst: List[Reminder]) : Unit =
    lst match {
    case Nil => Nil
    case head :: tail => println(s"Title: ${head._1} Body: ${head._2} Priority: ${head._3} Date: ${head._4}"); printReminders(tail)
  }

  //-------------UPDATE------------------------
  def addReminder(rem_man: RemindersManager, new_rem: Reminder): RemindersManager = {
    if(new_rem._3 < 1 || new_rem._3 > 4) {
      throw new IllegalArgumentException("Erro: A priordidade deve estar entre 1 e 4!")
    }
    else {
      RemindersManager(new_rem :: rem_man.lst_rem)
    }
  }

  def delReminder(rem_man: RemindersManager, title: String): RemindersManager = {
    RemindersManager(rem_man.lst_rem.filter( r => !r._1.equals(title)))
  }


  //------------AUXILIAR------------------------------
  def searchReminder(rem_man: RemindersManager, title: String): Option[Reminder] = {
    Option((rem_man.lst_rem filter ( r => r._1.equals(title))) (0))
  }

  //-------------SORTING------------------------------
  def sort_by_priority(rem_man: RemindersManager): RemindersManager = {
    RemindersManager(rem_man.lst_rem.sortBy(_._3).reverse)
  }

  def sort_by_date(rem_man: RemindersManager): RemindersManager = {
    RemindersManager(rem_man.lst_rem.sortBy(_._4))
  }

  def sort_smart(rem_man: RemindersManager): RemindersManager = {
    //Algoritmo de complexidade
    //RemindersManager((rem_man.lst_rem.sortBy( r => (r._4, r._3))))
    RemindersManager(smart_list(rem_man.lst_rem).sortBy(_._6).reverse)
  }

  def smart_list(rems: List[Reminder]): List[Reminder] = rems match {
      case Nil => Nil
      case head :: tail => (head._1, head._2, head._3, head._4, head._5, points(head)) :: smart_list(tail)
    }

  def points(rem: Reminder): Double = {
    val today = LocalDate.now()
    val days = Period.between(today, rem._4).getDays()
    val prior_points = rem._3
    if(days < 1)
      prior_points match {
        case 1 => 1*0.25
        case 2 => 1*0.50
        case 3 => 1*0.75
        case 4 => 1*1
      }
    else if(days < 3)
      prior_points match {
        case 1 => 0.75*0.25
        case 2 => 0.75*0.50
        case 3 => 0.75*0.75
        case 4 => 0.75*1
      }

    else if(days < 5)
      prior_points match {
        case 1 => 0.5*0.25
        case 2 => 0.5*0.50
        case 3 => 0.5*0.75
        case 4 => 0.5*1
      }

    else if(days < 7){
      prior_points match {
        case 1 => 0.25*0.25
        case 2 => 0.25*0.50
        case 3 => 0.25*0.75
        case 4 => 0.25*1
      }
    }

    else {
      prior_points match {
        case 1 => 0.10*0.25
        case 2 => 0.10*0.50
        case 3 => 0.10*0.75
        case 4 => 0.10*1
      }
    }
  }

  /*def points_parabolic(rem: Reminder): Double = {
    val today = LocalDate.now()
    val days = Period.between(today, rem._4).getDays()
    val prior_points = rem._3.toFloat

    prior_points =
  }*/

  def main(args: Array[String]): Unit = {
    val rems: RemindersManager = RemindersManager(List(("Titulo1", "Body1", 3, LocalDate.now(), 1, 0.0),
      ("Titulo2", "Body2", 1,LocalDate.parse("2020-11-19") , 2, 0.0), ("Titulo3", "Body3", 2, LocalDate.parse("2020-11-19"), 3, 0.0),
      ("Titulo4", "Body4", 4, LocalDate.parse("2020-11-20"), 4, 0.0), ("Titulo5", "Body5", 4, LocalDate.parse("2020-11-25"), 5, 0.0)))
    //println(rems.sort_by_priority())
    //println(rems.sort_by_date())
    //println(rems.addReminder(("Titulo4", "Body4", 4, LocalDate.now())))
    //println(rems.delReminder("Titulo20"))
    //println(rems.searchReminder("Titulo7"))
    //printReminders(rems.lst_rem)
    //println(points(rems.lst_rem.last))
    //println(sort_smart(rems))
  }

}

