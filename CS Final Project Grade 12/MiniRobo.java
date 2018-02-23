
/**
 * @(#)MiniRobo.java
 *
 *
 * @Shahir Chowdhury
 * @version 1.00 2015/4/26
 */
 
 /* This program creates a MiniRobo object, a small robot that functions as a minion for its commanding main robot. 
  *	It contains all methods regarding how the MiniRobo attacks, how it gains experience points and how to draw its 
  *	animations. Its purpose int he game is to fight against enemy MiniRobos and MainRobot
  * and defend its own MainRobot from enemy attacks with its life. */
  
/* Things to do: 
 *	-Make LaserBot laser aim for hitBox
 *	-Status effects (once drain and shock are made)
 *	-Click to display all MiniRobo's health
 *	-pop up window regarding info if specific MiniRobo is clicked on
 *	-Position minirobo beside an existing minirobo of same type if two manage to make it on to the same side (hack)
	-Ability for robots to flinch (Sprite-wise) upon taking damage after their attack animations have finished
   		-Tank and Shot bots are different because they can flinch as soon as their bullets have all been shot
   		-Laser will only be flinchible after laser has been shot
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class MiniRobo extends GenRobo implements Comparable <MiniRobo>{
	private boolean attacking=false; //flag for if the MiniRobo is attacking
	private boolean finishlaser=false; //flag for when a LaserBot's attack animation is finished
	private boolean firstshot=false; //flag for if the first bullet in a ShotBot's salvo has been fired or not
	
   	private int bulletnum; //number of bullets a ShotBot or TankBot can fire 
   	private int bulletpow; //power of each bullet a ShotBot can fire (ShotBot's power is divided among the number of bullets it can shoot) 
   	private int bulletspeed;
   	private int bullettime=0; //counter to time the delay in between two bullet shots 
   	private int maxbullettime; //number of frames that must pass before another bullet can be fired
   	private int lasertime=0; //counter for each frame a LaserBot's laser animation is displayed on screen
   	private int maxlasertime; //Maximum time a LaserBot's laser animation can last for
   	private int level; //current experience level of the MiniRobo (attack power and health increase with level)
   	private int exp; 
   	private int bonusexp; //bonus experience points awarded to MiniRobos for successful kills at the end of a combat phase
   	private int outerbeamw=5; //width of the outer laser beam effect that goes alongside the laser beam animation
   	private int maxbeamw=9; //maximum width of the outer laser beam
   	private int minbeamw=1; //minimum width of the outer laser beam
   	private int beamchange=1; //increment by which the outer laser beam's width changes
   	private int pulsetime=5; //time interval inbetween the size of the beam changing
   	
   	private ArrayList<GenRobo> targetlist= new ArrayList<GenRobo>(); //target list for MiniRobos
	
    public MiniRobo(String type,int robox, int roboy,boolean affinity,ArrayList<Image> piclist,int level){
    	super (type,robox,roboy,affinity,piclist);
    	if (type.equals("ShotBot")){ //MiniRobo type with medium health and medium damage output
    		maxbullettime=45;
    		bulletnum=2;
    		bulletpow=3; //ShotBots shoot multiple bullets, each having an equal fraction of the ShotBot's total power
    		bulletspeed=5;
    		maxlasertime=0; //all fields regarding other MiniRobos types are unused
    	}
    	else if (type.equals("TankBot")){ //MiniRobo type with highest health and lowest damage output
    		bulletspeed=3;
    		bulletnum=1;
    		maxbullettime=0; 
    		bulletpow=0; 
    		maxlasertime=0; 	
    	}
    	else if(type.equals("LaserBot")){ //MiniRobo type with the lowest health and highest damage output
    		maxlasertime=150;
    		bulletspeed=0;
    		bulletnum=0;
    		maxbullettime=0; 
    		bulletpow=0; 
    	}
    	this.level=level;
    	if (level>=2){ //Modify the MiniRobo's stats to match its current level if the level is greater than 1	
    	    adjustStats(); 
    	}
    }
//Comparator Methods-----
    public int compareTo(MiniRobo compared) { //compares total attack power of MiniRobo
        int compp=compared.getp();
        return compp-getp(); //higher power is valued higher (compared in descending order)
    }
    public static Comparator<MiniRobo> HIDComparator= new Comparator<MiniRobo>(){
	/* Comparator that allows for the comparison of MiniRobo based on their highest instance damage.
	 * This means they will be compared based on the damage they can deal in one attack, not their full
	 * damage output */
    	public int compare(MiniRobo a, MiniRobo b){ 
	       	int pa,pb;//attack powers of robo a and b
	       	if (a.gett().equals("ShotBot")){ //special case for ShotBot as they deal a fraction of their total damage in each attack 
	       		pa=a.getbp(); 
	       	}
	       	else{
	       		pa=a.getp();  	
	       	}
	       	if (b.gett().equals("ShotBot")){
	       		pb=b.getbp();
	       	}
	       	else{
	       		pb=b.getp();  	
	       	}      	
	        return pb-pa;//higher power is valued higher (compared in descending order)
	    }
    };
    
//Get and Set Methods------
    public GenRobo getTarget(){ //returns the MiniRobo's current MiniRobo target
    	if (targetlist.isEmpty()==false){
    		return targetlist.get(0);//the first robo in the targetlist is the MiniRobo's current target
    	}
		return null;
    }
    public void resetTargets(){ //reset all targets after an attack
    	targetlist.clear();
    }
    public boolean getattacking(){ //returns if the MiniRobo is attacking
    	return attacking;
    }
    public boolean getf(){ //returns if a GenRobo has finished firing its laser assuming it is a LaserBot
    	return finishlaser;
    }
    public int getbn(){ //returns number of bullets the MiniRobo can shoot assuming it can shoot bullets
    	return bulletnum;
    }
    public int getbp(){ //returns the power of each individual bullet of the MiniRobo assuming it is a ShotBot
    	return bulletpow;
    }
    public int gete(){ //returns current amount of exp accumulated
    	return exp;
    }
    public int getl(){ //returns the level of the MiniRobo
    	return level;
    }
//Experience point related methods-----
	public void expCheck(){ //checks if enough exp has been accumulated to level up
		if ((exp>=150 && level==1) || (exp>=250 && level==2) || (exp>=350 && level==3)){ //requirement to levelup increases with level
			exp=0; //reset exp for next level
			levelup();
		}
	}
	public void levelup(){ //increases the level of the MiniRobo, increasing its attack power and maximum health 
		if (gett().equals("TankBot")){ //health and attack power are scaled differently depending on the MiniRobo type 
			setmaxhp(getmaxhp()+(8+(level*3))); 
			sethp(getmaxhp()); //MiniRobos heal up to full upon leveling up
			setp(getp()+(1+(1*level)));		
		}
		else if (gett().equals("ShotBot")){
			setmaxhp(getmaxhp()+ (2+(level*4)));
			sethp(getmaxhp());
			if (level==1 || level==3){ //ShotBot gains an additional bullet every other level
				bulletnum+=1;
			}
			bulletpow+=1;
			setp(bulletpow*bulletnum);
		}
		else if (gett().equals("LaserBot")){
			setmaxhp(getmaxhp()+(2+(level*2)));
			sethp(getmaxhp());
			setp(getp()+(6+(1*level)));
		}
		level+=1;
	}
	public void giveExp(){ //gives the MiniRobo experience points based on their damage output
		if (type=="LaserBot"){ //LaserBots are given a reduced amount of exp to slow down their rate of growth for game balance 
			exp+=(getp()*10-(20*level))+bonusexp;
		}
		else{
			exp+=getp()*10 +bonusexp;
		}
		bonusexp=0;//reset bonus exp gained from kills at the end of exp distribution
	}
	public void giveKillExp(MiniRobo target){ //awards bonus exp for sucessfully killing an enemy MiniRobo in a combat phase
		int bonus=0; //exp bonus
		if (target.gett().equals("LaserBot")){ //different MiniRobo types give different amounts of bonus exp
			bonus=10; 
		}
		else if (target.gett().equals("ShotBot")){
			bonus=20;
		}
		else if (target.gett().equals("TankBot")){
			bonus=30;
		}
		bonusexp=bonus*target.getl(); //bonus is multiplied by the killed MiniRobo's level
	}
	public void adjustStats(){ //adjusts the MiniRobo's stats to its appropriate level
		for (int i=1;i<level;i++){
			if (gett().equals("TankBot")){
				setmaxhp(getmaxhp()+(8+(i*3))); 
				sethp(getmaxhp()); //MiniRobos heal up to full upon leveling up
				setp(getp()+(1+(i*1)));			
			}
			else if (gett().equals("ShotBot")){
				setmaxhp(getmaxhp()+ (2+(i*4)));
				sethp(getmaxhp());
				if (i==1 || i==3){ //ShotBot gains an additional bullet every other level
					bulletnum+=1;
				}
				bulletpow+=1;
				setp(bulletpow*bulletnum);
			}
			else if (gett().equals("LaserBot")){
				setmaxhp(getmaxhp()+(2+(i*2)));
				sethp(getmaxhp());
				setp(getp()+(6+(i*1)));	
			}
		}
	}
//Attacking related methods-----
    public void planattack(GenRobo target){ //sets the target of the MiniRobo 
    	targetlist.add(target);
    	attacking=true;
    }
    public void attack(RoboPanel robopanel){//deals damage to another robot  
    	if (targetlist.isEmpty()==false){ //only attack if there are targets to hit
    		if (gett().equals("ShotBot") || gett().equals("TankBot")){ //ShotBot and TankBot type MiniRobo attack using bullets 
    			robopanel.makeBullet(this,targetlist.get(0),bulletspeed);
    			targetlist.remove(0);
    		}
    		else if (gett().equals("LaserBot")){ //LaserBot type MiniRobo deal damage at the end of their laser animation
    			for (int i=0;i<targetlist.size();i++){ 
    				targetlist.get(i).takeDamage(this.getp());
    			}
    			targetlist.remove(0);
    			finishlaser=false;
    		}
    	}
    	if (targetlist.isEmpty()==true){//reset all attacking related flags and targets once all targest have been attacked
    		firstshot=false;
       		attacking=false;
    		resetTargets();
    		giveExp(); //give exp for damage dealt
    	}
    }
    public boolean lasertimer(){ //method that will return false until animation timer has successfully reached it's target number of frames
    	if (lasertime<maxlasertime){ //return false if it is not time yet to stop drawing a laser
    		lasertime+=1;
    		return false;
    	}
    	lasertime=0; //reset lasertimer when finished
    	finishlaser=true; 
    	//attacking=false;
    	outerbeamw=6; //reset outerlaserbeam variables to default values
    	beamchange=1;
    	return true;	
    }
    public boolean bullettimer(){ //timer method that adds a delay to the time it takes to shoot another bullet
    	if (firstshot==false){ //return true for first bullet fired to prevent delay on first bullet
    		firstshot=true;
    		return true;
    	}
    	else if (bullettime<maxbullettime){ //return false until it is time to shoot another bullet
    		bullettime+=1;
    		return false;
    	}
    	bullettime=0;
    	return true;
    }
//Drawing methods----- 
    public void drawLaser(Graphics2D g,Image laserPic,RoboPanel panel){ //Draws a laser beam attack
    	int pich= new ImageIcon(laserPic).getIconHeight();
    	int tx=targetlist.get(0).getx()+targetlist.get(0).getw()/2; //gets the centre of the target (x value)
		int ty=targetlist.get(0).gety()+targetlist.get(0).geth()/2; //gets the centre of the target (y value)
		int dy= Math.abs((gety()+geth()/2)-ty); //y distance from target to robo
		int dx= Math.abs((getx()+getw()/2)-tx); //x distance from target to robo
		if (gety()+geth()/2<targetlist.get(0).gety()+targetlist.get(0).geth()/2){//if target is positioned higher, vertically reflect slope
			dy*=-1;
		}
		int hyp= (int)(Math.sqrt(dx*dx+dy*dy)); //hypoteneuse
    	double angle= Math.atan2(dy,dx); //tan inverse to get the angle
    	
    	if (getx()<=tx){//Angle  changes depending on the MiniRobo's x position
    		angle=(Math.PI*2)-angle; //playerside
    	}
    	else{
	   		angle=((Math.PI*2)-angle)*-1+Math.PI;//enemyside
    	}
    	
		for (int i=0;i<hyp*2;i++){
			int newy=0;
			int newx= getx()+getw()/2+(int)(Math.cos(angle)*(double)(i/2));
			if (geta()==true){//raise the starting point of a laser beam to the eye of a player side LaserBot 
				newy= gety()-40+geth()/2+(int)(Math.sin(angle)*(double)(i/2)); //-40 used to centre the laser beam (magical number)
			}
			else{
				newy= gety()+geth()/2+(int)(Math.sin(angle)*(double)(i/2));
			}
			drawOuterLaser(g,newx,newy,pich,panel);
			g.drawImage(laserPic,newx,newy,panel);
		}
		pulseOuterLaser(); //outer laser effect grows or shrinks in size for next drawing sequence
	}
	public void drawOuterLaser(Graphics2D g,int x,int y,int picw,RoboPanel panel){
		/* Draws additional laser beams to accompany the drawLaser's laser beam effect
		 * for a better looking laser beam */
		/*g.setColor(new Color(255,255,255));
		g.fillRect(x,y-(outerbeamw+2),2,outerbeamw+2);
		g.fillRect(x,y+picw,2,outerbeamw+2);*/
		if (geta()==true){ //different coloured beam for different teams
			g.setColor(new Color(108,0,38));
		}
		else{
			g.setColor(new Color(58,0,130));
		}
		g.fillRect(x,y-outerbeamw,2,(int)(outerbeamw));
		g.fillRect(x,y+picw,2,(int)(outerbeamw));
		
	}
	public void pulseOuterLaser(){ //method that causes the outer laser beam to grow or shrink in size, giving the illusion that it is pulsating
		if (lasertime%pulsetime==0){ //only change in size every set number of frames
			outerbeamw+=beamchange;
			if (outerbeamw==maxbeamw || outerbeamw==minbeamw){
				beamchange*=-1;
				outerbeamw+=beamchange;
			} 
		}
	}  
}
