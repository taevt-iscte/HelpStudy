import Deck.Card

import scala.annotation.tailrec
import scala.io.StdIn.readLine

case class Deck(cards: List[Card]){
  def addCard(card: Card): (List[Card], Deck) = Deck.addCard(this, card)

  def ask(course: String): Unit = Deck.ask(this, course)

  def printCards(cards: List[Card]): Unit = Deck.printCards(cards)
}

object Deck {

  type Card = (String, String, Int, String)

  def addCard(deck: Deck, card: Card): (List[Card], Deck) = (card :: deck.cards, Deck(card::deck.cards))

  def ask(deck: Deck, course: String): Deck = {
    val card = deck.cards.filter(_._4 == course)(Math.random().toInt % deck.cards.size)
    print(card._1)
    val answer = readLine.trim
    if (answer.compareToIgnoreCase(card._2) == 0) { println("Correct")
     val index = deck.cards.indexOf(card)
     Deck(deck.cards.updated(index, (card._1, card._2, card._3+1, card._4)))} else {println("Wrong"); deck}
  }

  @tailrec
  def printCards(cards: List[Card]) : Unit = cards match {
    case Nil => Nil
    case card :: tail => print("Q: " + card._1 + " A: " + card._2); printCards(tail)
  }

}
