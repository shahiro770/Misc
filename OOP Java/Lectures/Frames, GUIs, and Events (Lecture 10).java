/* Frames, GUIs, and Events(Lecture 10) 2017-06-28

The cool thing about this lecture is that whether you're programming Java GUI apps, Windows apps, Android apps, or IOS apps...
	General concepts hold but the classes you will use will be different
Pay attention if you want to program for mobile

Intro to Swing
--------
	The Java AWT (Abstract Window Toolkit) package is the original Java package for doing GUIS
	A GUI is a windowing system that the user can interact with
	The Swing package is an improved version of the AWT, but does not completely replace AWT
		Some AWT classes are replaced by Swing classes, but not all of them
	Swing GUIs are designed using a form of OOP programming known as Event-driven Programming
	
Structure of Frame-based Applications
--------
	A Frame object is a window with a title bar that provides the basic attributes of a window 
		Minimize, maximize, close, size, resizing, positioning, and more (ITS JUST AN OBJECT)
	You can think of a Frame object as a picture frame, on which other objects must be mounted
	GUIs are like the pictures that must be mounted on this picture frame
		Buttons, labels, text fields, etc.
	Various methods for JFrames

	*/	
		//import javax.swing.*;
		public JFrame()	//constructor
		public JFrame(String title)	//constructor with title given
		public void setDefaultCloseOperation(int operation)	//sets what should happen when user clicks close button
	/*
	
	The operation value can be one of the following:
		JFrame.DO_NOTHING_ON_CLOSE: Do nothing; JFrame will do nothing, but any registered window listeners are invoked
		JFrame.HIDE_ON_CLOSE: Invoke any registered window listeners, then hide
		JFrame.DISPOSE_ON_CLOSE: Invoke any registered window listeners, then hide and "dispose" of the frame
			Dispose eliminates the window, but not the program
		JFrame.EXIT_ON_CLOSE: Exit the application using the System exit method (Don't use this for applets)
		If not action is specified using the method, default is JFrame.HIDE_ON_CLOSE
		Throws an IllegalArgumentException if the operation value given is none of the above
	
	*/
		public void setSize(int width, int height)	//Sets the width and height, using pixels as the unit
		public void setTitle(String title)	//rename the title
		public void add (Component componentAdded)	//adds a component to the JFrame
		public void setLayout (LayoutManager manager)	//determines the default positioning for GUI elements in the frame
	/*	

	Layouts also manage things if the frame is resized
		Grid type managers, flow managers, and many more that always add things in a specified way
	
	*/
		public void setJMenuBar(JMenuBar menubar)	//Sets the menubar for the calling frame
		public void dispose()	//release memory for the JFrame and all subcomponents
								//does not end program if there are items left (items that are not the JFrame or its subcomponents)
	/*
	
	Any class that is a descendant of Container is considered a Container as well
		The Container class is found in java.awt, not in the Swing library
	Any object that belongs to a class derived from Container can have components added to it
	The class JFrame is a descendant of Container
		Therefore JFrame and any of its descendants can serve as containers, and thus have components added to them
	Any subclass of the class JComponent is called a component class
		Any JComponent object or component can be added to any container class object
		We will look at a few subclasses of JComponent
			C# and Android have tons of these in their respective packages(little sliders, etc.)

Graphical User Interfaces (GUIs)
--------
	Almost every GUI built using Swing classes will be made up of three kinds of objects:
		1) The container itself, probably a panel or window-like object
		2) The components added to the container, such as labels, buttons, and text fields
		3) A layout manager to position the components inside the container
	When you think of GUIs, think of what you interface with as a user of a computer 
		Surfing the web, using your phone, playing a video game, etc.
	We will be looking at 4 components: JLabels, JTextFields, JTextAreas, and JButtons
	1) JLabels are used to display messages
		For our purposes, we need a GUI that can display a message
		A label is any object of the class JLabel
			You can use labels to add text to JFrames
			The text for the label is given as an argument when the JLabel object is created
			The label can then be added to a JFrame

	*/
			JLabel greeting = new JLabel(“Hello”);
			add(greeting); //must be written in a container with an add method
	/*
				
	2) JTextField for single line 2-way communication
		Objects of the JTextField class are used for two-way communication
		Some methods from JTextComponent (JTextField and JTextArea both inherit methods from this)

	*/
		public void setBackground(color theColor)	//sets the background colour of this text component (rip Canadians)
		public boolean isEditable()					//returns true if the user can write in this text component, false otherwise
		public void setEditable(boolean argument)	/* if argument is true, user is allowed to write in this text component and 
													   vice versa; false does not allow user to write in this text component */
		public String getText()						//returns the text that is displayed by this text component
		public void setText(String text)			//sets the text displayed to whatever is given as an argument
	/*

		The constructor takes an integer n, where n is the number of characters to display
		setText(String text) defines the String to be stored in the object, and n characters from this String object are shown
		getText() returns the object of the String class saved in the object (regardless of what is displayed)
		A text field is an object of the class JTextField
			It is displayed as a field that allows the user to enter a single line of text
	
	*/
			private JTextField name;
			//some lines later
			name = new JTextField(NUMBER_OF_CHAR);	//At least NUMBER_OF_CHAR characters can be visible
	/*		

		There is also a constructor with one additional String parameter for displaying an initial String in the text field
	
	*/
			private JTextField  name;
			name = new JTextField(“Enter name here.”, 30);	//immediately prompt the user to type something in
	/*

		A swing GUI can read the text in a text field using the getText method

	*/
			String inputString = name.getText();
	/*
		
	The method setText can be used to display a new text string in a text field

	*/
			name.setText("This is some output")	
			name.setText(inputString)	//using the inputString obtained above from getText()
	/*
	
	3) JTextArea used for multi-line text display
		A text area is an object of the class JTextArea
			It is the same as a text field, except that it allows multiple lines of input/output
		JTextArea constructor accepts minimum number of lines and minimum number of characters per line that are guaranteed visible

	*/
			JTextArea text = new JTextArea(5, 20);
	/*

		Another constructor has one additional String parameter for the String initially displayed in the text area

	*/
			JTextArea text = new JTextArea(“Enter\ntext.”, 5, 20);
	/*

		The line-wrapping policy for a JTextArea can be set using the method setLineWrap(boolean wrap)
			If wrap is true then any additional characters at the end of the line will appear on the following line of the text area
			If wrap is false, the extra characters will remain on the same line and will not be visible

	*/
			text.setLineWrap(true);
	/*

		A JTextField or JTextArea can be set so that it cannot be changed by the user

	*/
			text.setEditable(false);	//can only be edited through code
			text.setEditable(true);		//user can edit in frame (default)
	/*

		The number of characters per line for a JTF or a JTA object is the number of "em" spaces
		An "em" space is the space needed to hold one upper case 'M', because it is the widest letter in the alphabet
			A line specified to hold 20 'M's will be able to hold 20 or more characters
				Exactly 20 if it is all capital M's
				Can fit in more characters if the characters are narrow (all '1's)
	4) JButton 
		A button object is created from the class JButton and can be added to a JFrame
			Buttons allow users to trigger pieces of code
			The argument to the JButton constructor is the string that appears on the button when it is displayed
	
	*/
			JButton endButton = new JButton(“Click to close the program.”);
	/*	
	
				Everything else is still an event (deleting a text field)

How to Show GUI Objects:
--------
	Create the object
	Attach it to the frame

	But where do we attach the object?
		Decided by layout manager
		The simplest layout manager is a flow layout manager (we'll only talk about this one)
	Multiple components can be added to the content pane of a JFrame using the add(Component someComponent) method
		However, the add method does not specify how these components are to be arranged
	To describe how multiple components are to be arranged, we use a layout manager
		There are several layout manager classes, such as BorderLayout, FlowLayout, and GridLayout
	FlowLayout manager is the simplest
		It arranges copmonents one after another, left to right and top to bottom
		Components are arranged in the order in which they are added
	Since a location is not specific, the add method has only one argument when you use a flow layout manager
		Just the component to be added

	*/	
		//inside of a class extending JFrame
		setLayout(new FlowLayout());
		add(label1);
	/*

	Flow Layout Manager
		FlowLayout works by placing GUI objects left to right and top to bottom 
			Top left then moves to right, when the object can't fit on the same row, moves down starting at left again, repeat process
			Resize the window, the order the objects are added in will remain, meaning positions will be reassigned
	General form of a frame-based program:

	*/
		import javax.swing.*;
		import java.awt.*;
		import java.awt.event.*;

		public class appName extends JFrame
		implements interfaceName // Optional – not needed now
		{
		//Variables needed for your application
		//Constructor method
		//other methods as needed
		//methods needed for the interface // not needed now
		//main method
		}
	/*

	Example Class

	*/
		import java.awt.*; // import the java.awt package
		import javax.swing.*;
		public class MyFirstFrame extends JFrame{
			JLabel myQuestion, yourResponse;
			public MyFirstFrame(){
				super("An example of a Frame");
				//Define the label on the titlebar
				setLayout( new FlowLayout());
				// Define what layout manager
				// will be used
				JLabel myQuestion = new JLabel("How are you?");
				// Create a GUI object
				// My mental image - get hold of a picture 
				add(myQuestion); //Add it to the frame
				//Mount the picture in the next available place 
				 JLabel yourResponse = new JLabel("I am fine");
				add(yourResponse);
			}
		}
	/*

	Example Main

	*/
		public static void main(String args[]) {
			MyFirstFrame aFrame = new MyFirstFrame(); 	aFrame.setSize(250, 100);
			aFrame.setVisible(true);
		}
		// Define a variable of the class I just defined
		// Create an object of this class
		// Define the size of the frame to be displayed
		// Display the frame
	/*
	
	Note that languages like c# will hang if an error occurs, Java will just give you an error
	With one element, it will try to line it up in the cent
	Use panels to make use of multiple layouts on a frame (panels are both containers and components)

Events and Event Handling *KNOW THE DEFINITIONS OF EVENT, LISTENER, AND HANDLERS*
--------
	An event is something that happens that our program should be aware of
		Pressing the carriage return
		Pressing a button
		Clicking or dragging the mouse
		Change of text in a text field
	Event-Driven Programming
		Is a style of programming that uses a signal-and-response approach
		An event is an object that acts as a signal to another object known as a listener
		The sending of an event is called firing
			The object that fires the event is often a GUI component, such as a button that has been clicked
	Event Listeners
		A listener object performs some action in response to an event
			A given component may have any number of listeners attached to it
			Each listener may respond to a different kinds of event, or multiple listeners may respond to the same event
	Event Handlers
		A listener object has methods that specify what will happen when events of various kinds are received by it
			These methods are called event handlers
			They determine how an object will handle an occurence of some event
	The programmer using the listener object will define or redefine these event-handler methods
	
	component -> event -> listener

	Event-driven programming is very different from what we've done until now
		So far programs have consisted of a list of statements executed in order
		Whether a statement was executed depended only on the logic of the program
			For instance, if the statement was in a for loop or in a method or in an if/else statement
	In event-driven programming, certain objects are created to fire events which listener objects listen for and respond to
		Listener objects respond to the firing of an event through their event handler methods
	The next thing that happens depends on the next event
	In particular, methods are defined that will never be explicitly invoked within the code
		Instead they are invoked automatically when the listener hears it
	How this works:
		Clicking a button fires an event
		The event object is "sent" to another object called a listener
			This means that a method in the listener object is invoked automatically
			Further, it is invoked with the event object as its argument
	In order to set this up, a GUI program needs to do two things:
		It must specify for each button, what objects are its listeners (i.e. it must register the listeners)
		It must define the methods that will be invoked when the event is sent to the listener
	Inputting and outputting numbers
		When attempting to input numbers from any Swing GUI, input text must be converted to numbers
			If the user inputted the number "42" in a JTextField, it recieves it as the string "42"
				Usually it must be converted to an integer
		Same thing when you want to output an integer
			You must convert it to a String
				“” + 42
	Action Listeners and Action Events
		We've seen this interface before, and it is already defined in Java:
	
	*/
		interface ActionListener{
			public void actionPerformed(ActionEvent e);
		}
	/*
	
	Any concrete class C implementing ActionListener must include the method actionPerformed 
		With exactly the specified signature and return type
	Implementing this interface ensures that an object has a means of handling the event
	An update to the frame model

	*/
		public class MySecondFrame {
		// GUI objects we need 
		// Constructor 
		// event handler 
		// main method which will display the JFrame object
		}
	/*

	Another problem:
		Same as previous except now we use a different object to handle events in the button instead of sharing JFrame's action handler
			Why do this?
				If the same event handler deals with events occurring from different sources, its handler can become extremely complex
					Solution?
						Let different objects deal with different events
						The task for each event handler because simpler
							This still isn't the best solution
								We'd have to make several classes for each listener

Inner and Anonymous Classes Revisited
---------
	We spoke about inner classes and anonymous classes
		One of their really nice uses is for GUI applications, creating ActionListeners within the class where the event is fired
			Keeps everything together and allows you to easily see which events need handling
	Again, why use a separate class for different events?
		Because we really don't want a big monolithic if/else construct in our listener, they're ugly and not maintainable
		In this version, code for the inner class is simple, it can see the JTextfield because it is an inner class
	Next we will eliminate any need for a named class altogther
		How?
			Since the class has a very limited role to play (the role of a listener) we can leverage anonymous classes
	Strategy
		Extend the built-in adapter interface called ActionListener by defining just the method you need, actionPerformed
		Similar adapter classes and interfaces are available for handling other events
	In event handling, of the type we want to achieve in this program,... 
		We don't need to care what is the class of the object actually handling the event
	This is a simple method to specify only what is to be done to handle the type of event of interest
	The adapter class (the inner anonmyous class) we define originally has empty bodies (no definition)... 
		For all methods required in the interface
			We fill these in as we please
	ActionEvent is not a good exmpmle to illustrate the advantage, so lets look at something a bit more specific

MouseListener
---------
	The MouseListener interface has 5 methods, one for each of the following events:
		Mouse pressed (button down), mouse released (button up), mouse clicked (button up and down in one movement), 
		mouse entered (entering a component on the screen), mouse exited (leaving a component on screen)
	Most of the time the user is interested in a few of these events, not necessarily all of them
	Using adapter classes, you only specify what you are interested in
		Instead of listening for all potential events that can be raised by an object
			This allows us to specify separate listeners for particular events raised by an object that fires a multitude of events
			Alternatively, we have a huge if/else or switch statement that determines how to handle each separate event
				Not maintainable








*/