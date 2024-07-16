package ca.sheridancollege.project;

// @author: Baoyong zhao

import java.util.List;

public class WarPlayer extends Player {
    private List<WarCard> groupOfCards;
    private Record record;

    public WarPlayer(String name, List<WarCard> deck) {
        super(name);
        this.groupOfCards = deck;
        this.record = new Record();
    }
    
        public Record getRecord() {
        return record;
    }

     /* @param record the player record to set
     */
    public void setRecord(Record record) {
        this.record = record;
    }

    public List<WarCard> getGroupOfCards() {
        return groupOfCards;
    }

    public void setDeck(List<WarCard> deck) {
        this.groupOfCards = deck;
    }

    public boolean hasCards() {
        return !groupOfCards.isEmpty();
    }

    public WarCard drawCard() {
        return groupOfCards.remove(0);
    }

    public void collectCard(WarCard card) {
        groupOfCards.add(card);
    }

    public void collectCards(List<WarCard> cards) {
        groupOfCards.addAll(cards);
    }

    public void clear() {
        groupOfCards.clear();
    }

    public int getCardCount() {
        return groupOfCards.size();
    }
    
    @Override
    public void play(){
    
    };
}
