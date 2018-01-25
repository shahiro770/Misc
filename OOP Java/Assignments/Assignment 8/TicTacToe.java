/*
 * Shahir Chowdhury
 * 2017-09-16
 * TicTacToe.java
 *
 * This program creates a TicTacToe object. It allows for a simple game of TicTacToe to played against a friend in person by taking
 * turns making moves. The game does not check for a winner, so its up to the players to claim victory and play fairly.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe extends JFrame {
	private String turn = "X"; 											//string to keep track of turn
	private int realBoard[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};		//real board showing the state of the game
	private int tempBoard[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};		//temporary board that holds the player's supposed next move
																		//for both tempBoard and realBoard, 0 is empty, 1 is X, 2 is O

	private int xTable[] = {46, 79, 113};   // xTable[i] gives the displacement for row number i
	private int yTable[] = {113, 146, 180};	// yTable[j] gives the displacement for column number j


	public TicTacToe(){
		super("TicTacToe Board");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){		//holding the mouse button down will queue up the move in the tempBoard
				planDecision(e.getX(), e.getY(), tempBoard);
				repaint();
			}
			public void mouseReleased(MouseEvent e){	//release the mouse button to finalize the move
				finalizeDecision();
				repaint();
			}

			public void mouseEntered(MouseEvent e){}

			public void mouseExited(MouseEvent e){}

			public void mouseClicked(MouseEvent e){}
		});

		addMouseMotionListener(new MouseMotionListener(){
			//holding the mouse button down will queue up the move in the tempBoard, updating the move as the mouse is dragged
			public void mouseDragged(MouseEvent e){	
				planDecision(e.getX(), e.getY(), tempBoard);
				repaint();
			}
			public void mouseMoved(MouseEvent e){}
		});
		
		setVisible(true);
	}

	public void paint(Graphics g){
		int red = 255, green = 0, blue = 0;
		Font my_font;
		my_font = new Font( "Serif", Font.BOLD, 18 );
		super.paint(g); // Note use of super!!
		g.setColor(new Color(red, green, blue));
		g.drawLine(40, 140, 140, 140);
		g.drawLine(40, 173, 140, 173);
		g.drawLine(73, 107, 73, 207);
		g.drawLine(107, 107, 107, 207);
		g.setColor(new Color(0, 0, 0));
		g.fillRect(20, 87, 140, 5);
		g.fillRect(20, 87, 5, 140);
		g.fillRect(20, 222, 140, 5);
		g.fillRect(155, 87, 5, 140);
		g.setColor(Color.blue);
		g.setFont(my_font);
		g.drawString("My Tic Tac Toe Board", 20, 280);
		displayMoves(g); 
	}

	//draws the current state of the board, including the positions of temporary moves
	private void displayMoves(Graphics g){
		for (int i = 0;i < 3;i++){
			for (int j = 0;j < 3;j++){
				if (realBoard[i][j] == 1){
					drawX(g, i, j);
				}
				else if (realBoard[i][j] == 2){
					drawCircle(g, i, j);
				}
				if (tempBoard[i][j] == 1 && realBoard[i][j] == 0){
					drawX(g, i, j);
				}
				else if (tempBoard[i][j] == 2 && realBoard[i][j] == 0){
					drawCircle(g, i, j);
				}
			}
		}
	}
	
	//draws an X in a given row and column on the board
	private void drawX(Graphics g, int rowNo, int columnNo){
		g.drawLine(xTable[columnNo], yTable[rowNo], xTable[columnNo] + 10, yTable[rowNo] + 20);
		g.drawLine(xTable[columnNo], yTable[rowNo] + 20, xTable[columnNo] + 10, yTable[rowNo]);
	}

	//draws an O in a given row and column on the board
	private void drawCircle(Graphics g, int rowNo, int columnNo){
		g.drawOval(xTable[columnNo], yTable[rowNo], 20, 20);
	}

	//attempts to put your next move into a given board, not allowing it if the spot is taken
	private void planDecision(int mouseX, int mouseY, int [][] boardType){
		if (mouseX >= 46 && mouseX < 79){						//column 1
			if (mouseY >= 113 && mouseY < 146){					//row 1
				if (turn.equals("X") && boardType[0][0] == 0){	
					clearTempBoard();
					boardType[0][0] = 1;
				}
				if (turn.equals("O") && boardType[0][0] == 0){
					clearTempBoard();
					boardType[0][0] = 2;
				}
			}
			if (mouseY >= 146 && mouseY < 180){					//row 2
				if (turn.equals("X") && boardType[1][0] == 0){
					clearTempBoard();
					boardType[1][0] = 1;
				}
				if (turn.equals("O") && boardType[1][0] == 0){
					clearTempBoard();
					boardType[1][0] = 2;
				}
			}
			if (mouseY >= 180){									//row 3
				if (turn.equals("X") && boardType[2][0] == 0){
					clearTempBoard();
					boardType[2][0] = 1;
				}
				if (turn.equals("O") && boardType[2][0] == 0){
					clearTempBoard();
					boardType[2][0] = 2;
				}
			}
		}
		if (mouseX >= 79 && mouseX < 113){						//column 2
			if (mouseY >= 113 && mouseY < 146){					//row 1
				if (turn.equals("X") && boardType[0][1] == 0){
					clearTempBoard();
					boardType[0][1] = 1;
				}
				if (turn.equals("O") && boardType[0][1] == 0){
					clearTempBoard();
					boardType[0][1] = 2;
				}
			}
			if (mouseY >= 146 && mouseY < 180){					//row 2
				if (turn.equals("X") && boardType[1][1] == 0){
					clearTempBoard();
					boardType[1][1] = 1;
				}
				if (turn.equals("O") && boardType[1][1] == 0){
					clearTempBoard();
					boardType[1][1] = 2;
				}
			}
			if (mouseY >= 180){									//row 3
				if (turn.equals("X") && boardType[2][1] == 0){
					clearTempBoard();
					boardType[2][1] = 1;
				}
				if (turn.equals("O") && boardType[2][1] == 0){
					clearTempBoard();
					boardType[2][1] = 2;
				}
			}
		}
		if (mouseX >= 113){										//column 3
			if (mouseY >= 113 && mouseY < 146){					//row 1
				if (turn.equals("X") && boardType[0][2] == 0){
					clearTempBoard();
					boardType[0][2] = 1;
				}
				if (turn.equals("O") && boardType[0][2] == 0){
					clearTempBoard();
					boardType[0][2] = 2;
				}
			}
			if (mouseY >= 146 && mouseY < 180){					//row 2
				if (turn.equals("X") && boardType[1][2] == 0){
					clearTempBoard();
					boardType[1][2] = 1;
				}
				if (turn.equals("O") && boardType[1][2] == 0){
					clearTempBoard();
					boardType[1][2] = 2;
				}
			}
			if (mouseY >= 180){									//row 3
				if (turn.equals("X") && boardType[2][2] == 0){
					clearTempBoard();
					boardType[2][2] = 1;
				}
				if (turn.equals("O") && boardType[2][2] == 0){
					clearTempBoard();
					boardType[2][2] = 2;
				}
			}
		}
	}

	//adds a move from the temporary board to the real board
	public void finalizeDecision(){
		for (int i = 0;i < 3;i++){
			for (int j = 0;j < 3;j++){
				if (realBoard[i][j] == 0 && tempBoard[i][j] != 0){	//do a final validity check
					realBoard[i][j] = tempBoard[i][j];
					turnSwitch();
				}
			}
		}
	}

	//clears the temporary board of all X's and O's
	public void clearTempBoard(){
		for (int i = 0;i < 3;i++){
			for (int j = 0;j < 3;j++){
				tempBoard[i][j] = 0;
			}
		}
	}

	//switches which player is currently making a move
	public void turnSwitch(){
		if (turn.equals("X")){
			turn = "O";
		}
		else if (turn.equals("O")){
			turn = "X";
		}
	}

	public static void main(String args[]){
		new TicTacToe();
	}
}