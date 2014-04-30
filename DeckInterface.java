/**
 * Created by Keyan on 4/30/14.
 *
 * Interface for the Deck class.
 *
 */
public interface DeckInterface {
    /**
     * Creates the 52 Card object ArrayList with nested loops. The cards are in order and must be randomized before use.
     */
    public void freshDeck();

    /**
     * Method removes the card in the first position of the ArrayList.
     *
     * @return Card object in the first position of the ArrayList.
     */
    public Card dealCard();

    /**
     * Finds the size of the ArrayList, which signifies the number of elements left in the "deck".
     *
     * @return integer size of the ArrayList.
     */
    public int cardsRemaining();

    /**
     * Randomizes the order of Card objects in the ArrayList.
     *
     */
    public void shuffle();

    /**
     * Checks if the ArrayList has anymore elements remaining.
     *
     * @return true if there are no more elements in the ArrayList
     *         false if there are more elements
     */
    public boolean isEmpty();
}
