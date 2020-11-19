import java.time.LocalDate

import scala.::
import scala.annotation.tailrec
import scala.collection.IterableOnce.iterableOnceExtensionMethods
import scala.collection.View.Empty.foldRight

case class Schedule(sblocks: List[SBlock]) {

  //ADDS ONE BLOCK OF TIME TO THE SCHEDULE

  def addSBlock(sblock: SBlock): Schedule = Schedule.addSBlock(this, sblock)

  //GIVES THE LIST OF BLOCKS OF ONE SPECIFIC DAY

  def blocksByDay(date: LocalDate): List[SBlock] = Schedule.blocksByDay(this, date)

  //GIVES THE TIME SPENT ON A SPECIFIC CUNIT ON THE LAST 7 DAYS

  def timebyCUnitL7Days(cunit: String): Long = Schedule.timebyCUnitL7Days(this, cunit)

  //GIVES THE TIME SPENT ON A SPECIFIC CUNIT ON THE LAST 30 DAYS

  def timebyCUnitL30Days(cunit: String): Long = Schedule.timebyCUnitL7Days(this, cunit)

  //GIVES THE SUM OF TIME SPENT ON A SPECIFIC CUNIT

  def sumTime(cunit: String, f: (Schedule, String) => List[SBlock]): Long =
    Schedule.sumTime(this, cunit, f)

  //GIVES THE LIST OF BLOCKS OF TIME OF A SPECIFIC CUNIT ON THE LAST 7 DAYS

  def last7Days(cunit: String): List[SBlock] = Schedule.last7Days(this, cunit)

  //GIVES THE LIST OF BLOCKS OF TIME OF A SPECIFIC CUNIT ON THE LAST 30 DAYS

  def last30Days(cunit: String): List[SBlock] = Schedule.last30Days(this, cunit)



  //TALVEZ JÁ NÃO SEJA PRECISO!!! CONFIRMAR!!!

  //def timebyCUnit(cunit: String): Long = Schedule.timebyCUnit(this, cunit)

  //def isProblematic(sblock: SBlock): Boolean = Schedule.isProblematic(this, sblock)

}

object Schedule {

  //ADDS ONE BLOCK OF TIME TO THE SCHEDULE

  def addSBlock(schedule: Schedule, sblock: SBlock): Schedule = {
    @tailrec
    def aux(l: List[SBlock], s: SBlock, stock: List[SBlock]): Schedule = l match {
      case Nil => Schedule(sblock :: stock)
      case head :: Nil => if(head.isOverlay(s)) {
        println("Não pode inserir este bloco no horário porque irá existir uma sobreposição!")
        Schedule(l)
      } else Schedule(s::stock)
      case head :: tail => if(head.isOverlay(s)) {
        println("Não pode inserir este bloco no horário porque irá existir uma sobreposição!")
        Schedule(l)
      } else aux(tail, s, stock)
    }
    aux(schedule.sblocks, sblock, schedule.sblocks)
  }

  //GIVES THE LIST OF BLOCKS OF ONE SPECIFIC DAY

  def blocksByDay(schedule: Schedule, date: LocalDate): List[SBlock] =
    schedule.sblocks.filter(x => x.date == date)

  //GIVES THE TIME SPENT ON A SPECIFIC CUNIT ON THE LAST 7 DAYS

  def timebyCUnitL7Days(schedule: Schedule, cunit: String): Long = sumTime(schedule, cunit, last7Days)

  //GIVES THE TIME SPENT ON A SPECIFIC CUNIT ON THE LAST 30 DAYS

  def timebyCUnitL30Days(schedule: Schedule, cunit: String): Long = sumTime(schedule, cunit, last30Days)

  //GIVES THE SUM OF TIME SPENT ON A SPECIFIC CUNIT

  def sumTime(schedule: Schedule, cunit: String, f: (Schedule, String) => List[SBlock]): Long =
    schedule.sblocks match {
      case Nil => 0
      case _ => (f(schedule,cunit).map(x => x.duration()) foldRight 0.longValue()) (_ + _)
    }

  //GIVES THE LIST OF BLOCKS OF TIME OF A SPECIFIC CUNIT ON THE LAST 7 DAYS

  def last7Days(schedule: Schedule, cunit: String): List[SBlock] =
    schedule.sblocks.filter(x => (x.cunit == cunit) &&
      (x.date.isEqual(LocalDate.now().minusDays(7)) ||
        (x.date.isAfter(LocalDate.now().minusDays(7)) &&
          x.date.isBefore(LocalDate.now()))))

  //GIVES THE LIST OF BLOCKS OF TIME OF A SPECIFIC CUNIT ON THE LAST 30 DAYS

  def last30Days(schedule: Schedule, cunit: String): List[SBlock] =
    schedule.sblocks.filter(x => x.cunit == cunit &&
      (x.date.isEqual(LocalDate.now().minusDays(30)) ||
        (x.date.isAfter(LocalDate.now().minusDays(30)) &&
          x.date.isBefore(LocalDate.now()))))



  //TALVEZ JÁ NÃO SEJA PRECISO!!! CONFIRMAR!!!

  /*def timebyCUnit(schedule: Schedule, cunit: String): Long = {
    @tailrec
    def aux (l: List[SBlock], c: String, acc: Long): Long = l match {
      case Nil => 0
      case head :: Nil => if(head.cunit == c) head.duration() else 0
      case head :: tail => if(head.cunit == c) aux(tail, c, acc + head.duration()) else aux(tail, c, acc)
    }
    aux(schedule.sblocks, cunit, 0)
  }*/



  //TALVEZ JÁ NÃO SEJA PRECISO!!! CONFIRMAR!!!

  /*  def isProblematic(schedule: Schedule, sblock: SBlock): Boolean = {
      @tailrec
      def aux (l: List[SBlock], s: SBlock): Boolean = l match {
        case Nil => false
        case head :: Nil => if(head.isOverlay(s)) true else false
        case head :: tail => if(head.isOverlay(s)) true else aux(tail, s)
      }
      aux(schedule.sblocks, sblock)
    }*/
}