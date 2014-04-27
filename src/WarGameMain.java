/**
 * Created by Keyan on 4/27/14.
 *
 * The main() method which allows for playing the war game at the command prompt.
 *
 */
import java.util.Scanner;

public class WarGameMain {
    public static void main(String[] args) {
        WarGame newGame = new WarGame();
        Scanner keyboard = new Scanner(System.in);

        while (true){
            Card playersCard = newGame.playerDraw();
            Card computersCard = newGame.computerDraw();

            System.out.println("Press any key to play your next card: ");
            String randmVar = keyboard.next();

            newGame.battle(playersCard, computersCard);
        }
    }
}
