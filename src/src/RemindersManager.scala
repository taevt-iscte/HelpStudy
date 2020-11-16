import java.time.LocalDate

import RemindersManager.{Reminder, Reminder_List}

case class RemindersManager(val lst_rem: Reminder_List) {

  def addReminder(new_rem: Reminder): Reminder_List = RemindersManager.addReminder(this, new_rem)

  def delReminder(title: String): Reminder_List = RemindersManager.delReminder(this, title)

  def searchReminder(title: String): Option[Reminder] = RemindersManager.searchReminder(this, title)

  def sort_by_priority(): Reminder_List = RemindersManager.sort_by_priority(this)

  def sort_by_date(): Reminder_List = RemindersManager.sort_by_date(this)
}

object RemindersManager {
  type Title = String
  type Body = String
  type Priority = Int
  type Date = LocalDate
  type Reminder = (Title, Body, Priority, Date)
  type Reminder_List = List[Reminder]


  //-------------UPDATE------------------------
  def addReminder(rem_man: RemindersManager, new_rem: Reminder): Reminder_List = {
    new_rem :: rem_man.lst_rem
  }

  def delReminder(rem_man: RemindersManager, title: String): Reminder_List = {
    rem_man.lst_rem.filter( r => !r._1.equals(title))
  }


  //------------AUXILIAR------------------------------
  def searchReminder(rem_man: RemindersManager, title: String): Option[Reminder] = {
    Option((rem_man.lst_rem filter ( r => r._1.equals(title))) (0))
  }

  //-------------SORTING------------------------------
  def sort_by_priority(rem_man: RemindersManager): Reminder_List = {
    rem_man.lst_rem.sortBy(_._3)
  }

  def sort_by_date(rem_man: RemindersManager): Reminder_List = {
    rem_man.lst_rem.sortBy(_._4)
  }

  def sort_default(rem_man: RemindersManager): Reminder_List = {
    rem_man.lst_rem.sortBy( r => (r._4, r._3))
  }

}