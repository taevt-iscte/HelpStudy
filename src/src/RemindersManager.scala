
case class RemindersManager(lst_rem: List[Reminder]) {

  def sort_by_priority(): List[Reminder] = RemindersManager.sort_by_priority(this)

  def sort_by_date(): List[Reminder] = RemindersManager.sort_by_date(this)
}

object RemindersManager {


  def sort_by_priority(rem_man: RemindersManager): List[Reminder] = {
    rem_man.lst_rem.sortBy(_.priority)
  }

  def sort_by_date(rem_man: RemindersManager): List[Reminder] = {
    rem_man.lst_rem.sortBy(_.date)
  }

}