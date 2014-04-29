/**
 * Created by Keyan on 4/28/14.
 *
 * The GUI for the War Card Game. Uses the java class "WarGame" as a backbone. Utilizes some aspects of "WarGameCmdPrmpt"
 * to actually run the game.
 *
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class WarGameGUI extends JFrame{
    private WarGame game;     //The backbone of the game.
    private JButton newGame;  //Starts a game, shuffles/deals/etc
    private JButton playCard; //Plays a card.
    private JPanel buttonPanel, playerPanel, computerPanel;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 500;

    private static final String BLANK = "/cardPics/blank.jpg";

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
        getContentPane().setBackground(new Color(14, 138, 210));

        buildContentPane();
        add(getContentPane());

        buildCardPanel();
        add(buttonPanel);
        add(playerPanel);
        add(computerPanel);
        setVisible(true);
    }

    /**
     *
     *
     */
    private void buildContentPane(){
        //Create the two buttons which control gameplay.
        newGame = new JButton("New Game");
        playCard = new JButton("Play Card");

        //Add event listeners for the buttons.
        newGame.addActionListener(new NewGameListener());
        playCard.addActionListener(new PlayCardListener());

        //Add the buttons to the panel.
        buttonPanel = new JPanel();
        buttonPanel.add(newGame);
        buttonPanel.add(playCard);
    }

    private void buildCardPanel(){
        //playerPanel
    }

    /**
     *
     *
     */
    private class NewGameListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

        }
    }

    private class PlayCardListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Card playersCard = newGame.playerDraw();
            Card computersCard = newGame.computerDraw();

            
            newGame.battle(playersCard, computersCard);
        }
    }

    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        WarGameGUI game = new WarGameGUI();
    }

}
