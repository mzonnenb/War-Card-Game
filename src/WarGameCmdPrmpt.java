/**
 * Created by Keyan on 4/27/14.
 *
 * The main() method which allows for playing the war game at the command prompt. Currently gameplay requires
 * the input of any letter, but the game can be run automatically without user input by commenting out line 23.
 *
 */
import java.util.Scanner;

public class WarGameCmdPrmpt {
    public static void main(String[] args) {
        WarGame newGame = new WarGame();
        Scanner keyboard = new Scanner(System.in);

        //Loop runs the game, gameplay countinues until
        while (true){
            //Pulls cards off of their respective queues, before player confirms gameplay, calls the battle() method
            //once input is received.
            Card playersCard = newGame.playerDraw();
            Card computersCard = newGame.computerDraw();

            System.out.println("Your cards remaining: " + newGame.getPlayersCardsRemaining());
            System.out.println("Computer cards remaining: " + newGame.getComputersCardsRemaining());
            System.out.println();
            System.out.println("Enter any key to play your next card: ");
            //the variable randmVar doesn't actually do anything, just allows for the user to control gameplay.
            String randmVar = keyboard.next();

            //Start a battle.
            newGame.battle(playersCard, computersCard);
        }
    }
}
