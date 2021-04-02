# Laboratory 6

## GeometryDrawing

This repository contains all the problems proposed for the fifth laboratory in Advanced Programming course solved by me.

# Essentials tools
You need to have Java RE or JDK >= 8 installed on your computer.

# Build and run the above programs
Launch IntelliJ IDEA and click ▶️ in the gutter and select Run 'Main()' in the popup. The IDE starts compiling your code. When the compilation is complete, the Run tool window opens at the bottom of the screen

# Tasks
Create an application with graphical user interface for creating images (layouts) containing standard or custom geometric figures: diamonds, trapezes, regular polygons, snow flakes, etc.
You may use either Swing or JavaFX.

The main specifications of the application are:
# Compulsory 
Create the following components:
- [x] The main frame of the application.
- [x] A configuration panel for introducing parameters regarding the shapes that will be drawn: the size, the number of sides, the stroke, etc.
- [x] The panel must be placed at the top part of the frame. The panel must contain at least one label and one input component for specifying the size of the component.
- [x] A canvas (drawing panel) for drawing various types of shapes. You must implement at least one shape type at your own choice. This panel must be placed in the center part of the frame.
- [x] When the users execute mouse pressed operation, a shape must be drawn at the mouse location. You must use the properties defined in the configuration panel (at least one) and generate random values for others (color, etc.).
- [x] A control panel for managing the image being created. This panel will contains the buttons: Load, Save, Reset, Exit and it will be placed at the bottom part of the frame.
- [x] Use a file chooser in order to specify the file where the image will be saved (or load).
## Output 

https://user-images.githubusercontent.com/75542257/113048360-1d2f3400-91ab-11eb-9f62-aa2d1af9308d.mp4

# Optional
- [x] Implement a retained mode drawing and add support for deleting shapes.
- [x] Add support for drawing multiple types of components. Consider creating a new panel, containing a list of available shapes.
- [x] The configuration panel must adapt according to the type of the selected shape. Implement at least two types of shapes.
- [ ] Implement free drawing and a simple shape recognition algorithm, capable of recognizing at least lines and circles.
## Output

https://user-images.githubusercontent.com/75542257/113418431-264c1b00-93ce-11eb-98f9-80d0834f6fa6.mp4

# Bonus 
- [ ] Create a simple grammar in order to specify commands for drawing geometric shapes, for example fill circle name,x,y,radius,color.
- [ ] The commands will be specified in a text area component. Important: parsing the strings using regular expressions or other "custom" methods is not accepted.
- [ ] Use ANTLR(or a similar library) to generate a parser for your grammar, in order to evaluate the syntax and the semantics of your commands.
- [ ] Implement various commands at your own choice, for example draw, fill, delete, etc.
- [ ] An additional bonus may be given for looping commands, like for, in order to perform a drawing several times.
## Output
