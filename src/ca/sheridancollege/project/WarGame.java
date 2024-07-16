package ca.sheridancollege.project;

// @author: Baoyong zhao
import java.util.ArrayList;
import java.util.Scanner;

public class WarGame extends Game {

    private WarPlayer player1;
    private WarPlayer player2;
    private Scanner scanner;
    private int counter = 0;

    public WarGame(String name) {
        super(name);
        this.scanner = new Scanner(System.in);
        initializePlayers();
        resetGame();
    }

    // initializePlayers, set name and get cards.
    private void initializePlayers() {
        System.out.println("Please input player 1 name: ");
        String player1Name = scanner.nextLine();
        System.out.println("Please input player 2 name: ");
        String player2Name = scanner.nextLine();

        Deck deck = new Deck();
        player1 = new WarPlayer(player1Name, deck.getHalfDeck());
        player2 = new WarPlayer(player2Name, deck.getRemainingDeck());

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        super.setPlayers(players);
    }

    // give both players equal cards again.
    private void resetGame() {
        Deck deck = new Deck();
        player1.setDeck(deck.getHalfDeck());
        player2.setDeck(deck.getRemainingDeck());
    }

    @Override
    public void play() {
        while (player1.hasCards() && player2.hasCards() && counter < 50000) {
            playRound();
        }
    }

    @Override
    public void declareWinner() {
        if (counter == 50000) {
            System.out.println(player1.getName() + " ties with: " + player2.getName() + " with " + counter + " rounds.");
            player1.getRecord().setNumMatch(player1.getRecord().getNumMatch() + 1);
            player2.getRecord().setNumMatch(player2.getRecord().getNumMatch() + 1);
            player1.getRecord().setNumTie(player1.getRecord().getNumTie()+1);
            player2.getRecord().setNumTie(player2.getRecord().getNumTie()+1);
        } else if (player1.hasCards()) {
            System.out.println(player1.getName() + " wins the game with: " + counter + " rounds.");
            player1.getRecord().setNumMatch(player1.getRecord().getNumMatch() + 1);
            player2.getRecord().setNumMatch(player2.getRecord().getNumMatch() + 1);
            player1.getRecord().setNumWin(player1.getRecord().getNumWin() + 1);
            player2.getRecord().setNumLost(player2.getRecord().getNumLost() + 1);
        } else {
            System.out.println(player2.getName() + " wins the game with: " + counter + " rounds.");
            player1.getRecord().setNumMatch(player1.getRecord().getNumMatch() + 1);
            player2.getRecord().setNumMatch(player2.getRecord().getNumMatch() + 1);
            player2.getRecord().setNumWin(player2.getRecord().getNumWin() + 1);
            player1.getRecord().setNumLost(player1.getRecord().getNumLost() + 1);
        }
        player1.clear();
        player2.clear();
        counter = 0;
    }

    private void playRound() {
        WarCard card1 = player1.drawCard();
        WarCard card2 = player2.drawCard();

        System.out.println(player1.getName() + " plays: " + card1);
        System.out.println(player2.getName() + " plays: " + card2);

        int comparison = card1.getRank().getValue() - card2.getRank().getValue();
        if (comparison > 0) {
            player1.collectCard(card1);
            player1.collectCard(card2);
            counter++;
            System.out.println(player1.getName() + " wins the round: " + counter);
        } else if (comparison < 0) {
            player2.collectCard(card1);
            player2.collectCard(card2);
            counter++;
            System.out.println(player2.getName() + " wins the round: " + counter);
        } else {
            System.out.println("It's a tie! War!!!");
            ArrayList<WarCard> warPile = new ArrayList<>();
            warPile.add(card1);
            warPile.add(card2);
            boolean player1Wins = resolveWar(warPile);
            if (player1Wins) {
                player1.collectCards(warPile);
            } else {
                player2.collectCards(warPile);
            }
        }
        System.out.println(player1.getName() + " has: " + player1.getCardCount() + " cards in hand.\t" + player2.getName() + " has: " + player2.getCardCount() + " cards in hand.\n");
    }

    private boolean resolveWar(ArrayList<WarCard> warPile) {

        // when war, both players play 4 more cards. three cards face down, and the fourth face up.
        if (player1.getCardCount() < 4 || player2.getCardCount() < 4) {
            System.out.println("One player does not have enough cards for war. The other player wins.");
            return player1.getCardCount() > player2.getCardCount();
        }

        for (int i = 0; i < 4; i++) {
            warPile.add(player1.drawCard());
            warPile.add(player2.drawCard());
        }

        WarCard warCard1 = warPile.get(warPile.size() - 2);
        WarCard warCard2 = warPile.get(warPile.size() - 1);

        System.out.println("War cards:");
        System.out.println(player1.getName() + ": " + warCard1);
        System.out.println(player2.getName() + ": " + warCard2);

        int comparison = warCard1.getRank().getValue() - warCard2.getRank().getValue();
        if (comparison > 0) {
            System.out.println(player1.getName() + " wins the war.");
            return true;
        } else if (comparison < 0) {
            System.out.println(player2.getName() + " wins the war.");
            return false;
        } else {
            System.out.println("War again!");
            return resolveWar(warPile);
        }
    }

    public static void main(String[] args) {
        WarGame warGame = new WarGame("War");
        System.out.println("\nWar Card Game\n");
        String continuePlaying;
        Scanner scanner = new Scanner(System.in);

        do {
            warGame.play();
            warGame.declareWinner();
            System.out.println("Do you want to continue playing? (yes/no)");
            continuePlaying = scanner.nextLine();
            if (continuePlaying.equalsIgnoreCase("yes") || continuePlaying.equalsIgnoreCase("y")) {
                warGame.resetGame();
            }
        } while (continuePlaying.equalsIgnoreCase("yes") || continuePlaying.equalsIgnoreCase("y"));

        scanner.close();

        // Display records
        System.out.println("\n---Record List---\n");
        System.out.println(warGame.player1.getName());
        System.out.println("Number of matches: " + warGame.player1.getRecord().getNumMatch());
        System.out.println("Number of wins: " + warGame.player1.getRecord().getNumWin());
        System.out.println("Number of ties: " + warGame.player1.getRecord().getNumTie());
        System.out.println("Number of losses: " + warGame.player1.getRecord().getNumLost());
        System.out.println();
        System.out.println(warGame.player2.getName());
        System.out.println("Number of matches: " + warGame.player2.getRecord().getNumMatch());
        System.out.println("Number of wins: " + warGame.player2.getRecord().getNumWin());
        System.out.println("Number of ties: " + warGame.player2.getRecord().getNumTie());
        System.out.println("Number of losses: " + warGame.player2.getRecord().getNumLost());
    }
}
