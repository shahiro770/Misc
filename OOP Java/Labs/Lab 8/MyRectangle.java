/*
 * Shahir Chowdhury
 * 2017-09-16
 * MyRectangle.java
 *
 * This program creates a MyRectangle object. It allows for the drawing of a rectangle on screen, that can be resized and dragged 
 * around to your pleasure.
*/

import java.awt.*;
import java.awt.event.*;

public class MyRectangle {
	
	//rectangle properties
	private int tol = 3;	//tolerance in pixels for area that can be clicked around the rectangle to interact with it
	private int x;			//the x for position
	private int y;			//the y for position
	private int width;		//the rectangle width
	private int height;		//the rectangle height 
	private String action = "None";	//what is currently being done to the rectangle
	private Color color = Color.RED;

	//mouse properties
	private int prevMouseX; //the old x for mouse
	private int prevMouseY; //the old y for mouse

	public MyRectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	//prepares the rectangle for a specific action depending on the edge grabbed
	public void processMousePressed(MouseEvent e) {
		if (checkBounds(e.getX(), e.getY(), 1) == true || checkBounds(e.getX(), e.getY(), 4) == true){	//prepare for dragging
			color = Color.GREEN;		
			prevMouseX = e.getX();
			prevMouseY = e.getY();
			action = "Drag";
		}
		else if (checkBounds(e.getX(), e.getY(), 2) == true){	 //prepare for resizing using the right edge
			color = Color.BLUE;
			prevMouseX = e.getX();
			prevMouseY = e.getY();
			action = "Resize Right";
		}
		else if (checkBounds(e.getX(), e.getY(), 3) == true){	//prepare for resizing using the bottom edge
			color = Color.BLUE;
			prevMouseX = e.getX();
			prevMouseY = e.getY();
			action = "Resize Bottom";
		}
	}

	//release the rectangle, retaining any changes made
	public void processMouseReleased(MouseEvent e) {	
		action = "None";
		color = Color.RED;
	}

	//alters the rectangle depending on the action prepared for in processMousePressed
	public void processMouseDragged(MouseEvent e) {
		if (action.equals("Drag")){	//rectangle is being dragged 
			x += e.getX() - prevMouseX;
			y += e.getY() - prevMouseY;

			prevMouseX = e.getX();
			prevMouseY = e.getY();
		} 
		if (action.equals("Resize Right")){
			width += e.getX() - prevMouseX;
			prevMouseX = e.getX(); 
		}
		if (action.equals("Resize Bottom")){
			height += e.getY() - prevMouseY;
			prevMouseY = e.getY(); 	
		}
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect(x, y, width, height);
	}
	
	/* Checks if the mouse is close enough to a given edge to see if it is in realistic clicking range.
	   The side being checked is specified as an integer (1 is top, 2 is right, 3 is bottom, 4 is left) */
	public boolean checkBounds(int mouseX, int mouseY, int side){
		switch (side){
			case 1:				//top
				if ((mouseX <= x + width + tol) && (mouseX >= x - tol)){
					if ((mouseY <= y + tol) && (mouseY >= y - tol)){
						return true;
					}
				}
				break;
			case 2:				//right
				if ((mouseX <= x + width + tol) && (mouseX >= x + width - tol)){
					if ((mouseY <= y + height + tol) && (mouseY >= y - tol)){
						return true;
					}
				}
				break;
			case 3:				//bottom
				if ((mouseX <= x + width + tol) && (mouseX >= x - tol)){
					if ((mouseY <= y + height + tol) && (mouseY >= y  + height - tol)){
						return true;
					}
				}
				break;
			case 4:				//left
				if ((mouseX <= x + tol) && (mouseX >= x - tol)){
					if ((mouseY <= y + height + tol) && (mouseY >= y - tol)){
						return true;
					}
				}
				break;
		}

		return false;
	}
}