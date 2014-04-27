/**
 * Created by Keyan on 4/27/14.
 *
 * This class is the backbone of the War card game GUI. It allows for a new WarGame object to be made.
 *
 * Aces are high!
 *
 */
public class WarGame implements WarGameInterface {

    private QueueReferenceBased computerHand = new QueueReferenceBased();
    private QueueReferenceBased playerHand = new QueueReferenceBased();

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
     * The cards are already shuffled. Private so that the hands cannot be reset outside of this class.
     *
     * @param inputDeck, the deck that is instantiated in the WarGame object constructor.
     */

    public void dealHand(Deck inputDeck){
        for (int x = 1; x < 27; x++){
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
            return 'V';
        }

        else if (playerCard.getRank() < computerCard.getRank()){
            computerHand.enqueue(playerCard);
            computerHand.enqueue(computerCard);
            return 'L';
        }

        else{
            return 'W'; //WAR!!!
        }
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
     * @return V if player wins the war
     *         L if player loses the war
     *         If there is another war, an internal call to war() will be made, only V or L will be returned.
     */
    public char war(Card playerCard, Card computerCard){
        //These two cards are not revealed, they are simply added to the hand of whoever wins the war.
        Card faceDownPlayerCard = new Card(playerDraw());
        Card faceDownComputerCard = new Card(computerDraw());

        //These two cards are revealed and are used to determine the winner of the war.
        Card WarPlayerCard = new Card(playerDraw());
        Card WarComputerCard = new Card(computerDraw());

        //V,L, or W as above.
        char warResults = battle(WarPlayerCard, WarComputerCard);

        //If the cards match again, then call the war() method recursively.
        if (warResults == 'W'){
            war(WarPlayerCard, WarComputerCard);
        }

        return warResults;
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
     *
     * @param s
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
