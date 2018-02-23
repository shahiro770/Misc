/**
 * @(#)EffectShot.java
 *
 *
 * @author 
 * @version 1.00 2015/6/11
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class EffectShot{
	private boolean exploded=false; //flag for if the bullet has not reached its target yet
	
	private double bulletx; //x and y are doubles so that they can move on more precise slopes
	private double bullety;
	private double movex; //x velocity
	private double movey; //y velocity
	
	private int bulletw;
	private int bulleth;
	private int attpower;//attack power of the bullet
	private int speed; //speed at which bullet moves
	
	private Rectangle bulletRect; //hitbox
	private GenRobo shooter; //bullets can only be shot by MiniRobo
	private GenRobo target;
	
	public EffectShot(GenRobo shooter, GenRobo target,int speed,String type,int pow){
		attpower=pow;
    	this.bulletx=shooter.getx()+shooter.getw()/2;
    	this.bullety=shooter.gety()+shooter.geth()/2;
    	if (type.equals("Shock")){
    		this.bulletw=10;
    		this.bulleth=10;
    	}
    	else if (type.equals("Microwave")){
    		this.bulletw=20;
    		this.bulleth=20;
    	}
    	bulletRect=new Rectangle((int)bulletx,(int)bullety, bulletw, bulleth);
    	this.speed=speed;
    	this.target=target;

    							
    	if (target!=null){
    		int tx=target.getx()+target.getw()/2; //gets the centre of the target (x value)
			int ty=target.gety()+target.geth()/2; //gets the centre of the target (y value)
			int distx= Math.abs((shooter.getx()+shooter.getw()/2)-tx); //dx
			int disty= Math.abs((shooter.gety()+shooter.geth()/2)-ty); //dy
    		double angle=Math.atan2(disty,distx); //tan inverse to get the angle
    		double hyp=(double)speed; //a similar triangle is created based on the speed value given
    		this.movex= (hyp*Math.sin(Math.PI/2-angle)); //similar triangle's hypoteneuse determines movement values
    		this.movey= (hyp*Math.sin(angle)); 
    		if (bullety>target.gety() && target.gett()!="MainBot"){//only flip the slope if the target's entire body is higher than the shooter
    		//This might be incorrectly done as the y value of the bullet will be greater if it is moving downwards
    			movey*=-1; //flip the slope if the target is higher
    		}
    		if (shooter.geta()==false){// move in opposite direction if being shot from the enemy side
    			movex*=-1;
    		}
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
//Action methods-----
    public void move(){ //move the bullet according to it's x and y velocities
    	bulletx+=movex;
    	bullety+=movey;
    	bulletRect.setLocation((int)(bulletx+movex),(int)(bullety+movey));
	}
	public void dealDamage(){//deals damage to another robot  
    	if (target!=null){
    		target.takeDamage(attpower);
    	}
    	exploded=true; //bullet explodes upon reaching target
    }
    public Boolean collision(){ //checks if a bullet has collided with it's target
		int targetx,targety,targetw,targeth;
		targetx=targety=targetw=targeth=0;
		if (target!= null){
			targetx= target.getx();
			targety= target.gety();  
			targetw= target.getw();  
			targeth= target.geth(); 
		}
		if ((bulletRect).intersects(new Rectangle(targetx, targety, targetw, targeth))){
			dealDamage(); //get this to use the target's hit rect
			return true;
		}
        return false;
    }
//Drawing methods-----
	public void drawEffectShot(Graphics2D g){ 
		g.fillRect((int)bulletx,(int)bullety,bulletw,bulleth);
    }
}