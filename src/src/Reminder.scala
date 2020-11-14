
import java.time.LocalDate

case class Reminder(title: String, body: String, priority: Int, date: LocalDate) {

    //update
    def updateTitle(newTitle: String): Reminder = Reminder.updateTitle(this, newTitle)
}

object Reminder {

  def updateTitle(rem: Reminder, newTitle: String): Reminder = {
    Reminder(newTitle, rem.body, rem.priority, rem.date)
  }





}