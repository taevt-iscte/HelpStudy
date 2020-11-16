import java.time.{LocalDate, LocalTime}

case class Schedule(sblocks: List[SBlock]) {

  def addSBlock(sblock: SBlock): (List[SBlock], Schedule) = Schedule.addSBlock(this, sblock)

  def timebyCUnit(cunit: String): Long = Schedule.timebyCunit(this, cunit)

}

object Schedule {

  def addSBlock(schedule: Schedule, sblock: SBlock): (List[SBlock], Schedule) =
    (sblock :: schedule.sblocks, Schedule(sblock :: schedule.sblocks))

  def timebyCunit(schedule: Schedule, cunit: String): Long = {
    def aux (l: List[SBlock], c: String): Long = l match {
      case Nil => 0
      case head :: Nil => if(head.cunit == c) head.duration() else 0
      case head :: tail => if(head.cunit == c) head.duration() + aux(tail, c) else aux(tail,c)
    }
    aux(schedule.sblocks, cunit)
  }
}