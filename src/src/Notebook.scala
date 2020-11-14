import Notebook.Note

case class Notebook (notes: List[Note]){
  def addNote(note: Note): (List[Note], Notebook) = Notebook.addNote(this, note)

  def getNotesbyCUnit(cunit: String): List[Note] = Notebook.getNotesbyCUnit(this, cunit)
}

object Notebook {

  type Note = (String, String, String)

  def addNote(nbook: Notebook, note: Note): (List[Note], Notebook) = (note :: nbook.notes, Notebook(note :: nbook.notes))

  def getNotesbyCUnit(nbook: Notebook, cunit: String): List[Note] = nbook.notes.filter(_._3 == cunit)
}
