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
    private JPanel panel;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 500;

    /**
     *
     *
     */
    public WarGameGUI(){
        setTitle("War Card Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        buildPanel();
        add(panel);
        setVisible(true);
    }

    /**
     *
     *
     */
    private void buildPanel(){
        //Create the two buttons which control gameplay.
        newGame = new JButton("New Game");
        playCard = new JButton("Play Card");


        panel = new JPanel();
        panel.add(newGame);
        panel.add(playCard);
    }

    /**
     *
     *
     */
    private class NewGameListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            WarGame newGame = new WarGame();
        }
    }

    private class playCardListener implements ActionListener{
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
