import java.time.LocalDate

import About.about
import RemindersManager.sort_default

object Main {
  def main(args: Array[String]): Unit = {
    //println(about()) ----FUNCIONA
    val rems: RemindersManager = RemindersManager(List(("Titulo1", "Body1", 3, LocalDate.now()),
      ("Titulo2", "Body2", 1, LocalDate.now()), ("Titulo3", "Body3", 2, LocalDate.now())))
    //println(rems.sort_by_priority())
    //println(rems.addReminder(("Titulo4", "Body4", 4, LocalDate.now())))
    //println(rems.delReminder("Titulo2"))
    //println(sort_default(rems))
    //println(rems.searchReminder("Titulo7"))
  }
}
