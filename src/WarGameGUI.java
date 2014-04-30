/**
 * Created by Keyan on 4/28/14.
 *
 * The GUI for the War Card Game. Uses the java class "WarGame" as a backbone. Utilizes some aspects of "WarGameCmdPrmpt"
 * to actually run the game.
 *
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.*;


public class WarGameGUI extends JFrame{
    private WarGame game;                                       //The backbone of the game.
    private JButton playCardButton;                             //Plays a card.

    private static final String BLANK = "cardPics/back.jpg";    //Path to image of card backside.
    private ImageIcon blankCard;                                //Icon which displays image of card back.
    private ImageIcon blankCard2;
    private ImageIcon blank;                                    //Actually nothing, just a placeholder.
    private ImageIcon playerCard;                               //Holds path to the current card being played by the player.
    private ImageIcon computerCard;                             //Holds path to the current card being played by the computer.

    private JLabel blankCardLabelLeft;                          //Label which holds the imageIcon for the back-side of a card.
    private JLabel blankCardLabelRight;
    private JLabel playerCardLabel;                             //Label which holds the ImageIcon for the player's current card selection.
    private JLabel computerCardLabel;                           //Label which holds the ImageIcon for the computer's current card selection.

    private JTextField playerCardsLeft;
    private JTextField computerCardsLeft;

    private JPanel buttonPanel;                                 //Holds the "playCard" button which results in both players drawing cards.
    private JPanel rightDeckPanel;                              //Holds the "blankCardLabel" JLabel, stimulates the decks of the player.
    private JPanel leftDeckPanel;                               //Holds the "blankCardLabel" JLabel, stimulates the decks of the computer.
    private JPanel cardsPanel;                                  //Holds both the "playerCardLabel" and "computerCardLabel", where gameplay occurs.
    private JPanel counterPanel;                                //Holds the counters which show how many cards each player has left in their decks.

    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 500;
    private String war = "false";



    /**
     *
     *
     */
    public WarGameGUI(){
        game = new WarGame();

        setLayout(new BorderLayout());
        setTitle("WAR! Card Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Builds the button panel.
        buildButtonPanel();
        //Adds it to the JFrame content pane, in the bottom position "PAGE_END"
        add(buttonPanel, BorderLayout.PAGE_END);

        buildDecksPanel();
        add(leftDeckPanel, BorderLayout.LINE_START);
        add(rightDeckPanel, BorderLayout.LINE_END);


        buildCounterPanel();
        add(counterPanel, BorderLayout.PAGE_START);

        buildCardsPanel();
        add(cardsPanel, BorderLayout.CENTER);

        setVisible(true);

        JOptionPane.showMessageDialog(null, "Welcome to WAR!, a Java implementation of the classic card game War.\n\nThe rules are simple, just press 'Play Card' " +
                "to start playing. Your deck \nis on the left, the computer's deck is on the right. The winner of each hand\n is the one that draws the highest card, and gets" +
                "to take both cards, aces are high!\n" + "If both cards are the same, then you must play a face down card and then another\nface up card, the winner takes all six cards!\n" +
                "\nGood Luck!");
    }

    /**
     *
     *
     */
    private void buildButtonPanel(){
        //Create the two buttons which control gameplay.
        playCardButton = new JButton("Play Card");
        playCardButton.setPreferredSize(new Dimension(200,100));

        //Add event listeners for the buttons.
        playCardButton.addActionListener(new PlayCardListener());

        //Add the buttons to the panel.
        buttonPanel = new JPanel();
        buttonPanel.add(playCardButton);
        buttonPanel.setBackground(new Color(14, 138, 210));
    }

    /**
     *
     *
     */
    private void buildCardsPanel(){
        blank = new ImageIcon();

        playerCardLabel = new JLabel(blank);
        computerCardLabel = new JLabel(blank);

        cardsPanel = new JPanel();
        cardsPanel.add(playerCardLabel);
        cardsPanel.add(computerCardLabel);
        cardsPanel.setBackground(new Color(89, 3, 67));
        cardsPanel.setPreferredSize(new Dimension(600, 600));
    }

    /**
     *
     *
     */
    private void buildDecksPanel(){
        blankCard = new ImageIcon(BLANK);
        blankCard2 = new ImageIcon(BLANK);

        blankCardLabelLeft = new JLabel();
        blankCardLabelLeft.setIcon(blankCard);

        blankCardLabelRight = new JLabel();
        blankCardLabelRight.setIcon(blankCard);

        rightDeckPanel = new JPanel();
        rightDeckPanel.setPreferredSize(new Dimension(152,213));
        rightDeckPanel.add(blankCardLabelRight);

        leftDeckPanel = new JPanel();
        leftDeckPanel.setPreferredSize(new Dimension(152,213));
        leftDeckPanel.add(blankCardLabelLeft);

        leftDeckPanel.setBackground(new Color(89, 3, 67));
        rightDeckPanel.setBackground(new Color(89, 3, 67));
    }

    /**
     *
     *
     */
    private void buildCounterPanel(){
        playerCardsLeft = new JTextField("Player's Cards: 26", 26);
        computerCardsLeft = new JTextField("Computer's Cards: 26", 26);

        playerCardsLeft.setEditable(false);
        computerCardsLeft.setEditable(false);

        counterPanel = new JPanel();
        counterPanel.add(playerCardsLeft);
        counterPanel.add(computerCardsLeft);
    }

    /**
     *
     *
     */
    private class PlayCardListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (war.equals("last")){
                playerCard = new ImageIcon(game.getWarPlayerCard());
                playerCardLabel.setIcon(playerCard);

                computerCard = new ImageIcon(game.getWarComputerCard());
                computerCardLabel.setIcon(computerCard);

                war = "final";
            }

            if (war.equals("true")){
                playerCard = new ImageIcon(BLANK);
                playerCardLabel.setIcon(playerCard);

                computerCard = new ImageIcon(BLANK);
                computerCardLabel.setIcon(computerCard);

                war = "last";
            }

            if (war.equals("false")){
                Card playersCard = game.playerDraw();
                Card computersCard = game.computerDraw();

                playerCard = new ImageIcon(playersCard.getCardPic());
                playerCardLabel.setIcon(playerCard);

                computerCard = new ImageIcon(computersCard.getCardPic());
                computerCardLabel.setIcon(computerCard);


                game.battle(playersCard, computersCard);


                if (playersCard.equals(computersCard)){
                    war = "true";
                    JOptionPane.showMessageDialog(null, "WAR!");
                }

                if (!war.equals("true")){
                    playerCardsLeft.setText("Player's Cards: " + (game.getPlayersCardsRemaining() - 1));
                    computerCardsLeft.setText("Computer's Cards: " + (game.getComputersCardsRemaining() - 1));

                }
            }

            if (war.equals("final")){
                war = "false";
                playerCardsLeft.setText("Player's Cards: " + (game.getPlayersCardsRemaining() - 1));
                computerCardsLeft.setText("Computer's Cards: " + (game.getComputersCardsRemaining() - 1));
            }
        }
    }

    /**
     *
     *
     */
    public static void main(String[] args) {
        WarGameGUI guiGame = new WarGameGUI();
    }

}
