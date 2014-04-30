/**
 * Created by Keyan on 4/27/14.
 *
 * This class is the backbone of the War card game GUI. It allows for a new WarGame object to be made.
 *
 * Aces are high!
 *
 *
 */
import java.util.ArrayList;
import javax.swing.*;

public class WarGame implements WarGameInterface {

    private QueueReferenceBased computerHand = new QueueReferenceBased();
    private QueueReferenceBased playerHand = new QueueReferenceBased();

    //This array list will contain all the face up/face down cards involved in a war. At the end of a war (or double war etc)
    //all the elements will be added to the victors hand.
    private ArrayList<Card> warCardsPot= new ArrayList<Card>();


    /**
     * Constructor for starting a new game. Makes a new deck, shuffles it, deals one half to each player.
     *
     */
    public WarGame(){
        Deck newDeck = new Deck();
        newDeck.shuffle();

        dealHand(newDeck);
    }

    /**
     * Loops 26 times (one half of a 52 card deck) queueing one card at a time to each player's hand.
     * The cards are already shuffled.
     *
     * @param inputDeck, the deck that is instantiated in the WarGame object constructor.
     */

    public void dealHand(Deck inputDeck){
        for (int x = 0; x < 26; x++){

            playerHand.enqueue(inputDeck.dealCard());
            computerHand.enqueue(inputDeck.dealCard());
        }
    }

    /**
     * Draws one card from each players hand, then compares the two. Only the rank is considered. Whoever wins gets both cards
     * added to their hand (bottom of the queue). If it is a war then no cards are added to either hand, instead the main() method of the
     * game program should call the war() method.
     *
     * @return  V if player beats the computer (victory)
     *          L if player loses to computer
     *          W if there is a match (WAR!)
     *
     */
    public char battle(Card playerCard, Card computerCard){
        if (playerCard.getRank() > computerCard.getRank()){
            playerHand.enqueue(playerCard);
            playerHand.enqueue(computerCard);

            //Add any cards from the war pot into the computers hand. The variable loopsize saves the original size of
            //the arraylist which holds the cards to be given to the winning player. The size of the array goes down when
            //elements are removed, so it is not an accurate way to loop.
            int loopSize = warCardsPot.size();
            for (int x = 0; x < loopSize; x++){
                System.out.println("WAR CARD ADDED " + x);
                playerHand.enqueue(warCardsPot.remove(0));
            }

            System.out.println("Your card: " + playerCard.toString());
            System.out.println("Computer's card: " + computerCard.toString());
            System.out.println("Win!");

            return 'V';
        }

        else if (playerCard.getRank() < computerCard.getRank()){
            computerHand.enqueue(playerCard);
            computerHand.enqueue(computerCard);

            //Add any cards from the war pot into the computers hand. The variable loopsize saves the original size of
            //the arraylist which holds the cards to be given to the winning player. The size of the array goes down when
            //elements are removed, so it is not an accurate way to loop.
            int loopSize = warCardsPot.size();
            for (int x = 0; x < loopSize; x++){
                System.out.println("WAR CARD ADDED " + x);
                computerHand.enqueue(warCardsPot.remove(0));
            }

            System.out.println("Your card: " + playerCard.toString());
            System.out.println("Computer's card: " + computerCard.toString());
            System.out.println("Lose.");

            return 'L';
        }

        else{
            System.out.println("Your card: " + playerCard.toString());
            System.out.println("Computer's card: " + computerCard.toString());
            System.out.println("War!");
            war(playerCard, computerCard);
        }

        //Required because method returns an int, this value will never actually be returned.
        return 0;
    }

    /**
     * The highlight of this boring pseudogame! Two cards are dequeued from each hand (player and computer) but only the
     * second card is shown. The second card is used to determine the winner of the war by calling the battle() method.
     *
     * If the two War cards are again the same rank, then there is a recursive call to the war() method again.
     *
     * @param playerCard Original player card which started the war.
     * @param computerCard Original computer card which started the war.
     *
     */
    public void war(Card playerCard, Card computerCard){
        //These two cards are not revealed, they are simply added to the hand of whoever wins the war.
        Card faceDownPlayerCard = new Card(playerDraw());
        Card faceDownComputerCard = new Card(computerDraw());

        warCardsPot.add(playerCard);
        warCardsPot.add(computerCard);
        warCardsPot.add(faceDownPlayerCard);
        warCardsPot.add(faceDownComputerCard);

        //These two cards are revealed and are used to determine the winner of the war.
        Card warPlayerCard = new Card(playerDraw());
        Card warComputerCard = new Card(computerDraw());

        //V,L as above.
        char warResults = battle(warPlayerCard, warComputerCard);

        if (warResults == 'V'){
            System.out.println("Victory is yours! You have won the war!");
        }

        if (warResults == 'L'){
            System.out.println("You have been defeated in the war.");
        }
    }

    /**
     * Returns the first card in the players hand. Because the Card objects are in a queue, they must be cast as Card objects,
     * otherwise they will be dequeued as superclass "Object" objects.
     *
     * If there are no cards remaining the the player's hand (queue) then a call to the gameOver() method is made.
     *
     * @return The top card of the player's hand.
     */
    public Card playerDraw(){
        if (playerHand.isEmpty()){
            gameOver("Player");
        }
        return (Card)playerHand.dequeue();
    }

    /**
     * Returns the first card in the computers hand. Because the Card objects are in a queue, they must be cast as Card objects,
     * otherwise they will be dequeued as superclass "Object" objects.
     *
     * If there are no cards remaining the the computer's hand (queue) then a call to the gameOver() method is made.
     *
     * @return The top card of the computer's hand.
     */
    public Card computerDraw(){
        if (computerHand.isEmpty()){
            gameOver("Computer");
        }
        return (Card) computerHand.dequeue();
    }

    /**
     * Getter so that the main() method can get access to the remaining cards in each persons (or computers) hand.
     *
     * @return number of cards left in the player's hand.
     */
    public Integer getPlayersCardsRemaining() {
        return playerHand.getSize();
    }

    /**
     * Getter so that the main() method can get access to the remaining cards in each persons (or computers) hand.
     *
     * @return number of cards left in the computer's hand.
     */
    public Integer getComputersCardsRemaining() {
        return computerHand.getSize();
    }

    /**
     *
     * This method prints a string telling the player the outcome of the game, it is called internally if either the player
     * or the computer runs out of cards in their hand.
     *
     * @param s is a string which holds the name of the player who has run out of cards.
     *
     */
    public void gameOver(String s){
        if (s.equals("Player")){
            System.out.println("Sorry, you have run out of cards! Game over, you lose!");
        }
        else if (s.equals("Computer")){
            System.out.println("The computer has run out of cards! Hooray, you win!");
        }
        System.exit(0);
    }
}
