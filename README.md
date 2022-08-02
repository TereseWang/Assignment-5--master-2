# Simple Animator (Programmed in Java)
### Introduction:
A simple animation player by reading a list of instructions from an imported text file to output a svg file playing the desired animation. Examples are given in resources folders. 

### Model Design:
We first had defined some basic shapes such as oval and rectangle that extends an abstract shape
class that implements a shape interface. Each shape has fields for position, color and its size
width and height. Beside the fields, each shape has the functionality of changeColor which changes
the color of the shape, changeSize which changes the width and height of the shape and changePosn
that changes the position of the shape. We also had getter functions for copying fields of the
shape and some basic functionality such as toString and hashCode. To better implement the shape
class we defined Posn class representing the position and Color class representing the RGB color.
For Posn class, besides the x, y value fields, we also defined some functionality such as getter
methods, toString and hashCode. For color class, we did basically the same as Posn, which we had
the fields of R, G, and B and also functionality of getter methods, toString and hashCode. For each
shape class defined and color and posn class we all had sort of copy methods which are either
public copy function or a copy constructor to avoid the problem of copy reference.

For Animation part we first defined a motion class which has fields of beginning time, ending time,
beginning shape and ending shape. We had an animation interface and a SimpleAnimation that
implements that Animation interface. For the animation part, we had fields of a map linked list
which each entry in the map has a string key for the name and a list of motions for playing the
actual animation. We had functionality such as adding motion and deleting motion that add or delete
motion to a specific motion set, and declare shape which add an empty map with the given string
key and an empty list of motions. We also support functionality such as animationDescriptor that
returns the whole state as the string and also condition check methods that makes sure each
motion is added with no time gap between each motion. Similar to the shape class, we also have
copy methods or constructor for the motion or simplemotion class to avoid copy of the reference.
For both simplemotion and motion class, we defined getter class and some basic functionality of
toString and hashCode(). For Animation model, we also added methods such as getSequence that helps
us to get the copied list of motion in a specific shape. For each class we fully test our public
methods.


### View Design and Model Revision:
- Model Revision:
    In order to better support the visual part we implement a nested class Build that helps us to build up the animation to be used in the main class for displaying the animation and such. This build class is used in the SimpleAnimation constructor. For the build class we add methods that support functionality of returning the canvas size that preset by the user. In the model class we add a new fields HashMap<String, Shape> so that we can store the information for each shape and its type so that we know when and which shape will appear on the canavas. To adjust with the newly added build class we had changed several signatures of the methods in model.
  We also change the condition for constructing a motion, which previously we do not allow end time to be less than equal to the start time, but sine the text file provided as an example allows end time to be equal to the start time, we revise that so that end time now can be equal to the start time.

- View interface:
  For inheritance reason we had a view interface which had functionality of refresh that refresh
  the view and display methods which display the model as desired.

- Textual view:
  For all textual view we input an animation model and outputstream to output the desired output and also to helps to keep tracks of what will be added to the output. For display methods that inherited from the view interface, we just add each string values to the system.out and print the result.

- Visual view:
   For visual view, what we did is to input a model class and a tick per second to indicate the speed of the animation. To display the animation, what we did is to split each motion that had time span more than one second into a list of one second time span motion and draw each shape by second. For clearity, we implement a visual panel that do the functionality of spliting motions and also paint the shape on to the canvas. Besides, in order for the shape to be stay on the canvas after it finished its action we filled the motion list, by adding the motions of same endshape and startshape until the end of the entire motion. Besides, to makes the animation runs more smoothly we also added the tweening methods that get the motion state for every second, by using the function provided on the homework intruction. This methods also helps me to split each motion into a list of motions.

- SVG view:
   For SVG view, we had the model input, the outputstreamWriter and also the tick for the speed. we add each motion to the canavas with its string output. For each motion we looked at whether if it changes position, or changes the color, or is a motion of change shape size and paint each different shape onto the canavas. We also set up the speed to make sure that the motion is displayed with a given rate of speed.

- Controller:
   To make sure that model and view does not quite depend on each other, we also add controller to be used in the main class that are used to display the animation. In the controller class, we execute the display method. We also added a view creator in order to read the command input by the user to that we know exactly which model or view should we display.

- Main class: To fully execute the animation, we created a main class called animation player that input the user argument and we split the argument into small set so that we know which model to be used and call the view creator class to create the desired view and also set up the desired speed rate.



### Model, View, Controller revision, Support functionality of modify the animation.
To makes each class more seprate from each other, we want to add abstract class to the model,
so that we can add more functionalities by writing less code.
Besides, in order to add new models and allows the view to run the new model class, we also changed
each view class, which makes them input a Animation model rather than specifically a SimpleAnimation
model.

- Revision of textual view:
- Revision of visual view:
- Revision of SVG view:
- Revision of controller:
- Revision of Animation interface:
    change the signature of changeColor(), changeSize(), and ChangePosn();
    delete method getAnimateDescription because similar process handled in textual.
- Revision of SimpleAnimation model:

- Add EditorView:
  We implement an editor view class that did the similar thing with visual panel,except that
  it has editing features that stop, start, restart, resume, increase speed and decrease speed
  of the animation. We ensured that stop, and resume cannot be activated if the start
  has not be activated. We also ensured that resume can only be activated if the stop is activated
  before. Increase speed and decrease speed button will increase or decrease speed by 10 tick
  per second each time user click the button and the new speed will display onto the screen, and
  restart will restart the animation with the new speed. Furthermore, we also allows features of
  disable and enable looping button, which the button will display "enable" when the loop is
  actually disabled, and vise versa. When the user clicked enable, the animation will rerun with
  the loop allowed, meaning animation will play again from the start. Moreover when user clicked
  enable, the button text will switched to disable and vise versa. Rather than using mouse listener
  or key listener, we believed that button is more user friendly and easy to use.

