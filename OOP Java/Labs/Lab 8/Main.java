/*
 * Shahir Chowdhury
 * 2017-09-16
 * Main.java
 *
 * This program provides the user with a window containing a rectangle that they can interact with.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

	private int rectangleWidth;
	private int rectangleHeight;

	MyRectangle rectangle = new MyRectangle(50, 50, 100, 100);

	public Main() {
		super("Resizable rectangle");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){
				rectangle.processMousePressed(e);
				repaint();
			}
			public void mouseReleased(MouseEvent e){
				rectangle.processMouseReleased(e);
				repaint();
			}

			public void mouseEntered(MouseEvent e){}

			public void mouseExited(MouseEvent e){}

			public void mouseClicked(MouseEvent e){}
		});

		addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e){
				rectangle.processMouseDragged(e);
				repaint();
			}
			public void mouseMoved(MouseEvent e){}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		rectangle.draw(g);
	}
}