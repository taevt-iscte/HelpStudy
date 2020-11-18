import java.time.LocalDate

import Notebook.Note
import RemindersManager.Reminder


//val rems_default = List()

case class Subject(name: String, rems: List[Reminder] = List(), notes: List[Note] = List()) {

  /*def this(m: Map[String, String]) =
    this(m.getOrElse("rems", rems_default))*/


  def associate_reminder(rem: Reminder): Subject = Subject.associate_reminder(this, rem)

  def associate_note(note: Note): Subject = Subject.associate_note(this, note)


}

object Subject {

  def associate_reminder(subj: Subject, rem: Reminder): Subject = {
    Subject(subj.name, rem :: subj.rems)
  }

  def associate_note(subj: Subject, note: Note): Subject = {
    Subject(subj.name, subj.rems, note :: subj.notes)
  }


  def main(args: Array[String]): Unit = {

    val subj = Subject("PPM")
    println(subj.associate_reminder(("Titulo1", "Body1", 3, LocalDate.now(), 1, 0.0)))


  }


}
