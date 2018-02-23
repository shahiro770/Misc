/**
 * @(#)GenRobo.java
 *
 *
 * @Shahir Chowdhury
 * @version 1.00 2015/5/23
 */

 /* This program creates a GenRobo object, a generic robo object that is used as a baseline for 
  * all other robot objects (e.g. MiniRobots and MainRobots). It contains all methods regarding
  * accessing and changing the properties of the GenRobo and drawing the GenRobo.
  * Note that the term Robo is used to not confuse readers with the Java class Robot*/

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GenRobo{
	private Image BPic,EPic,RAPic,LAPic;
	
	String type; //the category of GenRobo that this GenRobo falls under (different type GenRobo perform differently in combat
	String animtype= "Idle"; //current cycle of animation that the GenRobo is in
	
	private boolean alive=true; //flag for if the GenRobo is alive
	private boolean affinity; //flag for what side the GenRobo is on (true is on the player side, false is on the enemy side)
	
	private Rectangle hitBox; //area used to check collisions against RoboBullets
	
	private int robox;
	private int roboy;
	private int robow;
	private int roboh;
	private int hitboxl=10; //length of each side of the GenRobo's hitbox
	private int maxhp; //max health of the GenRobo
	private int hp; //current health of the GenRobo
	private int healthbarw=100; //width of displayed healthbar
   	private int healthbarh=20; //height of displayed healthbar
   	private int attpower; //attack power of the GenRobo
   	private int animframe=3; //frames inbetween an animation movement
   	
   	private ArrayList<Image> piclist; //list of sprites
   	private SpriteManager manager; //organizes and moves all sprites
   		
    public GenRobo(String type,int robox, int roboy,boolean affinity,ArrayList<Image> piclist) {
    	this.robox=robox;
    	this.roboy=roboy;
    	robow= new ImageIcon(piclist.get(0)).getIconWidth();//width of MiniRobo is determined by the size of its body sprite
    	roboh= new ImageIcon(piclist.get(0)).getIconHeight(); //height of MiniRobo is detemrined by the size of its body sprite
    	this.hitBox=new Rectangle((robox+robow/2)-hitboxl/2,(roboy+roboh/2)-hitboxl/2,hitboxl,hitboxl); //hitbox is on the centre of the robo 
    	this.affinity=affinity;
    	this.maxhp=maxhp;
    	this.hp=maxhp; //starting health is equal to max health
    	
    	if (type.equals("MainBot")){ //GenRobo type for the special "MainRobo" that acts as the flagship and target of opposing MiniRobots
    		this.type="MainBot"; 
			healthbarw=200; //enlarged healthbar to make MainRobo feel stronger
			attpower=0; //MainRobo does not attack
			maxhp=100;
			hp=maxhp;
    	}
    	else if (type.equals("ShotBot")){ //GenRobo type with medium health and medium damage output
    		this.type="ShotBot";
    		maxhp=22;
    		hp=maxhp;
    		attpower=6;
    	}
    	else if (type.equals("TankBot")){ //GenRobo type with highest health and lowest damage output
    		this.type="TankBot";
    		maxhp=30;
    		hp=maxhp;
    		attpower=5;
    	}
    	else if(type.equals("LaserBot")){ //GenRobo type with the lowest health and highest damage output
    		this.type="LaserBot";
    		maxhp=19;
    		hp=maxhp;
    		attpower=7;
    	}
    	this.piclist=piclist;
    	manager= new SpriteManager(this,piclist);
    }  
//Get and Set Methods------
    public int getx(){ 
    	return robox;
    }
    public int gety(){
    	return roboy;
    }
    public int getw(){
    	return robow;
    }
    public int geth(){
    	return roboh;
    }
    public Rectangle getRect(){ //returns the hitRect of the GenRobo
    	return hitBox;
    }
    public String gett(){ //return the type of robo the GenRobo is
    	return type;
    }
    public boolean geta(){ //return which side the GenRobo is on
    	return affinity;
    }
    public void seta(boolean a){
    	affinity=a;
    }
    public int getp(){ //return attack power of the GenRobo
    	return attpower;
    }
    public void setp(int p){ //sets the attack power of the GenRobo
    	attpower=p;
    }
    public boolean alive(){ //checks if GenRobo is alive
    	return alive;
    }
    public void die(){ //kills the GenRobo
    	alive=false;
    }
    public int gethp(){ //returns the GenRobos current health
    	return hp;
    }
    public void sethp(int h){//sets the GenRobos current health to an inputted amount;
    	hp=h;
    }
    public int getmaxhp(){ //returns the GenRobos maximum health
    	return maxhp;
    }
    public void setmaxhp(int h){//sets the GenRobos max health to an inputted amount
    	maxhp=h;
    }
    public void heal(int h){ //increases the GenRobos health by an inputted amount amount
    	hp+=h;
    }
    public ArrayList<Image>getpics(){
    	return piclist;
    }
//Attacking related methods-----
    public void takeDamage(int damage){ //decreases the GenRobo's current health
    	hp-=damage;
    	if (hp<=0){ //robo dies if health goes below 0
    		alive=false;
    	}
    } 
//Drawing methods----- 
	public void drawRobo(Graphics2D g, RoboPanel panel){ //draws the GenRobo 
    	manager.animateRobo(animframe); //animate the robo before it is drawn
    	manager.drawRobo(g, panel);
    }
}