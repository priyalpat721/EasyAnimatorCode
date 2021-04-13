CS5004
Spring 2021
Easy Animator, Part 2 of 3


The entry point to our program is the main method in the class EasyAnimator in package cs5004.animator.

Program steps:

1) The program parses the command-line arguments with the static method 'parseCommands'.
This method handles invalid inputs and returns a String array with all the commands passed.
If the command is mandatory and the input is invalid, the method calls 'showMessage' to popup an error message and exits.
If the command is not mandatory and the input is invalid, the method calls 'showMessage' to popup an error message and sets the command to default values.
The default speed value is 1. The default view for SVG and text is System.out.

2) The program creates a Builder object that acts as an adapter between the input file reader that was given to us and our model implementation.
The Builder was implemented following the Builder design pattern.
We added three methods, one field variable, and one action to our model implementation:
- 'createShape' that creates a shape with no attributes
- 'setAttributes' that sets the attributes of a shape
- 'setBounds' that sets the attributes of the canvas box
- field variable 'box' that represents the canvas box
- 'Stay' action that represents a 'no motion' action

3) The program tries to read the input file.
If the file is not found, the program calls 'showMessage' again to popup an error message and exits.

4) The program populates the animator model by calling the method 'parseFile' passing the valid input file and the Builder previously created.

5) The program generates a view calling the view factory method 'generateView'.
The three views available in package cs5004.animator.view are: svg, text, and visual.
They implement the interface IAnimatorView that contains a single method 'create'.

- The SVG view is implemented in the class SVGView
- The text view is implemented in the class TextView
- The visual view is implemented in the class VisualView that 'has a' Canvas that 'has a' ShapesPanel

This method calls 'showMessage' to popup an error message if the animator model is empty.

We added a two new methods to our model implementation and changed a data structure:
- 'toString' that returns a string representation of the animation with the speed
- 'getTotalTime' that returns the total time of display of the animation
- data structure 'logOfShapes' from a Hashmap to a List to maintain order

6) If the program needs to output a file, it calls the method 'createFile' that creates the file and returns its name.
'showMessage' is called again to popup a plain message communicating the success or failure of the operation.