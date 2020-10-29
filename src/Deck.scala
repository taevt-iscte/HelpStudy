import Deck.Card

case class Deck(cards: List[Card]){
  def addCard(card: Card): List[(String, String)] = Deck.addCard(this, card)
}

object Deck {

  type Card = (String, String)

  def addCard(deck: Deck, card: Card): List[Card] = card :: deck.cards

}
