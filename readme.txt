CS5004
Spring 2021
Easy Animator, Part 3 of 3


Our main method gets the command line arguments, instantiates the model and an empty view, and relinquishes control to the controller.

We implemented a single controller for all our views in the package cs5004.animator.controller.
This required extensive refactoring from our previous submission to make sure that the MVC architecture pattern was correctly followed.

The AnimatorControllerImpl implements the interface IAnimatorController and our 4 views implement the interface IAnimatorView with a new method generate().
We implemented our new PlayBack view using Swing elements: JButtons, JPanel, Timer, etc.


Controller.go() selects the appropriate view and calls the right method:

- For SVG: it calls getSVGView()
- For Text: it calls getTextView()
- For Visual: it calls getVisualView()
- For the new PlayBack: it calls getPlayBackView()


Both Visual and PlayBack use the java.swing.Timer:

- Visual sets a timer and starts it. actionPerformed(...) works on the canvas to display the shapes
- PlayBack sets a timer and a new actionPerformed to work on the canvas but waits for user input to trigger the actions


The controller creates Mouse and Keyboard listeners to execute the actions and calls the method setCommandButtonListener in the PlayBack view to get the components to be listened:

- MouseHandler represents a mouse handler object. It extends MouseAdapter
- KeyboardHandler represents a keyboard handler object. It extends KeyAdapter


Steps - PlayBack view example:
1) User input on terminal: java -jar project.jar -in foo.txt -view playback
1) Main instantiates the model, an empty view, and a controller
2) Main relinquishes control to the controller calling controller.go()
3) go() selects the PlayBack view 
4) go() creates the PlayBack view by calling getPlayBackView()
5) go() creates mouse and keyboard listeners
6) go() initiates the listeners in the PlayBack view
7) go() makes the PlayBack view visible and focused


The user can use the mouse or the keyboard to trigger actions on the animation.
Example with "Play":
1) The user clicks on the "Play" button or presses "Enter" on the keyboard
2) The play button is listened by the mouse and the enter is listened by the keyboard
3) The method play() in the controller is triggered
4) The timer starts
5) actionPerformed triggers and works on the canvas to display the animation


Our controller is also capable of creating files in case the user wants to output an svg or text animation, and it can also handle different speeds.


To test the controller, we created a mock controller that extends AnimatorControllerImpl and a mock view that extends PlayBack.
Then, we simulated keyboard events to make sure that the controller gets the right signals.


In the menu, our PlayBack view offers a "Help" option to display what every key does.