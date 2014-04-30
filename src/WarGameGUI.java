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
    private ImageIcon playerCard;                               //Holds path to the current card being played by the player.
    private ImageIcon computerCard;                             //Holds path to the current card being played by the computer.

    private JLabel blankCardLabel;                              //Label which holds the imageIcon for the back-side of a card.
    private JLabel playerCardLabel;                             //Label which holds the ImageIcon for the player's current card selection.
    private JLabel computerCardLabel;                           //Label which holds the ImageIcon for the computer's current card selection.

    private JTextField playerCardsLeft;
    private JTextField computerCardsLeft;

    private JPanel buttonPanel;                                 //Holds the "playCard" button which results in both players drawing cards.
    private JPanel decksPanel;                                  //Holds the "blankCardLabel" JLabel, stimulates the decks of the computer and player.
    private JPanel cardsPanel;                                  //Holds both the "playerCardLabel" and "computerCardLabel", where gameplay occurs.
    private JPanel counterPanel;                                //Holds the counters which show how many cards each player has left in their decks.

    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 500;



    /**
     *
     *
     */
    public WarGameGUI(){
        game = new WarGame();

        setLayout(new BorderLayout());
        setTitle("War Card Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Builds the button panel.
        buildButtonPanel();
        //Adds it to the JFrame content pane, in the bottom position "PAGE_END"
        add(buttonPanel, BorderLayout.PAGE_END);

        buildDecksPanel();
        add(decksPanel, BorderLayout.LINE_END);
        add(decksPanel, BorderLayout.LINE_START);

        buildCounterPanel();
        add(counterPanel, BorderLayout.PAGE_START);

        buildCardsPanel();
        add(cardsPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     *
     *
     */
    private void buildButtonPanel(){
        //Create the two buttons which control gameplay.
        playCardButton = new JButton("Play Card");

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
        playerCard = new ImageIcon("cardPics/21.jpg");
        computerCard = new ImageIcon(BLANK);

        playerCardLabel = new JLabel(playerCard);
        computerCardLabel = new JLabel(computerCard);

        cardsPanel = new JPanel();
        cardsPanel.add(playerCardLabel);
    }

    /**
     *
     *
     */
    private void buildDecksPanel(){
        blankCard = new ImageIcon(BLANK);

        blankCardLabel = new JLabel(blankCard);

        decksPanel = new JPanel();
        decksPanel.add(blankCardLabel);
    }

    /**
     *
     * 
     */
    private void buildCounterPanel(){
        playerCardsLeft = new JTextField(26);
        computerCardsLeft = new JTextField(26);

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
            Card playersCard = game.playerDraw();
            Card computersCard = game.computerDraw();


            game.battle(playersCard, computersCard);

            playerCardsLeft.setText(game.getPlayersCardsRemaining().toString());
            computerCardsLeft.setText(game.getComputersCardsRemaining().toString());
        }
    }

    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        WarGameGUI guiGame = new WarGameGUI();
    }

}
