import Deck.Card

import scala.annotation.tailrec

case class Deck(cards: List[Card]){
  def addCard(card: Card): (List[Card], Deck) = Deck.addCard(this, card)

  def printCards(cards: List[Card]): Unit = Deck.printCards(cards)
}

object Deck {

  type Card = (String, String, Int)

  def addCard(deck: Deck, card: Card): (List[Card], Deck) = (card :: deck.cards, Deck(card::deck.cards))

  def answer(card: Card, reply: String): Boolean = card._1 == reply

  @tailrec
  def printCards(cards: List[Card]) : Unit = cards match {
    case Nil => Nil
    case card :: tail => print("Q: " + card._1 + " A: " + card._2); printCards(tail)
  }

}
