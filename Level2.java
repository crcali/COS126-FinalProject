/* *****************************************************************************
 * Description: This subclass contains the code that sets up the board for the
 * second level of the brickbreaker game. This class differs from Level1 by
 * having a different orientation of the bricks.
 **************************************************************************** */

// Citation: We used the resources cited in the Inheritance section of
// the readme in order to know how to use 'extends'
public class Level2 extends Level {

    // constructor method that both sets the radius, number of lives, and
    // platform width, while also calling setRRectangles() to initialize all
    // the x and y positions of each rectangle
    public Level2(double r, double life, double platformWidth) {
        // calls parent class constructor method.
        // NOTE: we could not store the rectangle number, width, or height in
        // variables to avoid hard coded values due to the nature of inheritance
        // Citation: We used the resources cited in the Inheritance section of
        // the readme in order to know how to use 'super'
        super(r, life, platformWidth, 24, new double[24][3], 0.333, 0.1);
        setRRectangles(); // sets the x and y positions of the rRectangles array
    }

    // sets the x and y positions of each rectangle, and defaults the last value
    // in each column to zero, indicating that the rectangle has not been hit
    // by the ball yet. It overrides the inherited setRRectangles().
    // Citation: We used the resources cited in the Inheritance section of
    // the readme in order to know how to use '@Override'
    @Override
    public void setRRectangles() {
        // x and y positions of the first rectangle
        double initRectX = -1 + getRectWidth() / 2;
        double initRectY = 0.2;

        // keeps track of whether or not we should be adding or subtracting from
        // the x coordinate depending on whether or not we are on an even or odd
        // row
        boolean oddRow = true;
        for (int i = 0; i < getNumRectangles(); i++) {
            if (i % (getNumRectangles() / 4) == 0) {
                initRectY += 0.1; // go up on the screen for the next row
                if (oddRow) oddRow = false; // invert the oddRow variable
                else oddRow = true;
            }
            else if (oddRow) initRectX -= getRectWidth();
            else initRectX += getRectWidth();

            // sets the x and y positions of the current rectangle
            getrRectangles()[i][0] = initRectX;
            getrRectangles()[i][1] = initRectY;
            // defaults to the rectangle not being hit
            getrRectangles()[i][2] = 0;
        }
    }

    // tests the different methods in the Level2 class
    public static void main(String[] args) {
        // creates a testing object of type Level2 with different parameters
        Level2 test = new Level2(0.03, 3, 0.25);

        // print different elements of the test object
        System.out.println("Lives: " + test.getLives());
        System.out.println("Platform Width: " + test.getPlatformW());
        System.out.println("Rectangle Width: " + test.getRectWidth());
        for (int i = 0; i < test.getrRectangles().length; i++) {
            // print each element of the rRectangles array
            System.out.println("x-coordinate: " + test.getrRectangles()[i][0]);
            System.out.print(" y-coordinate: " + test.getrRectangles()[i][1]);
            System.out.print(" hit: " + test.getrRectangles()[i][2]);
        }
        test.runGame();

        // sets the rectangles to default values to test its function
        test.setRRectangles();
        for (int i = 0; i < test.getrRectangles().length; i++) {
            // print each element of the rRectangles array
            System.out.println("x-coordinate: " + test.getrRectangles()[i][0]);
            System.out.print(" y-coordinate: " + test.getrRectangles()[i][1]);
            System.out.print(" hit: " + test.getrRectangles()[i][2]);
        }
        test.runGame();
    }

}
