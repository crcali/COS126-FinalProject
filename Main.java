/* *****************************************************************************
 * Description: This class contains the code that shows the GUI that displays
 * the instructions to play the game. It also contains the code that allows
 * users to select a difficulty and runs the game.
 *
 * Examples:
 * > javac-introcs Main.java
 * > java-introcs Main
 * > Please select your difficulty: (Type 'EASY', 'MEDIUM', or 'HARD' into
 *   the terminal)
 *
 *   The game will open in the StdDraw window and you can start by pressing 's'
 *   on your keyboard. Close the StdDraw window when you are done.
 **************************************************************************** */

// Citation: We used the resources cited in the Graphics section of the
// readme in order to determine what modules to import

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

// Citation: We used the resources cited in the Graphics section of the
// readme in order to know what to extend
public class Main extends Canvas {

    // method that outputs a platform width based on the inputted difficulty
    // string. When the user inputs an invalid input, it will default to medium
    // difficulty.
    public static double difficulty(String difficulty) {
        // initialize the width values for each difficulty level
        double easyWidth = 0.25;
        double mediumWidth = 0.15;
        double hardWidth = 0.075;
        if (difficulty.equals("EASY"))
            return easyWidth;

        else if (difficulty.equals("MEDIUM"))
            return mediumWidth;

        else if (difficulty.equals("HARD"))
            return hardWidth;

        else {
            System.out.println("Invalid input. Defaulting to medium "
                                       + "difficulty");
            return mediumWidth; // default to medium difficulty
        }
    }

    // this method resets the game by prompting the user to determine whether
    // the user wants to continue, then returning the corresponding boolean
    // value
    public static boolean resetGame() {
        System.out.println("YOU LOSE!");
        System.out.println("Would you like to restart? ");
        if (StdIn.readString().toUpperCase().equals("YES"))
            return true;
        System.out.println("Thanks for playing!");
        return false;
    }

    // runs the game interface
    public static void main(String[] args) {

        // create a new JFrame to put the Brickbreaker instructions on
        // Citation: We used the resources cited in the Graphics section of the
        // readme in order to develop our graphics code.
        JFrame frame = new JFrame("Brickbreaker Instructions!");
        Canvas canvas = new Main();
        canvas.setSize(500, 500);
        frame.add(canvas);
        frame.pack();
        // makes the instructions visible before prompting user for any input
        frame.setVisible(true);

        // a variable that keeps track of whether the game should be running
        boolean gameRunning = true;

        while (gameRunning) {

            // prompts user to set the game's difficulty
            System.out.println("Please select your difficulty: ");
            String difficulty1 = StdIn.readString();

            // allows for testing of the difficulty function by printing out
            // the platform width, allowing us to determine whether the correct
            // value has been set
            System.out.println("You selected " + difficulty1 + " mode. The "
                                       + "platform will be of width " +
                                       difficulty(difficulty1));

            // creates and runs a new game with the specified difficulty
            Level1 game = new Level1(0.03, 3, difficulty(difficulty1));
            game.runGame();

            if (game.playerWon()) {
                // prompts user to set the game's difficulty
                System.out.println("Please select your difficulty for the "
                                           + "second game: ");
                String difficulty2 = StdIn.readString();

                // allows for testing of the difficulty function by printing out
                // the platform width, allowing us to determine whether the correct
                // value has been set
                System.out.println("You selected " + difficulty2 + " mode. The "
                                           + "platform will be of width " +
                                           difficulty(difficulty2));
                // creates and runs a new game with the specified difficulty
                // and lives carried over from the last level
                Level2 game2 = new Level2(0.03, game.getLives(),
                                          difficulty(difficulty2));
                game2.runGame();
                if (game2.playerWon()) {
                    // prints out how many lives the user had left and informs
                    // them that they won
                    System.out.println("With " + game2.getLives() +
                                               " lives remaining, you WIN!");
                    StdAudio.playInBackground("sounds/smb_world_clear.wav");
                }
                else
                    gameRunning = resetGame();
            }
            else
                gameRunning = resetGame();
        }
    }

    // this method will be run by the painting subsystem whenever the component
    // is painted. It contains the instructions for what to put on the GUI.
    // Citation: We used the resources cited in the Graphics section of the
    // readme in order to develop our code in the paint method.
    public void paint(Graphics g) {

        // a variable for the font for any text on the GUI
        final String FONT = "TimesRoman";

        // sets the font with a text size of 50
        g.setFont(new Font(FONT, Font.BOLD, 50));

        // adds the instructions to the GUI
        g.drawString("BrickBreaker", 65, 50);
        g.setFont(new Font(FONT, Font.PLAIN, 15));
        g.drawString("Welcome to BrickBreaker! "
                             + "Press 's' to start the game.", 15, 100);
        g.drawString("Type 'EASY', 'MEDIUM', OR 'HARD' to "
                             + "pick the difficulty", 20, 120);
        g.drawString("Use the left and right arrow keys to "
                             + "move the platform", 20, 140);

        // adds an image of a wall breaking to the GUI
        g.drawImage(Toolkit.getDefaultToolkit().getImage(
                "images/brickbreaker.png"), 50, 150, null);
    }

}
