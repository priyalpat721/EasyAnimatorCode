CS5004
Spring 2021
Easy Animator, Part 1 of 3


Our Easy Animator Model consists of 4 packages:

cs5004.animator - encapsulates all the classes related to the model implementation:
- (I) IAnimatorModel: interface of the model with all the operations that the model implementation supports.
- (C) AnimatorModelImpl: concrete class of the model implementation that implements IAnimatorModel.

The animator package is in charge of:
- Create shapes for the animation
- Move shapes in the animation, change their dimensions and color
- Return a list of updated shapes at a particular moment in time
- Return a string representation of the entire animation

cs5004.action - encapsulates all the classes related to the actions that the model implementation supports:
- (I) IAction: interface with all the operations that an action supports.
- (A) AbstractAction: abstract class that implements IAction to enhance code re-usability.
- (E) Action: enum that includes the types of actions.
- (C) Move: concrete class of the move action that extends AbstractAction.
- (C) Scale: concrete class of the scale action that extends AbstractAction.
- (C) ChangeColor: concrete class of the 'change color' action that extends AbstractAction.

The action package is in charge of:
- Create a Move object when a shape is moved
- Create a Scale object when the dimensions of a shape are changed
- Create a ChangeColor object when the color of a shape is changed
- Return the state of a specific shape at a particular moment in time


cs5004.shape - encapsulates all the classes related to the shapes that the model uses:
- (I) IShape: interface with all the operations that a shape supports.
- (A) AbstractShape: abstract class that implements IShape to enhance code re-usability.
- (E) Shape: enum that includes the types of shapes.
- (C) Rectangle: concrete class for the rectangle shape that extends AbstractShape.
- (C) Oval: concrete class for the oval shape that extends AbstractShape.
- (C) Circle: concrete class for the circle shape that extends AbstractShape.
- (C) Square: concrete class for the square shape that extends AbstractShape.
- (C) Triangle: concrete class for the triangle shape that extends AbstractShape.
- (C) Rhombus: concrete class for the rhombus shape that extends AbstractShape.

The shape package is in charge of:
- Create all the shapes available: rectangle, oval, circle, square, triangle, and rhombus.


cs5004.utilities - encapsulates all the classes related to tools that the other packages use:
- (C) Time: concrete class for the time of occurrence of an event.
- (C) Position: concrete class for the position in the Cartesian coordinate system.
- (C) RGB: concrete class for the color in format RGB.

The utilities	package is in charge of:
- Create a Time object that includes the starting time and ending time expressed in integers.
- Create a Position object that includes an X coordinate and a Y coordinate.
- Create an RGB object that includes 3 components: red, green, and blue.


Functionality of the model:
When the AnimatorModelImpl is initialized, 3 main data structures are created:
- HashMap<String, IShape> logOfShapes: contains all the original shapes created by the model.
- HashMap<String, List<IAction>> logOfActions: contains all the actions that every shape received through the entire animation.
- List<String> chronologicalOrderOfActions: contains every action in the sequence they occurred.

Creating shapes:
The model implementation creates shapes by calling the method 'createShape'. 
1) The method checks for usual parameter constraints. 
2) The method checks if the name of the shape being created already exists.
3) The method creates the shape.
4) The method adds the shape to the logOfShapes.

Moving, scaling, and changing color of shapes:
The model implementation moves, changes the dimensions, and changes the color of shapes by calling the methods 'move', 'scale', and 'changeColor' respectively.
The methods identify a shape by its name.
1) They look for the shape in the logOfShapes by calling the private method 'getCurrentShape'.
2) They check if the new event is within the total time of occurrence of that shape by calling the private method 'checkRange'.
3) They check that the new event does not overlap with an existing action by calling the private method 'checkOverlap'.
4) They create a new action that stores the name of the shape, the current shape, the new: position, dimension, or color, and the time of execution of that action.
5) They add the action to the logOfActions of that particular shape by calling the private method 'addActionToShape'.
6) They add the action to the chronologicalOrderOfActions of all the actions so far.

Returning shapes at a particular moment in time:
The model implementation returns a list of every updated shape at a particular moment in time by calling the method 'getShapesAtTicks'.
The method identifies a particular moment in time by an integer called tick.
1) The method checks that the tick is not negative.
2) The method creates a List<IShape> frameOfShapes where it will put every updated shape found at the given tick.
3) The method looks for every created shape in logOfShapes.
4) The method creates a copy of that shape to prevent mutating.
5) The method looks for every action that the shape received at the tick and gets the most updated version of that shape.
6) The method gets the original shape if it did not receive any action.
7) The method adds the shape to frameOfShapes and returns the list.

String representation of the animation:
The model implementation offers a string representation of the entire animation for devices that cannot display the play or for visually impaired users. It does so by calling the method 'toString'.
