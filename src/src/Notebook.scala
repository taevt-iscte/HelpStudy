case class Notebook (notes: List[Note]){
  def addNote(note: Note): List[Note] = Notebook.addNote(this, note)

  def getNotesbyCUnit(cunit: String): List[Note] = Notebook.getNotesbyCUnit(this, cunit)
}

object Notebook {
  def addNote(nbook: Notebook, note: Note): List[Note] = note :: nbook.notes

  def getNotesbyCUnit(nbook: Notebook, cunit: String): List[Note] = nbook.filter(_.cunit == cunit)
}
