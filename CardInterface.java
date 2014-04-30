/**
 * Created by Keyan on 4/30/14.
 *
 */
public interface CardInterface {
    /**
     * Returns the suit of the card.
     * @return a Suit constant representing the suit value of the card.
     */
    public int getSuit();

    /**
     * Returns the rank of the card.
     * @return a Rank constant representing the rank value of the card.
     */
    public int getRank();

    /**
     * Returns a description of the suit of this card.
     * @return the suit value of the card as a string.
     */
    public String getSuitAsString();

    /**
     * Returns a description of the rank of this card.
     * @return the rank value of the card as a string.
     */
    public String getRankAsString();

    /**
     * Returns a description of this card.
     * @return the name of the card.
     */
    public String toString();

    /**
     * Compares two cards to determine if they have the same value.
     * @param otherCard
     * @return true if the two cards have the same rank,
     * falseotherwise.
     */
    public boolean equals(Card otherCard);

    /**
     * Retrieves the image of the card.
     *
     * @return the path to the jpg file.
     */
    public String getCardPic();
}
