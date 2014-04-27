/**
 * Created by Keyan on 4/27/14.
 *
 * Interface for the WarGame class.
 *
 */
public interface WarGameInterface {

    /**
     *
     * @param inputDeck
     */
    public void dealHand(Deck inputDeck);

    /**
     *
     * @param playerCard
     * @param computerCard
     * @return
     */
    public char battle(Card playerCard, Card computerCard);

    /**
     *
     * @param playerCard
     * @param computerCard
     * @return
     */
    public char war(Card playerCard, Card computerCard);

    /**
     *
     * @return
     */
    public Card playerDraw();

    /**
     *
     * @return
     */
    public Card computerDraw();

    /**
     *
     * @param s
     */
    public void gameOver(String s);

}
