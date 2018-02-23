/**
 * @(#)RoboBullet.java
 *
 *
 * @Shahir Chowdhury
 * @version 1.00 2015/5/11
 */
 
 /* This program creates a RoboBullet object, a moving projectile that will deal damage 
  * to its target once it collides with them. This program contains all methods regarding the movement and collision
  *	detection of the RoboBullet and can work for MiniRobos on the player`or enemy side. */
 
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoboBullet {
	private Image bulletPic;
	
	private boolean exploded=false; //flag for if the bullet has not reached its target yet
	
	private double bulletx; //x and y are doubles to allow movement on more precise slopes
	private double bullety;
	private double movex; //x velocity
	private double movey; //y velocity
	
	private Rectangle bulletRect; //hitbox
	
	private int bulletw;
	private int bulleth;
	private int attpower;//attack power of the bullet
	private int speed; //speed at which bullet moves
		
	private MiniRobo shooter; //bullets can only be shot by MiniRobo
	private GenRobo target; 
		
    public RoboBullet(MiniRobo shooter, GenRobo target,int speed,Image bulletPic){
  		this.bulletPic=bulletPic;
    	this.bulletx=shooter.getx()+shooter.getw()/2;
    	this.bullety=shooter.gety()+shooter.geth()/2;
    	this.bulletw=new ImageIcon(bulletPic).getIconWidth(); //width of and height are determined by sprite size
    	this.bulleth=new ImageIcon(bulletPic).getIconHeight();
    	if (shooter.gett().equals("ShotBot")){ //bullet power changes depending on the type of the shooting robo
    		this.attpower=shooter.getbp(); //power is equal to the power of ShotBot's bullet power, not total attack power
    	}
    	else if (shooter.gett().equals("TankBot")){ //TankBot bullet power is equal to its total attack power
    		this.attpower=shooter.getp();
    	}
    	bulletRect=new Rectangle((int)bulletx,(int)bullety, bulletw, bulleth);
    	this.speed=speed;
    	this.target=target;
    							
		int tx=target.getx()+target.getw()/2; //gets the centre of the target (x value)
		int ty=target.gety()+target.geth()/2; //gets the centre of the target (y value)
		int distx= Math.abs((shooter.getx()+shooter.getw()/2)-tx); //dx
		int disty= Math.abs((shooter.gety()+shooter.geth()/2)-ty); //dy
		double angle=Math.atan2(disty,distx); //tan inverse to get the angle
		double hyp=(double)speed; //a similar triangle is created based on the speed value given
		this.movex= (hyp*Math.sin(Math.PI/2-angle)); //similar triangle's hypoteneuse determines movement values
		this.movey= (hyp*Math.sin(angle)); 
			
		if (bullety+bulleth/2>target.gety()+target.geth()/2){//if target is positioned higher than shooter, vertkcally reflect slope
			movey*=-1;
		}
		if (shooter.geta()==false){// move in opposite direction if being shot from the enemy side
			movex*=-1;
		}	
    } 
//Get and Set Methods------
    public double getx(){ 
    	return bulletx;
    }
    public double gety(){
    	return bullety;
    }
    public int getw(){
    	return bulletw;
    }
    public int geth(){
    	return bulleth;
    }
    public boolean exploded(){ //return if the bullet has exploded or not
    	return exploded;
    } 
//Movement methods-----
    public void move(){ //move the bullet according to it's x and y velocities
    	bulletx+=movex;
    	bullety+=movey;
    	bulletRect.setLocation((int)(bulletx+movex),(int)(bullety+movey));
	}
	public boolean checkInWindow(int maxx, int maxy){ //check if the RoboBullet is in a window
		if (bulletx>maxx || bulletx<0){
			return false;
		}
		if (bullety>maxy || bullety<0){
			return false;
		}
		return true;
	
	}	
//Attacking methods-----
	public void dealDamage(){//deals damage to another robot  
    	if (target!=null){
    		target.takeDamage(attpower);
    	}
    	exploded=true; //bullet explodes upon reaching target
    }
    public Boolean collision(){ //checks if a bullet has collided with it's target
		if ((bulletRect).intersects(target.getRect())){
			dealDamage(); 
			return true;
		}
        return false;
    }
//Drawing methods-----
	public void drawBullet(Graphics2D g,RoboPanel panel){ //draws the RoboBullet
		g.drawImage(bulletPic,(int)bulletx,(int)bullety,panel);
    }
}