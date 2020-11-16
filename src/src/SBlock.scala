import java.time.temporal.ChronoUnit
import java.time.{LocalDate, LocalTime}

case class SBlock(date: LocalDate, start_time: LocalTime, end_time: LocalTime, title: String, cunit: String) {

  def duration(): Long = SBlock.duration(this)

}

object SBlock {

  def duration(sblock: SBlock): Long = sblock.start_time.until(sblock.end_time, ChronoUnit.MINUTES)

}
