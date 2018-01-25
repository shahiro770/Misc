/* Graphics and Mouse Event Handlers(Lecture 11) 2017-07-03

Graphics
--------
	A Graphics object is an object that allows a Java application to draw onto components and off-screen images
	Graphics objects encapsulate state information needed for basic Java rendering operations
	State information includes:
		The Component on which to draw
		The position at which to draw
		The color
		The font
		etc.

Coordinate Systems
--------
	When drawing objects on a screen (any object), Java uses a coordinate system where the origin [0,0] is the upper-left corner
	...of the screen area used for drawing
		The x-coordinate (horizontal) is positive and increasing to the right
		The y-coordinate (vertical) is positive and increasing down
		All coordinates are normally positive
			Can be negative if coordinates are taken with respect to the window instead of the screen
		Units and sizes are in pixels
		The drawing area is typically a JFrame or JPanel
		We'll talk about this mostly in light of JFrames
	The point (x,y) is located x pixels from the left edge of the screen and y pixels from the top
	When placing a rectangle on the screen, the location of its upper-left corner is specified
	When placing a figure other than a rectangle on the screen, Java encloses the figure in an imaginary rectangle called a bounding box
		The upper left corner of the bounding box is its position

paint() and Graphics
--------
	Almost all Swing and Swing related components and containers have a method called paint
	This method draws the component or container on screen
		It is already defined and called automatically when the figure is displayed on the screen
		However, if we want to draw geometric figures like circles and boxes, we must redefine it
		When redefining, include the following because it allows us to first draw the original object

	*/
			super.paint(g); //(paint the panel first, then whats inside by overriding)
	/*

	Every container and component that can be drawn on the screen has an associated Graphics object
		The Graphics class is an abstract class found in java.awt
	This object has the data specifying what area of the screen the component or container covers
		The Graphics object for a JFrame specifies that drawing takes place inside the borders of the JFrame object
	The object g of the class Graphics can be used as the calling object for a drawing method
		The drawing will then take place inside the area of the screen specified by g
	The method paint has a parameter g of type Graphics
		When the paint method is invoked, g is filled in by the Graphics object associated with the JFrame
			Thus, figures are drawn inside of the JFrame
	Some basics:

	*/
		import java.awt.Graphics;	//gets you graphics
		import java.awt.Color;

		//sample paint

		public void paint(Graphics g){
			super.paint(g);	//paint the panel
			g.drawOval(X_FACE, Y_FACE, FACE_DIAMETER, FACE_DIAMETER);
			g.drawLine(X_RIGHT_EYE, Y_RIGHT_EYE, X_RIGHT_EYE + WIDTH, Y_RIGHT_EYE);
		}
	/*

	Some methods in the Class Graphics

	*/
		public abstract void drawLine(int x1, int y1, int x2, int y2)	//draws a line between points (x1, y1) and (x2, y2)
		public abstract void drawRect(int x, int y, int width, int height)	//draws the outline of the specified rect
																			//(x, y) is the upper left corner
		public abstract void fillRect(int x, int y, int width, int height)	//Fills the specified rect 
																			//(x, y) is the upper left corner
		public abstract void drawOval(int x, int y, int width, int height)	
		/* Draws the outline of the specified oval with the smallest enclosing rectangle that has the specified height and width.
		   The (imagined) rectangle has its upper-left corner located at (x, y)	*/
		public abstract void fillOval(int x, int y, int width, int height)	//fills the oval specified by drawOval(x, y, width, height)
		public abstract void drawArc(int x, int y, int width, int height, int startAngle, int arcSweep)
		/* Draws part of an oval that just fits	into an imaginary rectangle described by the first four arguments. The portion of
		   the oval drawn is given by the last two arguments  */
		public abstract fillArc()(int x, int y, int width, int height, int startAngle, int arcSweep)
		/* Fills the oval specified by drawArc(x, y, width, height, startAngle, arcSweep)
	/*
	
Ovals
--------
	An oval is drawn by the method drawOval
		The arguments specify the location, width, and height of the smallest rectangle that can enclose the oval (its bounding box)

	*/ g.drawOval(100, 50, 300, 200); /*
	
	A circle is a special case of an oval in which the width and height of the rectangle are equal

	g.drawOval(xFace, yFace, faceDiam, faceDiam);


Arcs
--------
	Arcs are described by giving an oval, and then specifying a portion of it to be drawn
		The following statement draws a smile on a smiley face

		*/	g.drawArc(xMouth, yMouth, mouthWidth, mouthHeight, mouthStartAngle, mouthArcSweep); /*

		The arguments mouthWidth and mouthHeight determine the size of the bounding box
		The arguments xMouth and yHeight determine the location (top left corner)
		The last two arguments specify the portion made visible
	*/
		g.drawArc(x, y, width, height, 0 ,90)	//start at 0 degrees, sweep 90 degrees (0 to π/2)
		g.drawArc(x, y, width, height, 0 , -90)	//start at 0 degrees, sweep -90 degrees (0 to 3π/2)
	/*

Action Drawings and repaint()
--------
	The repaint method should be invoked when the graphics content of a window is changed
		The repaint method takes care of some overhead and then invokes the paint method which redraws
		Although the repaint method must be explicitly invoked, it is already defined (final method so it can't be overridden)
		The paint method, in contrast must be defined but not explicitly invoked
	Sample usage

	*/
		private class WinkAction implements actionListener{
			public void actionPerformed (ActionEvent e){
				wink = true;
				repaint();
			}
		}	//end of WinkAction inner class
	/*

More Details on Updating a GUI
--------
	With Swing, most changes to a GUI are updated automatically to become visible on the screen
		This is done by a repaint manager object
	Although the repaint manager works automatically, there are a few updates that it does not perform
		There are methods validate, repaint, and pack that handle the remaining updates
		The pack method handles window resizing
		We just talked about repaint...

The validate Method
--------
	An invocation of validate causes a container to lay out its components again
		It is essentially an update method that makes changes in the components shown on screen
		Every container class has the validate method, which has no arguments
	Many simple changes made to a Swing GUI happen automatically
	 	Others require an invocation of validate or some other such update method
			When in doubt, validate (it won’t cause any harm if you invoke it unnecessarily)

Using Colours
--------
	Using the method setColor inside the paint method is similar to drawing with a pen that can change colours
		The method setColor will change the color of the pen used for the drawing
		The colour specified can be changed later on with another invocation of setColor so that a single drawing can

	*/ g.setColor(Color.BLUE); /*

	Standard colours in the class Color are already defined (yes, you have to spell it like an American)
	The Color class can also be used to define any other colours you would like
		Uses the RGB colour system (red-green-blue) 
			i.e. different amounts of red, green, and blue light are used to produce any colour
	Got lots of standard colours (they all static members) *STATIC IS BACK FOR MIDTERM 2*
		BLACK, BLUE, CYAN, etc...
	Integer or floats may be used when specifying the amount of red, green, and blue in a colour
		Integers are in the range of 0 - 255 inclusive
		Floats are in the range of 0 - 1, inclusive
		No combinations of the two (either int int int or float float float)
	Pitfall: Using doubles to Define a Color
		Constructors for the Color class only accept args of type int or float
			Without a cast, numbers like 200.0/255, 0.5, or 0.0 are considered of type double, not float
			Do not forget to use a type cast when intending to use float numbers
			Note that these numbers should be replaced by defined constants in production code, unless they are meant to be dynamic
	
	*/ public static final float RED_VALUE = (float)0.5; /*

	A lot of objects in Java have copy constructors, Color is one of them
		Can pass in the Color object itself, rather than the individual values
	Some constructors and methods

	*/
		public Color(int r, int g, int b) //constructor 
		public Color(float r, float g, float b) //constructor
		public int getRed()	//returns the integer red component of the calling object (0 - 255)
		public int getGreen()	//returns the integer green component of the calling object (0 - 255)
		public int getBlue()	//returns the integer blue component of the calling object (0 - 255)
		public Color brighter()	//returns a brighter version of the calling object
		public Color darker()	//returns a darker version of the calling object
		public boolean equals()	//returns true if c is equal to the calling object color, otherwise false
	/*

drawString
--------
	The method drawString is similar to the other drawing methods we have seen in Graphics
		However, it displays text instead of a drawing
		If no font is specified, a default font is used

		*/ 
			import java.awt.Font;

			private Font fontObject = new Font("Sanserif", Font.PLAIN, POINT_SIZE);

			//somewhere in paint

			g.setFont(fontObject)
			g.drawString(theText, xStart, yStart); 
		/*

	Keep stuff self contained inside the constructor for setting values

Fonts
--------
	A font is an object of the Font class (in Java)
		The Font class is found in Java.awt
	The constructor for the Font class creates a font in a given style and size

	*/ Font fontObject = new Font("SansSerif", Font.PLAIN, fontSize); /*

	A program can set the font for the drawString method within the paint method
	Any font currently available on a system can be used in Java
		However, Java guarantees that at least three fonts will be available:
			Monospaced, SansSerif, and Serif
	Serifs are small lines that finish off the ends of the lines in letters
	A serif font will always have serifs
	Sans means without, therefore SansSerif does not have serifs
		Monospaced means that all characters are of equal width, which can be useful (kinda wide looking)
	Fonts can be given style modifiers such as bold or italic
		Multiple styles can be specified by connected them with the | operator (called the bitwise OR)

	*/ new Font ("Serif", Font.BOLD | Font.ITALIC, pointSize); /*

	The size of a font is called its point size
		Character sizes are specified in units called points
		One point is 1/72 of an inch
	Some methods and constants

	*/
		public Font (String fontName, int styleModifications, int size)	 //constructor
		public abstract void setFont(Font fontObject)	//located in Graphics; set the current font of the calling Graphics object

		//Constants
		Font.BOLD 	//Specifies bold style
		Font.ITALIC //Specifies Italic style
		Font.PLAIN 	//Specifies plain style - that is not bold or italic

		//Names of Fonts (guaranteed by Java, but you can use anything else your system has or downloaded)
		"Monospaced"
		"SansSerif"
		"Serif"
	/*

Graphics, Mouse, and Mouse Events
--------
	Classes in the AWT
		Color, Font Graphics, and Mouse Events and mouse motion events
	When you import stuff, packages provide a lot of overhead (Java is not a high performance language)
	There are a number of mouse events
	These mouse events can be categorized as:
		Mouse events
		Mouse motion events
	The corresponding listeners for these events are interfaces: 
		MouseListener
		MouseMotionListener
	Thus, we have the following mouse events that need handlers:
		mousePressed 	(button down)
		mouseClicked 	(button up and down in one movement)
		mouseReleased 	(button up)
		mouseEntered	(entering a component on the screen)
		mouseExited		(leaving a component on screen)
	The corresponding listeners for MouseMotionListener
		mouseDragged	(button down, hold and move)
		mouseMoved		(moving the mouse)
	The method headers for mouse events are:
	
	*/
		public void mousePressed(MouseEvent e)
		public void mouseReleased(MouseEvent e)
		public void mouseEntered(MouseEvent e)
		public void mouseExited(MouseEvent e)
		public void mouseClicked(MouseEvent e)
	/*
	The method headers for mouse motion events are:

	*/
		public void mouseDragged(MouseEvent e)
		public void mouseMoved(MouseEvent e)
	/*

	Again, you must have these methods in your program because interfaces (feel free to leave them empty)
	You get negative coordinates if you drag outside of the area you are detecting if its done with respect to an object on the window
	If you want different components to handle an event differently, get anonymous class objects to handle the events
		Its just an entirely new class with no overhead
	
Bonus
--------
	Look up a normal frame

