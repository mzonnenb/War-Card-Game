import java.util.ArrayList;
import java.util.Random;

/**
 * Keyan Pishdadian 2014, code belongs to Jackie Horton 2014
 *
 * This class creates a "deck" of cards by constructing an arrayList with 52 Card objects, methods are provided for
 * shuffling, dealing, and removing cards.
 */
public class Deck
{
    //will only be used for standard card game so set cards to 52.
    private final int CARDS_IN_DECK = 52;
    //The ArrayList which holds the references for the Card objects.
    private ArrayList<Card> deck;

    /**
     * Constructor for the deck, calls the freshDeck() method.
     *
     */
    public Deck()
    {
        freshDeck();
    }

    /**
     * Creates the 52 Card object ArrayList with nested loops. The cards are in order and must be randomized before use.
     */
    public void freshDeck()
    {
        deck = new ArrayList<Card>(CARDS_IN_DECK);

        for (int r = 2; r <= 14;r++)
        {
            for (int s = 1;s <= 4;s++)
            {
                deck.add(new Card(r,s));
            }
        }

    }

    /**
     * Method removes the card in the first position of the ArrayList.
     *
     * @return Card object in the first position of the ArrayList.
     */
    public Card dealCard()
    {
        Card c = deck.remove(0);  //  remove it (returns removed object)
        return c;
    }

    /**
     * Finds the size of the ArrayList, which signifies the number of elements left in the "deck".
     *
     * @return integer size of the ArrayList.
     */
    public int cardsRemaining()
    {
        return deck.size();
    }

    /**
     * Randomizes the order of Card objects in the ArrayList.
     *
     */
    public void shuffle()
    {
        int randNum;
        Card temp;
        Random r = new Random();
        for (int i = 0; i < deck.size(); i++)
        {
            randNum = r.nextInt(deck.size());
            temp = deck.get(i);
            deck.set(i,deck.get(randNum));
            deck.set(randNum,temp);
        }
    }

    /**
     * Checks if the ArrayList has anymore elements remaining.
     *
     * @return true if there are no more elements in the ArrayList
     *         false if there are more elements
     */
    public boolean isEmpty()
    {
        return (deck.size() == 0);
    }
}