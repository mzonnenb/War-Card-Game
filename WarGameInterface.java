/**
 * Created by Keyan on 4/27/14.
 *
 * Interface for the WarGame class.
 *
 */
public interface WarGameInterface {

    /**
     * Loops 26 times (one half of a 52 card deck) queueing one card at a time to each player's hand.
     * The cards are already shuffled.
     *
     * @param inputDeck, the deck that is instantiated in the WarGame object constructor.
     */
    public void dealHand(Deck inputDeck);

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
    public char battle(Card playerCard, Card computerCard);

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
    public void war(Card playerCard, Card computerCard);

    /**
     * Returns the first card in the players hand. Because the Card objects are in a queue, they must be cast as Card objects,
     * otherwise they will be dequeued as superclass "Object" objects.
     *
     * If there are no cards remaining the the player's hand (queue) then a call to the gameOver() method is made.
     *
     * @return The top card of the player's hand.
     */
    public Card playerDraw();

    /**
     * Returns the first card in the computers hand. Because the Card objects are in a queue, they must be cast as Card objects,
     * otherwise they will be dequeued as superclass "Object" objects.
     *
     * If there are no cards remaining the the computer's hand (queue) then a call to the gameOver() method is made.
     *
     * @return The top card of the computer's hand.
     */
    public Card computerDraw();

    /**
     * Getter so that the main() method can get access to the remaining cards in each persons (or computers) hand.
     *
     * @return number of cards left in the player's hand.
     */
    public Integer getPlayersCardsRemaining();

    /**
     * Getter so that the main() method can get access to the remaining cards in each persons (or computers) hand.
     *
     * @return number of cards left in the computer's hand.
     */
    public Integer getComputersCardsRemaining();


    /**
     * Keeps track of the car which is played during war (the second face up card, the first being the one which
     * started the war) by the computer. This allows for the GUI implementation to retrieve the image path.
     *
     * @return the image path corresponding to the card played by the computer during war.
     */
    public String getWarComputerCard();

    /**
     * Keeps track of the car which is played during war (the second face up card, the first being the one which
     * started the war) by the player. This allows for the GUI implementation to retrieve the image path.
     *
     * @return the image path corresponding to the card played by the player during war.
     */
    public String getWarPlayerCard();

    /**
     *
     * This method prints a string telling the player the outcome of the game, it is called internally if either the player
     * or the computer runs out of cards in their hand.
     *
     * @param s is a string which holds the name of the player who has run out of cards.
     *
     */
    public void gameOver(String s);
}
