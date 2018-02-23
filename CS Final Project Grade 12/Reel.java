/**
 * @(#)Reel.java
 *
 *
 * @Helen Zhang
 * @version 1.00 2015/5/22
 *
 * Roll-able three-slot reel that allows either the player or the AI enemy to roll for a desired robot,
 * support action, or attack. The player uses roll() and the AI uses rollFor(). rollFor() stops
 * by itself depending on what the AI wants to go for, and roll() stops when the player clicks a second
 * time (first click is to start).
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*; 
import javax.imageio.*; 
import java.awt.image.BufferedImage;
import java.util.*;
public class Reel{
	private boolean rollReel=false,rollSlow,rollTwo,rollThree,first=true,aff;
	private int nSlow=0,nTwo=0,nThree=0,rollCount=0,posx,posy,current=0,rollFor=0;
	
	private int[] slow={50,25,0,10,1};				// many fields and variables
	private int[] two={105,25,0,30,1};			
	private int[] three={210,25,0,30,1};
	
	BufferedImage botReel=null,atkReel=null,supReel=null,tempSlow=null,tempTwo=null,tempThree=null,currReel=null;
	private Image reelFrame,botButton,supButton,atkButton;
	
    public Reel(boolean a){
    	try{
    		botReel=ImageIO.read(new File("RoboTech Sprites/Pics/General Pics/botReel.png"));
    		supReel=ImageIO.read(new File("RoboTech Sprites/Pics/General Pics/supReel.png"));
    		atkReel=ImageIO.read(new File("RoboTech Sprites/Pics/General Pics/atkReel.png"));
    		  		
		}
		catch (IOException e){}
		
    	reelFrame=new ImageIcon("RoboTech Sprites/Pics/General Pics/reelFrame.png").getImage();
		botButton=new ImageIcon("RoboTech Sprites/Pics/General Pics/botButton.png").getImage();
		supButton=new ImageIcon("RoboTech Sprites/Pics/General Pics/supButton.png").getImage();
		atkButton=new ImageIcon("RoboTech Sprites/Pics/General Pics/atkButton.png").getImage(); 
    	
    	aff=a;
    	currReel=botReel;
    }
    
    /*private void imageLoad(){
    	try{
    		botReel=ImageIO.read(new File("botReel.png"));
    		supReel=ImageIO.read(new File("supReel.png"));
    		atkReel=ImageIO.read(new File("atkReel.png"));
    		reelFrame=ImageIO.read(new File("reelFrame.png"));
    		botButton=ImageIO.read(new File("botButton.png"));
    		atkButton=ImageIO.read(new File("atkButton.png"));
    		supButton=ImageIO.read(new File("supButton.png"));    		
		}
		catch (IOException e){}
    }*/
    
    public void stop(){
    	rollReel=false;
    	first=false;
    }
    
    public boolean getAff(){
    	return aff;
    }
   	
   	// rolls the reel
   	public void roll(){
   		System.out.println("ROLL-----------");
   		/*if(rollReel){
			rollReel=false;
			first=false;
		}*/
		if(rollReel==false){	// copied the clicking part from the original to here - not 100% sure it will
			rollReel=true;		// work but I can't test it without the main panel
			reset();
		}
		rollAll();
   	}
   	
   	// iniates the reel rolling, does some math regarding the operations, etc. Basically the main roll function
    private void rollAll(){
    	if(rollReel){
    		first=true;							// if rollReel is true, set all the roll-or-not-roll flags to true
    		rollSlow=true;						// so that when spinCheck() calls, they roll
    		rollTwo=true;
    		rollThree=true;
    	}
    	if(rollReel==false && first==false){	// but if rollReel is false (aka they clicked - which will somehow
    			rollSlow=false;    				// have to passed in??) and the first reel has stopped, start
	    		two[4]+=1;						// slowing down the other two
	    		two[3]-=two[4];					// subtract the distance shifted down each time by the friction,
				if(two[3]-two[4]<=0){			// which increases by 1 each time
					two[3]=0;					// until the speed becomes 0 or less than 0, in which case set it 
					rollTwo=false;				// to zero and set rollTwo to false so the reel stops spinning
				}
				if(rollTwo==false){
					three[4]+=1;
					three[3]-=three[4];			// and same thing for the third reel, but only start slowing down
					System.out.println(three[3]);		// if the second one already stopped
					if(three[3]-three[4]<=0){
						three[3]=0;
						rollThree=false;
					}
				}
    	}
    	if(aff==false){									// if this is the AI rolling, then
    		if(rollCount>=96){							// make sure the reel has rolled 3 times before
	    		if(rollFor==0 && nSlow>=0 && nSlow<40){	// stopping to make it look more natural
					rollSlow=false;						// and then, if the current coordinate is within the
		    	}										// checkRoll() stopping range on the slow reel, set
		    	if(rollFor==1 && nSlow>40 && nSlow<120){	// its rolling status to false, depending on the
					rollSlow=false;						// specified target
		    	}
		    	if(rollFor==2 && nSlow>120 && nSlow<200){
					rollSlow=false;
		    	}
		    	rollReel=false;							// set the rest of the reel, as well as first, to false
		    	first=false;
		    	rollCount=0;							// also rollCount so it can be used next rollFor()
    		}
    	}
    	if(three[3]==0){						// if all three have stopped, rollSnap(), which makes it so that
    		rollSnap(checkRoll());				// the reel images line up with the top
    	}
    	spinCheck();							// spinCheck() check the roll booleans to see if each reel should
    }											// be rolled
    
    // checks the booleans of roll-or-not based on what they are set to, the math is just a precaution, and
    // calls the rolling methods if the conditions are met
    private void spinCheck(){
    	System.out.println("SPINCHECK");
    	if(rollSlow && (slow[3]-slow[4])>=0){
    		spinSlow();
    	}

    	if(rollTwo && (two[3]-two[4])>=0){
    		spinTwo();
    	}
    	if(rollThree && (three[3]-three[4])>=0){
    		spinThree();
    	}
    }
    
    
    private void spinSlow(){
    	if(aff==false){
    		rollCount+=1;		// keeps track of how much it has rolled, to be used in rollFor() AKA the AI roll
    	}
    	nSlow+=slow[3];			// add the speed onto the coordinate
    	nSlow=nSlow%240;		// mod it by 240 (each icon is 80) so that it appears to spin continuously
    	tempSlow=currReel.getSubimage(slow[2],nSlow,100,80);	// then get a subimage using the coordinates
    }
    // spins the second reel
    private void spinTwo(){				// spinTwo() and spinThree() are the same as spinSlow, just without rollCount
    	nTwo+=two[3];
    	nTwo=nTwo%240;
    	tempTwo=currReel.getSubimage(two[2],nTwo,100,80);
    }
    // spins the third reel
    private void spinThree(){
    	nThree+=three[3];
    	nThree=nThree%240;
    	tempThree=currReel.getSubimage(three[2],nThree,100,80);
    }
    
    // resets the speed, friction, and starting coordinates to the originals after one roll
    private void reset(){
    	nSlow=0;
    	slow[3]=10;
    	slow[4]=1;
    	nTwo=0;
    	two[3]=30;
    	two[4]=1;
    	nThree=0;
    	three[3]=30;
    	three[4]=1;
    }
    
    // keeps track of which reel is being used via an int
    private void switchReel(int type){
		if(type==0){
			currReel=botReel;
			current=0;
		}
		if(type==1){
			currReel=supReel;
			current=1;
		}
		if(type==2){
			currReel=atkReel;
			current=2;
		}
    }
    
    // returns the results of the roll
    public ArrayList<String> checkRoll(){
    	ArrayList<String> roll=new ArrayList<String>();	// make a string list to hold the results
    	int s=(nSlow+40)%240;			// for each reel, add 40 to the coordinate (so that it's in the middle)
    	int tw=(nTwo+40)%240;			// then mod it by 240 so the selection is limited to 3,
    	int th=(nThree+40)%240;
    	s/=80;							// then integer division by 80 to determine the result
    	tw/=80;
    	th/=80;
		if(s==0){
			if(current==0){
				roll.add("ShotBot");		// add to the string list accordingly
			}
			if(current==1){
				roll.add("Hack");	
			}
			if(current==2){
				roll.add("Shock");	
			}			
		}
    	if(s==1){
			if(current==0){
				roll.add("TankBot");	
			}
			if(current==1){
				roll.add("Energy");	
			}
			if(current==2){
				roll.add("Laser");	
			}	
		}
		if(s==2){
			if(current==0){
				roll.add("LaserBot");	
			}
			if(current==1){
				roll.add("Firewall");	
			}
			if(current==2){
				roll.add("Microwave");	
			}	
		}
		
		if(tw==0){
			if(current==0){
				roll.add("ShotBot");
			}
			if(current==1){
				roll.add("Hack");	
			}
			if(current==2){
				roll.add("Shock");	
			}			
		}
    	if(tw==1){
			if(current==0){
				roll.add("TankBot");	
			}
			if(current==1){
				roll.add("Energy");	
			}
			if(current==2){
				roll.add("Laser");	
			}	
		}
		if(tw==2){
			if(current==0){
				roll.add("LaserBot");	
			}
			if(current==1){
				roll.add("Firewall");	
			}
			if(current==2){
				roll.add("Microwave");	
			}	
		}
		
		if(th==0){
			if(current==0){
				roll.add("ShotBot");
			}
			if(current==1){
				roll.add("Hack");	
			}
			if(current==2){
				roll.add("Shock");	
			}			
		}
    	if(th==1){
			if(current==0){
				roll.add("TankBot");	
			}
			if(current==1){
				roll.add("Energy");	
			}
			if(current==2){
				roll.add("Laser");	
			}	
		}
		if(th==2){
			if(current==0){
				roll.add("LaserBot");	
			}
			if(current==1){
				roll.add("Firewall");	
			}
			if(current==2){
				roll.add("Microwave");	
			}	
		}
		return roll;					// return the list when done
    }
    
   // 'snaps' the reels into lined-up position after rolling
   private void rollSnap(ArrayList<String> roll){
    	if("LaserBotHackShock".contains(roll.get(0))){				// basically just checks what
    		tempSlow=currReel.getSubimage(slow[2],0,100,80);	// the result from checkRoll() was
    	}														// and sets the coordinate on the
    	if("TankBotEnergyLaser".contains(roll.get(0))){				// picture accordingly for each
    		tempSlow=currReel.getSubimage(slow[2],80,100,80);	// reel
    	}
    	if("ShotBotFirewallMicrowave".contains(roll.get(0))){
    		tempSlow=currReel.getSubimage(slow[2],160,100,80);
    	}
    	
    	if("LaserBotHackShock".contains(roll.get(1))){
    		tempTwo=currReel.getSubimage(two[2],0,100,80);
    	}
    	if("TankBotEnergyLaser".contains(roll.get(1))){
    		tempTwo=currReel.getSubimage(two[2],80,100,80);
    	}
    	if("ShotBotFirewallMicrowave".contains(roll.get(1))){
    		tempTwo=currReel.getSubimage(two[2],160,100,80);
    	}
    	
    	if("LaserBotHackShock".contains(roll.get(2))){
    		tempThree=currReel.getSubimage(three[2],0,100,80); 
    	}
    	if("TankBotEnergyLaser".contains(roll.get(2))){
    		tempThree=currReel.getSubimage(three[2],80,100,80); 
    	}
    	if("ShotBotFirewallMicrowave".contains(roll.get(2))){
    		tempThree=currReel.getSubimage(three[2],160,100,80); 
    	}
    }
    
    // AI roll method which just calls the roll() function with the affinity
    // false for AI so that it enters the AI loop in rollAll() and stops on
    // its own depending on the specified type.
    public void rollFor(int type){
    	rollFor=type;
    	//aff=false;
    	roll();
    }
    
    public void drawReel(Graphics g,RoboPanel panel){
    	if(aff==true){
    		g.drawImage(tempSlow,slow[0],slow[1],panel);
	    	g.drawImage(tempTwo,two[0],two[1],panel);
	    	g.drawImage(tempThree,three[0],three[1],panel);
	    	
	    	g.drawImage(reelFrame,0,0,panel);
    	
	    	if(current==0){
	    		g.drawImage(botButton,110,137,panel);
	    	}
	    	else if(current==1){
	    		g.drawImage(supButton,180,137,panel);
	    	}
	    	else if(current==2){
	    		g.drawImage(atkButton,250,137,panel);
	    	}
    	}
    	else if(aff==false){    		
    		g.drawImage(tempSlow,slow[0]+500,(1080-slow[1]),panel);
	    	g.drawImage(tempTwo,two[0]+500,(1080-two[1]),panel);
	    	g.drawImage(tempThree,three[0]+500,(1080-three[1]),panel);
	    	
	    	g.drawImage(reelFrame,630,0,panel);
    	
	    	if(current==0){
	    		g.drawImage(botButton,740,667,panel);
	    	}
	    	else if(current==1){
	    		g.drawImage(supButton,810,667,panel);
	    	}
	    	else if(current==2){
	    		g.drawImage(atkButton,880,667,panel);
	    	}
    	}
    }
}