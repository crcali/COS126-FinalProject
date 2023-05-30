COS126 Final Project: Implementation

Please complete the following questions and upload this readme.txt to the
TigerFile assignment for the "Final Project Implementation".


/**********************************************************************
 * Basic Information                                                  *
 **********************************************************************/

Name 1: Christopher Caligiuri

NetID 1: cc0040

Name 2: Richard Gomez

NetID 2: rg6134

Project preceptor name: Ruth Fong

Project title: BrickBreaker!

CodePost link for proposal feedback: https://codepost.io/code/488296

Link to project video: https://youtu.be/ppf3GzB6JiQ

Approximate number of hours to complete the final project
Number of hours: 26 (including working on first project proposal)

/**********************************************************************
 * Required Questions                                                 *
 **********************************************************************/

Describe your project in a few sentences.

This project is a recreation of the the game BrickBreaker. The user is tasked
with controlling platform that moves left and right in order to prevent a ball
that is bouncing around the screen from getting past it. The ball breaks bricks
that are located at the top of the screen and the player wins each level when
all the bricks are destroyed.


Describe in detail your three features and where in your program
they are implemented (e.g. starting and ending line numbers,
function names, etc.).
1. Our first feature was the graphical user interface for the instructions and
the StdDraw window where our game is played. These both repesent the graphical
aspects of our program. The GUI for the intructions implements the Java Graphics
libaries in Swing in order to display the text and images on our instruction
window. We also used StdDraw to draw the blocks for the player to destroy,
the platform for the ball to bounce off of, and the ball. The GUI using the
Graphics library was implemented in lines 64-70 of the main Class and the
StdDraw to display the game board and its components were implemented in lines
162-205.


2. Our second feature are the blocks that the user needs to break in order to
play the game. Each block is grapically represented on the StdDraw window by
using StdDraw.filledRectangle and each are given collison by utilizing a 2D
array that tracks their position within the window and whether or not they are
destroyed by the ball. This was implemented in the setRRectangles() method in
lines 21-41 of the Level1 class, 24-48 of the Level2 class, and lines 173-199
of the Level class.


3. Our third feature is creating a ball and movable platform that the ball can
bounce off of. The platform is controlled by the left andr right arrows, and the
ball switches directions vertically whenever it hits it. While we did not
implement KeyListener to achieve this functionality as we expected we might in
the proposal, we used StdDraw.isKeyPressed() instead. The ball was implemeted in
lines 166, 158-159, and 110-113 in the Level class and the platform was
implemented in lines 135-143 also in the Level class.


Describe in detail how to compile and run your program. Include a few example
run commands and the expected results of running your program. For non-textual
outputs (e.g. graphical or auditory), feel free to describe in words what the
output should be or reference output files (e.g. images, audio files) of the
expected output.

From the description of the main class:
 > javac-introcs Main.java
 > java-introcs Main
 > Please select your difficulty: (Type 'EASY', 'MEDIUM', or 'HARD' into
  the terminal)

  Compile and run the program using the command line statements above and type
  in the level of difficulty that you want to play at (make sure it is all in
  uppercase letters). If you type a different string, it will defualt the
  difficulty to medium.

  The game will open in the StdDraw window and you can start by pressing 's'
  on your keyboard. Close the StdDraw and GUI window to restart.


Describe how your program accepts user input and mention the line number(s) at
which your program accepts user input.

This program accepts user inputs a few ways. First of all, the user needs to
input the level of difficulty they want to play the game at by typing it into
the terminal. Based on this input, the size of the platform that they control
is modified (larger for EASY and smaller for HARD). The function that takes this
input can be found at lines 79 and 96 of the Main class. Besides that, the user
needs to input keyboard commands to control the platform to the left and right.
By clicking the arrow keys, the user can move the platform and the code for this
can be found at lines 146-155 of the Level class. Two other ways the program
accepts user input is when the user starts the game by pressing 's' and when
they want to restart after they lose by typing 'YES' or 'NO' into the terminal.
The code for this can be found at lines 110 of the Level class and
lines 51-58 of the Main class.


Describe how your program produces output based on user input (mention line
numbers).

There are various outputs for this program based on what the user inputs. For
example, based on the difficulty the user wants, the size of the platform
changes. This code can be found at lines 27-46 of the Main class. Additionally,
the entire StdDraw window is updated to create a smooth animation as the ball
travels and the platform is moved. Line 168 in the Level class redraws the
platorm based on its new current position but lines 163 to 205 are where the
entire StdDraw window is updated for each frame. Additionally, this there are
sounds the are output when certain things happen in the program such as when the
ball hits the platform or when the ball hits a brick. Line 142 in the Level
class is where the sound for the platform hitting the block is output and Line
182 is where the sound for a brick breaking is produced.


Describe the data structure your program uses and how it supports your program's
functionality (include the variable name and the line number(s) at which it is
declared and initialized).

We created a 2D array that stores all the values associated with each rectangle.
In essance, each row represents a new rectangle, and the columns of each row
represent the values for that rectangle. The first column stores the
x-coordiantes of the rectangle, the second column stores the y-coordiantes of
the rectangle, and the third column stores whether or not the rectangle has been
hit by the ball. For the third column, a 1 represents that the rectangle has
been hit, while a 0 represents that it has not yet been hit. The 2D array is
names rRectangles and is declared in line 15 of the Level class and initalized
in line 31 of the Level class.

List the two custom functions written by your project group, including function
signatures and line numbers; if your project group wrote more than two custom
functions, choose the two functions that were most extensively tested.

1. Our first and most extensively used function is the function the runs our
game and each specific level. This function is called:
- public void runGame()
It can be found starting on line 82 of the Level class and runs from line 82-216
in the Level class. It is also inherited by the Level1 and Level2 class for
the different levels to also use the runGame() method.

2. Our second function is the one that sets the difficulty of our game by
changing the size of the movable platform. This function is called:
- public static double difficulty(String difficulty)
It can be found starting on line 27 of the main class and runs from line 27-46.

List the line numbers where you test each of your two custom functions twice.
For each of the four tests (two for each function), explain what was being
tested and the expected result. For non-textual results (e.g. graphical or
auditory), you may describe in your own words what the expected result
should be or reference output files (e.g. images, audio files).
1. In lines 265-266 of the Level class, we test the runGame() method for two
sample levels of different input parameters. The expected result for the first
test is a window that displays 12 blocks of width 0.1 and height 0.03 overlayed
over each other. The ball of radius 0.03 should be able to move about the screen
and hit the block. The expected result for the second test is virtually the
same as the first test, expect the block should have width 0.03 and height 0.1.
The ball should also have a radius of 0.02.

2. In lines 58/68 of the Level1 class and 65/75 of the Level2 class, we test
the runGame() method. In the Level1 class, calling the method should output a
screen that has 12 blocks all spaced apart and alternating colors between red,
yellow, and blue. The ball should start directly above the platform and, upon
pressing 's', the ball should starting moving. Whenever it hits a block, the
block should disappear. Furthermore, whenever the ball hits the bottom of the
screen, the ball should restart at its inital position and the lives should
decremement. Virutally the same thing should happen in the Level2 class,
however, the blocks should not have any spacing between them and there should be
24 total blocks.

3. In lines 78 to 86 of the Main class, we test the difficulty function by
asking the user to input what difficulty they want to play the game at. Based
on this input, terminal will output a confirmation message that checks to see
what it is about to run is the same as what the user wanted. Additionally, the
corresponding platform length is printed (0.25 for easy, 0.15 for medium, and
0.075 for hard).

4. In lines 94 to 103 of the Main class, we test the difficulty function by
asking the user to input what difficulty they want for the second level. This
essentially does what the test for the difficulty of level 1 does but ensures
that when we run the code for level 2, the difficulty function still works.


/**********************************************************************
 * Citing Resources                                                   *
 **********************************************************************/

List below *EVERY* resource your project group looked at (bullet lists and
links suffice).

- Ideas adapted from Week 2 Precept code for BouncingBallsDeluxe.java
- Sounds: https://themushroomkingdom.net/media/smb/wav
- Image: https://www.pngegg.com/en/png-dhrcb

Graphics:
- https://cs.lmu.edu/~ray/notes/javagraphics/
- https://www.oracle.com/java/technologies/painting.html
- https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html
- https://books.trinket.io/thinkjava/appendix-b.html
- https://stackoverflow.com/questions/36970962/java-graphics-update-repaint-graphic
- https://stackoverflow.com/questions/8802320/draw-text-with-graphics-object-on-jframe
- https://stackoverflow.com/questions/18806885/how-to-draw-disappearing-rectangles-in-java

Inheritance:
- How to use super in inheritance: https://www.geeksforgeeks.org/super-keyword/
- https://www.geeksforgeeks.org/inheritance-in-java/
- https://beginnersbook.com/2013/03/inheritance-in-java/
- https://www.geeksforgeeks.org/overriding-in-java/

Remember that you should *ALSO* be citing every resource that informed your
code at/near the line(s) of code that it informed.

Did you receive help from classmates, past COS 126 students, or anyone else?
If so, please list their names.  ("A Sunday lab TA" or "Office hours on
Thursday" is ok if you don't know their name.)
Yes or [no]?



Did you encounter any serious problems? If so, please describe.
Yes or [no]?




List any other comments here.




/**********************************************************************
 * Submission Checklist                                               *
 **********************************************************************/

Please mark that you’ve done all of the following steps:
[ ] Created a project.zip file, unzipped its contents, and checked that our
    compile and run commands work on the unzipped contents. Ensure that the .zip
    file is under 50MB in size.
[ ] Created and uploaded a Loom or YouTube video, set its thumbnail/starting
    frame to be an image of your program or a title slide, and checked that
    the video is viewable in an incognito browser.
[ ] Uploaded all .java files to TigerFile.
[ ] Uploaded project.zip file to TigerFile.

After you’ve submitted the above on TigerFile, remember to do the following:
[ ] Complete and upload readme.txt to TigerFile.
[ ] Complete and submit Google Form
    (https://forms.cs50.io/de2ccd26-d643-4b8a-8eaa-417487ba29ab).


/**********************************************************************
 * Partial Credit: Bug Report(s)                                      *
 **********************************************************************/

For partial credit for buggy features, you may include a bug report for at
most 4 bugs that your project group was not able to fix before the submission
deadline. For each bug report, copy and paste the following questions and
answer them in full. Your bug report should be detailed enough for the grader
to reproduce the bug. Note: if your code appears bug-free, you should not
submit any bug reports.

BUG REPORT #1:
1. Describe in a sentence or two the bug.




2. Describe in detail how to reproduce the bug (e.g. run commands, user input,
   etc.).




3. Describe the resulting effect of bug and provide evidence (e.g.
   copy-and-paste the buggy output, reference screenshot files and/or buggy
   output files, include a Loom video of reproducing and showing the effects of
   the bug, etc.).




4. Describe where in your program code you believe the bug occurs (e.g. line
   numbers).




5. Please describe what steps you tried to fix the bug.





/**********************************************************************
 * Extra Credit: Runtime Analysis                                     *
 **********************************************************************/

You may earn a small amount of extra credit by analyzing the efficiency of one
substantial component of your project. Please answer the following questions if
you would like to be considered for the extra credit for program analysis
(remember to copy and paste your answers to the following questions into the
Google form as well for credit).

Specify the scope of the component you are analyzing (e.g. function name,
starting and ending lines of specific .java file).




What is the estimated runtime (e.g. big-O complexity) of this component?
Provide justification for this runtime (i.e. explain in your own words why
you expect this component to have this runtime performance).




Provide experimental evidence in the form of timed analysis supporting this
runtime estimate. (Hint: you may find it helpful to use command-line
arguments/flags to run just the specified component being analyzed).





/**********************************************************************
 * Extra Credit: Packaging project as an executable .jar file         *
 **********************************************************************/

You may earn a small amount of extra credit by packaging your project as an
executable .jar file. Please answer the following question if you would like to
be considered for this extra credit opportunity (remember to copy and paste your
answers to the following questions into the Google form as well for credit).

Describe in detail how to execute your .jar application (e.g. what execution
command to use on the terminal). Include a few example execution commands and
the expected results of running your program. For non-textual outputs
(e.g. graphical or auditory), feel free to describe in words what the output
should be or reference output files (e.g. images, audio files) of the expected
output.



