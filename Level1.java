/* *****************************************************************************
 * Description: This subclass contains the code that sets up the board for the
 * first level of the brickbreaker game. It inherits all the methods from the
 * Level class and defines its own rectangles for Level 1's game board.
 **************************************************************************** */

// Citation: We used the resources cited in the Inheritance section of
// the readme in order to know how to use 'extends'
public class Level1 extends Level {

    // constructor method that both sets the radius, number of lives, and
    // platform width, while also calling setRRectangles() to initialize all
    // the x and y positions of each rectangle
    public Level1(double r, double life, double platformWidth) {
        // calls parent class constructor method.
        // NOTE: we could not store the rectangle number, width, or height in
        // variables to avoid hard coded values due to the nature of inheritance
        // Citation: We used the resources cited in the Inheritance section of
        // the readme in order to know how to use 'super'
        super(r, life, platformWidth, 12, new double[12][3], 0.2, 0.1);
        setRRectangles(); // sets the x and y positions of the rRectangles array
    }

    // sets the x and y positions of each rectangle, and defaults the last value
    // in each column to zero, indicating that the rectangle has not been hit
    // by the ball yet. It overrides the inherited setRRectangles().
    // Citation: We used the resources cited in the Inheritance section of
    // the readme in order to know how to use '@Override'
    @Override
    public void setRRectangles() {
        double initRectX = -1.1; // x position of the first rectangle
        double initRectY = 0.2; // y position of the first rectangle

        for (int i = 0; i < getNumRectangles(); i++) {
            if (i == getNumRectangles() / 2)
                initRectY += 0.3; // go up on the screen for the next row
            if (i > getNumRectangles() / 2)
                initRectX -= 0.3;
            else if (i != getNumRectangles() / 2)
                initRectX += 0.3;
            // sets the x and y positions of the current rectangle
            getrRectangles()[i][0] = initRectX;
            getrRectangles()[i][1] = initRectY;
            // defaults to the rectangle not being hit
            getrRectangles()[i][2] = 0;
        }
    }

    // tests the different methods in the Level1 class
    public static void main(String[] args) {
        // creates a testing object of type Level2 with different parameters
        Level1 test = new Level1(0.03, 3, 0.25);

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
