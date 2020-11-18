import Notebook.Note

import scala.io.Source
import java.io._
import java.nio.file.Paths

case class Notebook (notes: List[Note]){
  def addNote(note: Note): Notebook = Notebook.addNote(this, note)

  def getNotesbyCUnit(cunit: String): List[Note] = Notebook.getNotesbyCUnit(this, cunit)

  def importFromFile(file: String, cunit: String): Notebook = Notebook.importFromFile(this, file, cunit)

  def exportToFile(index: Int): Unit = Notebook.exportToFile(this, index)
}

object Notebook {

  type Note = (String, String, String)

  def addNote(nbook: Notebook, note: Note): Notebook = Notebook(note :: nbook.notes)

  def getNotesbyCUnit(nbook: Notebook, cunit: String): List[Note] = nbook.notes.filter(_._3 == cunit)

  def importFromFile(nb: Notebook, file: String, cunit: String): Notebook = {
    val body = Source.fromFile(file).getLines.mkString
    val newNote = (file.stripSuffix(".txt"), body, cunit)
    Notebook.addNote(nb, newNote)
  }

  def exportToFile(nb: Notebook, index: Int): Unit = {
    val note = nb.notes(index)
    val file = Paths.get(System.getProperty("user.dir"), note._3, note._1+".txt")
    val pw = new PrintWriter(file.toFile)
    pw.write(note._2)
    pw.close()
  }
}
