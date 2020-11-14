
import java.time.LocalDate
import java.time.format.DateTimeFormatter

case class Reminder(title: String, body: String, priority: Int, date: LocalDate) {

    //getters
    def getPriority(): Int = Reminder.getPriority(this)

    //update
    def updateTitle(newTitle: String) = Reminder.updateTitle(this, newTitle)
}

object Reminder {

  def getPriority(rem: Reminder): Int = rem.priority

  def updateTitle(rem: Reminder, newTitle: String): Unit = {
    val rem.title = newTitle
  }





}