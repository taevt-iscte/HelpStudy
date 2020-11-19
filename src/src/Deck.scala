import Deck.Card

import scala.annotation.tailrec
import scala.io.StdIn.readLine
import java.time.LocalDate

case class Deck(cards: List[Card]){
  def addCard(card: Card): (List[Card], Deck) = Deck.addCard(this, card)

  def ask(course: String): Unit = Deck.ask(this, course)

  def printCards(cards: List[Card])(opt: String): Unit = Deck.printCards(cards)(opt)
}

object Deck {

  type Card = (String, String, Int, String, LocalDate)

  def addCard(deck: Deck, card: Card): (List[Card], Deck) = (card :: deck.cards, Deck(card::deck.cards))

  def ask(deck: Deck, course: String): Deck = {
    val card = deck.cards.filter(_._4 == course)(Math.random().toInt % deck.cards.size)
    print(card._1)
    val answer = readLine.trim
    val index = deck.cards.indexOf(card)
    if (answer.compareToIgnoreCase(card._2) == 0) {
      println("Correct")
      val newDate = LocalDate.now().plusDays(deck.cards(index)._3 * 2)
      if (deck.cards(index)._3 >= 5)
        Deck(deck.cards.updated(index, (card._1, card._2, card._3+1, card._4, LocalDate.MAX)))
      else
        Deck(deck.cards.updated(index, (card._1, card._2, card._3+1, card._4, newDate)))
    }
    else {
      println("Wrong")
      Deck(deck.cards.updated(index, (card._1, card._2, 1, card._4, LocalDate.now())))
    }
  }

  def printCards(cards: List[Card])(opt: String = "") : Unit = {
    @tailrec
    def aux(card_list: List[Card]): Unit = cards match {
    case Nil => Nil
    case card :: tail => println(s"Q: ${card._1} A: ${card._2} Level: ${card._3} Next Due Date: ${card._5}")
        aux(tail)
  }
    if (opt == "")
      aux(cards)
    else aux(cards.filter(card => card._4 == opt))
  }

}
