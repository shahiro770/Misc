/**
 * @(#)RoboTech.java
 *
 *
 * @Shahir Chowdhury
 * @version 1.00 2015/4/21
 */
 
/* This program creates RoboTech's displayed window, game loop and is used to run the game */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class RoboTech extends JFrame implements ActionListener{
	
	private int windoww=1080; //window width
	private int windowh=720; //window height
	
	Timer myTimer;   
	RoboPanel game;
	
    public RoboTech(){
    	super("RoboTech");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(windoww,windowh);

		myTimer = new Timer(10, this);	 // trigger every 10 ms
		this.setTitle(title());

		game = new RoboPanel(this);
		add(game);

		setResizable(false);
		setVisible(true);
    }
    public void start(){
		myTimer.start();
	}
	public void actionPerformed(ActionEvent evt){
		game.input(); //takes in all player input
		game.update(); //updates the game
		game.repaint(); //redraws the updated screen
	}
    public static void main(String[] arguments) {
		RoboTech frame = new RoboTech();		
    }
    public String title(){ //changes the title of the window to something creative
    	int chance=(int)(Math.random()*100);
    	if (chance<25){
    		return "RoboTech, March of the Orange Army";
    	}
    	else if (chance>25 && chance<50){
    		return "RoboTech, Revenge of the Blue Eyed Menace";
    	}
    	else if (chance>50 && chance<75){
    		return "RoboTech, Orange VS Blue";
    	}
    	else if (chance>75 && chance<99){
    		return "RoboTech";
    	}
    	else{ //the 1 percent
    		return "RoboTech 3, Rise of the Raymond Li";
    	}
    }
}
