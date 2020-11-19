import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalTime}

import About.about

import scala.collection.IterableOnce.iterableOnceExtensionMethods

object Main {
  def main(args: Array[String]): Unit = {

    //TESTES SOBRE A PARTE DO HORÁRIO!!!

    val bloco1 = SBlock(LocalDate.parse("12-11-2020", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
      LocalTime.of(9,30,0), LocalTime.of(10,30,0), "Aula TP de MC", "MC")
    val bloco2 = SBlock(LocalDate.parse("17-11-2020", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
    LocalTime.of(9,30,0), LocalTime.of(10,30,0), "Aula TP de MC", "CDSI")
    val bloco3 = SBlock(LocalDate.parse("19-11-2020", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
    LocalTime.of(9,30,0), LocalTime.of(10,30,0), "Aula TP de MC", "MC")

    val horario = Schedule(Nil)

    val horario1 = horario.addSBlock(bloco1)
    val horario2 = horario1.addSBlock(bloco2)
    val horario3 = horario2.addSBlock(bloco3)

    println(horario)
    println(horario1)
    println(horario2)
    println(horario3.timebyCUnitL7Days("MC"))
  }
}
