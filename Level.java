/* *****************************************************************************
 * Description: This is the superclass class for both Level1 and Level2.
 * It contains the features that Level1 and Level2 will both inherit,
 * including determining whether or not the player has one and running the
 * game itself. It tracks the user's inputs and updates the board accordingly
 * based on the current position of the ball and platform.
 **************************************************************************** */

import java.awt.Color;

public class Level {

    // initializes a 2D array for the rectangles that stores the rectangles
    // x and y coordinates and whether or not it has been hit
    private double[][] rRectangles;
    private final double RECT_WIDTH;    // sets the width of the bricks
    private final double RECT_HEIGHT;   // sets the height of the bricks
    private final int NUM_RECTANGLES;   // sets the number of bricks that appear

    private final double RADIUS;    // sets radius of the ball
    private double lives;           // tracks the lives the player has remaining
    private final double PLATFORM_W; // sets the width of the platform

    public Level(double radius, double lives, double platformWidth,
                 int numRectangles, double[][] rRectangles, double rectWidth,
                 double rectHeight) {
        this.RADIUS = radius;
        this.lives = lives;
        this.PLATFORM_W = platformWidth;
        this.NUM_RECTANGLES = numRectangles;
        this.rRectangles = new double[NUM_RECTANGLES][3];
        this.RECT_HEIGHT = rectHeight;
        this.RECT_WIDTH = rectWidth;
    }

    // function checks to see how many lives the player has remaining
    public double getLives() {
        return lives;
    }

    // function checks to see what the width of the platform is
    public double getPlatformW() {
        return PLATFORM_W;
    }

    // function checks to see what the width of each brick is
    public double getRectWidth() {
        return RECT_WIDTH;
    }

    // function checks to see what the current number of bricks remaining are
    public double getNumRectangles() {
        return NUM_RECTANGLES;
    }

    // function gets current 2D matrix for the bricks
    public double[][] getrRectangles() {
        return rRectangles;
    }

    // function checks if the player has won based on whether or not the
    // matrix shows any blocks that are unbroken
    public boolean playerWon() {
        for (int i = 0; i < rRectangles.length; i++) {
            if (rRectangles[i][2] == 0) return false;
        }
        return true;
    }

    // sets the rRectangles array to default values (all zeros)
    public void setRRectangles() {
        for (int i = 0; i < getNumRectangles(); i++) {
            getrRectangles()[i][0] = 0;
            getrRectangles()[i][1] = 0;
            // defaults to the rectangle not being hit
            getrRectangles()[i][2] = 0;
        }
    }

    // function runs the first level when called by setting up the StdDraw
    // window
    // Citation: We used the code from COS Precept week 2 in order to
    // understand how to have a continuously updating window using StdDraw.
    public void runGame() {

        // constants for the boundaries of the window
        final double X_AXIS = 1.0;
        final double Y_AXIS = 1.0;

        // Citation: We used the code from COS Precept week 2 in order to
        // understand how to implement a bouncing ball.
        double rxBall = 0.2, ryBall = -0.4;     // initial position of the ball
        double vxBall = 0, vyBall = 0;    // initial velocity of ball

        // initial dimensions and position of the platform
        double rxPlatform = 0.2, ryPlatform = -0.5;
        final double PLATFORM_H = 0.01;

        // a constant for the platform's movement with each input
        final double PLATFORM_INCREMENT = 0.03;

        // StdDraw window is constructed and fine-tuned for animations
        StdDraw.setXscale(-X_AXIS, X_AXIS);
        StdDraw.setYscale(-Y_AXIS, Y_AXIS);
        StdDraw.enableDoubleBuffering();

        // loop updates the StdDraw window
        while (true) {

            boolean alreadyHitBlock = false;

            double random = (StdRandom.uniform() - 0.5);

            if (StdDraw.isKeyPressed(83) && (vxBall == 0) && (vyBall == 0)) {
                vxBall = 0.02;
                vyBall = 0.02;
            }

            // adds collisions to the walls and ensures the player loses a life
            // and resets when the bottom of the window is reached
            if (Math.abs(rxBall + vxBall) + RADIUS > X_AXIS) vxBall = -vxBall;

            if (ryBall + vyBall + RADIUS > Y_AXIS) vyBall = -vyBall;

            if (ryBall + vyBall + RADIUS < -Y_AXIS) {
                lives--;
                rxBall = random;
                ryBall = -0.4;
                vxBall = 0;
                vyBall = 0;
                rxPlatform = random;
                ryPlatform = -0.5;
                vyBall = -vyBall;
                StdAudio.playInBackground("sounds/smb_kick.wav");
            }

            // if statement creates collision between ball and platform while
            // also ensures the ball is above the platform when it collides
            if ((ryBall + vyBall) + RADIUS < (ryPlatform - 2 * PLATFORM_H))
                vyBall = vyBall + 0;

            else if ((ryBall + vyBall) + RADIUS < (ryPlatform + RADIUS) &&
                    ((rxBall + vxBall) + RADIUS) < (rxPlatform + PLATFORM_W) &&
                    ((rxBall + vxBall) + RADIUS) > (rxPlatform - PLATFORM_W)) {
                vyBall = -vyBall;
                StdAudio.playInBackground("sounds/smb_bump.wav");
            }

            // updates position of platform based on user input
            if (StdDraw.isKeyPressed(37)) {
                if ((rxPlatform - PLATFORM_W) < -X_AXIS) rxPlatform -= 0;
                else rxPlatform -= PLATFORM_INCREMENT;

            }
            else if (StdDraw.isKeyPressed(39)) {
                if ((rxPlatform + PLATFORM_W) > X_AXIS) rxPlatform += 0;
                else rxPlatform += PLATFORM_INCREMENT;

            }

            // position of the ball is updated
            // Citation: We used the code from COS Precept week 2 in order to
            // understand how to implement a bouncing ball.
            rxBall = rxBall + vxBall;
            ryBall = ryBall + vyBall;


            // each aspect of the window is redrawn
            StdDraw.clear(StdDraw.LIGHT_GRAY);

            StdDraw.setPenColor(Color.black);
            StdDraw.filledCircle(rxBall, ryBall, RADIUS);

            StdDraw.filledRectangle(rxPlatform, ryPlatform, PLATFORM_W, PLATFORM_H);

            StdDraw.setPenColor(Color.blue);

            // loop adds collision to the blocks and
            for (int i = 0; i < rRectangles.length; i++) {
                double currentYPos = ryBall + RADIUS;
                double currentXPos = rxBall + RADIUS;
                if ((currentYPos > rRectangles[i][1] - (RECT_HEIGHT - RADIUS)) &&
                        (rRectangles[i][1] + (RECT_HEIGHT + RADIUS) > currentYPos) &&
                        (currentXPos > rRectangles[i][0] - (RECT_WIDTH + RADIUS)) &&
                        (rRectangles[i][0] + (RECT_WIDTH - RADIUS) > currentXPos) &&
                        !alreadyHitBlock) {
                    if (rRectangles[i][2] == 0) {
                        StdAudio.playInBackground("sounds/smb_coin.wav");
                        vyBall = -vyBall;
                        alreadyHitBlock = true;
                    }

                    rRectangles[i][2] = 1;
                }

                if (!(rRectangles[i][2] == 1)) {
                    if (i % 3 == 0) StdDraw.setPenColor(Color.blue);
                    else if (i % 2 == 0) StdDraw.setPenColor(Color.orange);
                    else StdDraw.setPenColor(Color.red);

                    StdDraw.filledRectangle(rRectangles[i][0],
                                            rRectangles[i][1],
                                            RECT_WIDTH / 2, RECT_HEIGHT / 2);
                }
            }

            StdDraw.text(-0.8, -0.8, "Lives: " + lives);

            // updated window is drawn and if-statement breaks loop if the
            // player loses
            StdDraw.show();
            if (lives <= 0) {
                StdAudio.playInBackground("sounds/smb_mariodie.wav");
                break;
            }
            if (playerWon()) {
                StdAudio.playInBackground("sounds/smb_stage_clear.wav");
                break;
            }
            StdDraw.pause(20);
        }
    }

    // tests the different methods in the Level class
    public static void main(String[] args) {
        // create two testing objects of type Level
        Level test = new Level(0.03, 3, 0.25, 12, new double[12][3], 0.1, 0.03);
        Level test2 = new Level(0.02, 3, 0.15, 18, new double[18][3], 0.03, 0.1);

        // print each element to ensure that they're set properly
        System.out.println("Lives: " + test.getLives());
        System.out.println("Lives: " + test2.getLives());
        System.out.println("Platform Width: " + test.getPlatformW());
        System.out.println("Platform Width: " + test2.getPlatformW());
        System.out.println("Rectangle Width: " + test.getRectWidth());
        System.out.println("Rectangle Width: " + test2.getRectWidth());

        // set the first rectangle's x-coordinate to 1
        test.getrRectangles()[0][0] = 1.0;
        test2.getrRectangles()[0][0] = 1.0;
        for (int i = 0; i < test.getrRectangles().length; i++) {
            // print each element of the rRectangles array
            System.out.println("x-coordinate: " + test.getrRectangles()[i][0]);
            System.out.print(" y-coordinate: " + test.getrRectangles()[i][1]);
            System.out.print(" hit: " + test.getrRectangles()[i][2]);
        }
        for (int i = 0; i < test2.getrRectangles().length; i++) {
            // print each element of the rRectangles array
            System.out.print("x-coordinate: " + test2.getrRectangles()[i][0]);
            System.out.print(" y-coordinate: " + test2.getrRectangles()[i][1]);
            System.out.println(" hit: " + test2.getrRectangles()[i][2]);
        }

        test.setRRectangles(); // reset the rRectangle array
        test2.setRRectangles(); // reset the rRectangle array
        for (int i = 0; i < test.getrRectangles().length; i++) {
            // print each element of the rRectangles array after resetting the
            // values
            System.out.print("x-coordinate: " + test.getrRectangles()[i][0]);
            System.out.print(" y-coordinate: " + test.getrRectangles()[i][1]);
            System.out.println(" hit: " + test.getrRectangles()[i][2]);
        }
        for (int i = 0; i < test2.getrRectangles().length; i++) {
            // print each element of the rRectangles array after resetting the
            // values
            System.out.print("x-coordinate: " + test2.getrRectangles()[i][0]);
            System.out.print(" y-coordinate: " + test2.getrRectangles()[i][1]);
            System.out.println(" hit: " + test2.getrRectangles()[i][2]);
        }

        test.runGame();
        test2.runGame();
    }
}
