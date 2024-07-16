package ca.sheridancollege.project;
// @author: Baoyong zhao


import java.util.ArrayList;
import java.util.List;

public class Deck extends GroupOfCards {

    public Deck() {
        super(52);
        ArrayList<Card> cards = getCards();
        
        for (WarCard.Suit suit : WarCard.Suit.values()) {
            for (WarCard.Rank rank : WarCard.Rank.values()) {
                cards.add(new WarCard(suit, rank));
            }
        }

        shuffle();
    }

    public List<WarCard> getHalfDeck() {
        ArrayList<Card> cards = getCards();
        int halfSize = cards.size() / 2;
        List<WarCard> halfDeck = new ArrayList<>();
        for (int i = 0; i < halfSize; i++) {
            halfDeck.add((WarCard) cards.get(i));
        }
        return halfDeck;
    }

    public List<WarCard> getRemainingDeck() {
        ArrayList<Card> cards = getCards();
        int halfSize = cards.size() / 2;
        List<WarCard> remainingDeck = new ArrayList<>();
        for (int i = halfSize; i < cards.size(); i++) {
            remainingDeck.add((WarCard) cards.get(i));
        }
        return remainingDeck;
    }
}
