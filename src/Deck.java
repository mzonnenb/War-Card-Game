import java.util.ArrayList;
import java.util.Random;

/**
 *
 *
 *
 */
public class Deck
{
    private final int CARDS_IN_DECK = 52;
    private ArrayList<Card> deck;

    /**
     *
     *
     */
    public Deck()
    {
        freshDeck();
    }

    /**
     *
     *
     *
     */
    public void freshDeck()
    {
        deck = new ArrayList<Card>();
        System.out.println(deck.size());

        for (int r = Card.ACE; r<=Card.KING;r++)
        {
            for (int s=Card.SPADES;s<=Card.CLUBS;s++)
            {
                deck.add(new Card(r,s));
            }
        }

    }

    /**
     *
     *
     * @return
     */
    public Card dealCard()
    {
        Card c = deck.remove(0);  //  remove it (returns removed object)
        return c;
    }

    /**
     *
     * @return
     */
    public int cardsRemaining()
    {
        return deck.size();
    }

    /**
     *
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
     *
     * @return
     */
    public boolean isEmpty()
    {
        return (deck.size() == 0);
    }
}