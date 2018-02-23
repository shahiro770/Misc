/**
 * @(#)RoboPanel.java
 *
 *
 * @Shahir Chowdhury
 * @version 1.00 2015/4/21
 */

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.font.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*; //useless imports for now
import javax.imageio.*; 
import java.io.File;
//import java.io.FileInputStream;
//import javax.sound.sampled.AudioSystem;
//import java.applet.*;

/* Things to do:
 *	-Stop being a pretentious jerk
 *	-Comment code (Go through and fix meaningless code as well improve style)
 *	-Get a battle system working that is turn based (Waiting on Helen)
 *		-Make a "Header" method that does after battle calculations, give each robot a "has attacked" and "total damage dealt" properties that will determine how EXP is given
 *	-Put in the slot machine (Helen!)
 *	-Make slot machine effects (Helen/Raymond)
 *	-AI (Raymond)
*/

public class RoboPanel extends JPanel implements MouseMotionListener, MouseListener {
	private Image gShotBPic,gShotEPic,gShotRAPic,gShotLAPic;
	private Image bShotBPic,bShotEPic,bShotRAPic,bShotLAPic,bShotMAPic;
	private Image gTankBPic,gTankEPic,gTankRAPic,gTankLAPic;
	private Image bTankBPic,bTankEPic,bTankRAPic,bTankLAPic;
	private Image gLaserBPic,gLaserEPic,gLaserRAPic,gLaserLAPic,gLaserRLPic,gLaserLLPic;
	private Image bLaserBPic,bLaserEPic,bLaserRLPic,bLaserLLPic,bLaserMAPic;
	private Image gMainBPic,gMainEPic,gMainRAPic,gMainLAPic,gMainRLPic,gMainLLPic;
	private Image bMainBPic,bMainEPic,bMainRLPic,bMainLLPic,bMainMAPic;
	private Image gTankBulletPic,gShotBulletPic,bTankBulletPic,bShotBulletPic;
	private Image gLaserBeamPic,bLaserBeamPic;
	private BufferedImage gFullHealthPic,gEmptyHealthPic,bFullHealthPic,bEmptyHealthPic; 
	
	private String currentScreen; //current screen that the user is looking at
	private String currentPhase="StartReel"; //current phase of combat the game is in during the combat phase
	
	private boolean mouseDown=false; //flag that checks if a mouse button has been pressed
	private boolean decision=false; //flag for if the player has made input via the mouse
	private boolean rolling=false;
	private boolean incombat=false; //flag for if the combat sequence is currently taking place
	private boolean afterflag=false; //flag to calculate the after math of a battle
	private boolean energy; //flag for if an energy leak has occured
	
	private int windoww=1080; //window width
	private int windowh=720; //window height
	private int energylevel; //current level of energy leak 
	private int playerstartx=30; //player MainRobo starting coordinates
	private int playerstarty=330;
	private int groundx=0; //ground starting coordinates
	private int groundy=600;
	private int tankstartx=220; //player TankBot starting coordinates
	private int tankstarty=360; 
	private int laserstartx=playerstartx+220; //player LaserBot starting coordinates
	private int laserstarty=500;
	private int shotstartx= 60; //player ShotBot starting coordinates
	private int shotstarty=240;
	private int enemystartx=890; //enemy MainRobo starting coordinates
	private int enemystarty=350;
	private int eshotx=980;//enemy ShotBot starting coordinates
	private int eshoty=260;
	private int etankx=720;//enemy TankBot starting coordinates
	private int etanky=380;
	private int elaserx=770;//enemy LaserBot starting coordinates
	private int elasery=548;
	
	private MainRobo player,enemy;
	private MiniRobo laser,tank,shot,eshot,etank,elaser;
	private Reel pReel,eReel;
	private ReelEffect reelEffect;
	
	private ArrayList<Image> gShotPics= new ArrayList<Image>();//list of sprites for player sided GenRobo
	private ArrayList<Image> gTankPics= new ArrayList<Image>();
	private ArrayList<Image> gLaserPics= new ArrayList<Image>();
	private ArrayList<Image> gMainPics= new ArrayList<Image>();
	private ArrayList<Image> bShotPics= new ArrayList<Image>();//list of sprites for enemy sided GenRobo
	private ArrayList<Image> bTankPics= new ArrayList<Image>();
	private ArrayList<Image> bLaserPics= new ArrayList<Image>();
	private ArrayList<Image> bMainPics= new ArrayList<Image>();
	private ArrayList<MiniRobo>playerbots=new ArrayList<MiniRobo>(); //list of all MiniRobo on the player's side
	private ArrayList<MiniRobo>enemybots=new ArrayList<MiniRobo>(); //list of all MiniRobo on the enemy's side
	private ArrayList<RoboBullet>bulletlist=new ArrayList<RoboBullet>(); //list of all RoboBullets currently alive in game
	private ArrayList<String> results=new ArrayList<String>(); //list of the results obtained from a reel roll
	
	private RoboTech mainFrame;
	
    public RoboPanel(RoboTech m){
    	mainFrame = m;
//Sprites-----
/* Sprites for all GenRobo are added in a specific order to allow SpriteManager to access sprites in a 
 * a more organized way.*/
//Player side Sprites---
	//ShotBot Sprites-
    	gShotBPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GShot Body.PNG").getImage();
    	gShotPics.add(gShotBPic);
    	gShotEPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GShot Eyes.PNG").getImage();
    	gShotPics.add(gShotEPic);
    	gShotRAPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GShot Right Arm.PNG").getImage();
    	gShotPics.add(gShotRAPic);
    	gShotLAPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GShot Left Arm.PNG").getImage();
    	gShotPics.add(gShotLAPic);  	
	//TankBot Sprites-
		gTankBPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GTank Body.PNG").getImage();
		gTankPics.add(gTankBPic);
		gTankEPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GTank Eyes.PNG").getImage();
		gTankPics.add(gTankEPic);
		gTankRAPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GTank Right Arm.PNG").getImage();
		gTankPics.add(gTankRAPic);
		gTankLAPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GTank Left Arm.PNG").getImage();
		gTankPics.add(gTankLAPic);
	//LaserBot Sprites-
		gLaserBPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GLaser Body.PNG").getImage();
		gLaserPics.add(gLaserBPic);
		gLaserEPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GLaser Eyes.PNG").getImage();
		gLaserPics.add(gLaserEPic);
		gLaserRAPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GLaser Right Arm.PNG").getImage();
		gLaserPics.add(gLaserRAPic);
		gLaserLAPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GLaser Left Arm.PNG").getImage();
		gLaserPics.add(gLaserLAPic);	
		gLaserRLPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GLaser Right Leg.PNG").getImage();
		gLaserPics.add(gLaserRLPic);
		gLaserLLPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GLaser Left Leg.PNG").getImage();
		gLaserPics.add(gLaserLLPic);		
	//MainBot Sprites-
		gMainBPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GMain Body.PNG").getImage();
		gMainPics.add(gMainBPic);
		gMainEPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GMain Eyes.PNG").getImage();
		gMainPics.add(gMainEPic);
		gMainRAPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GMain Right Arm.PNG").getImage();
		gMainPics.add(gMainRAPic);
		gMainLAPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GMain Left Arm.PNG").getImage();
		gMainPics.add(gMainLAPic);	
		gMainRLPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GMain Right Leg.PNG").getImage();
		gMainPics.add(gMainRLPic);
		gMainLLPic= new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GMain Left Leg.PNG").getImage();
		gMainPics.add(gMainLLPic); 

//Enemy side Sprites---
	//ShotBot Sprites-
		bShotBPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BShot Body.PNG").getImage(); //sprites for all GenRobo are added in a specific order
		bShotPics.add(bShotBPic);
		bShotEPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BShot Eyes.PNG").getImage();
		bShotPics.add(bShotEPic);
		bShotRAPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BShot Right Arm.PNG").getImage();
		bShotPics.add(bShotRAPic);
		bShotLAPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BShot Left Arm.PNG").getImage();
		bShotPics.add(bShotLAPic);
		bShotMAPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BShot Middle Arm.PNG").getImage();
		bShotPics.add(bShotMAPic);		
	//TankBot Sprites-
		bTankBPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BTank Body.PNG").getImage();
		bTankPics.add(bTankBPic);
		bTankEPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BTank Eyes.PNG").getImage();
		bTankPics.add(bTankEPic);
		bTankRAPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BTank Right Arm.PNG").getImage();
		bTankPics.add(bTankRAPic);
		bTankLAPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BTank Left Arm.PNG").getImage();
		bTankPics.add(bTankLAPic);    	
    //LaserBot Sprites-
    	bLaserBPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BLaser Body.PNG").getImage();
		bLaserPics.add(bLaserBPic);
		bLaserEPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BLaser Eyes.PNG").getImage();
		bLaserPics.add(bLaserEPic);
		bLaserRLPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BLaser Right Leg.PNG").getImage();
		bLaserPics.add(bLaserRLPic);
		bLaserLLPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BLaser Left Leg.PNG").getImage();
		bLaserPics.add(bLaserLLPic);
		bLaserMAPic= new ImageIcon("Robotech Sprites/Pics/Badguy Pics/BLaser Middle Leg.PNG").getImage();
		bLaserPics.add(bLaserMAPic);    
    //MainBot Sprites-
    	bMainBPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BMain Body.PNG").getImage();
		bMainPics.add(bMainBPic);
		bMainEPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BMain Eyes.PNG").getImage();
		bMainPics.add(bMainEPic);
		bMainRLPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BMain Right Leg.PNG").getImage();
		bMainPics.add(bMainRLPic);
		bMainLLPic= new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BMain Left Leg.PNG").getImage();
		bMainPics.add(bMainLLPic); 
		bMainMAPic=new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BMain Middle Leg.PNG").getImage();
		bMainPics.add(bMainMAPic);
//Bullets---
		gTankBulletPic=new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GTank Bullet.PNG").getImage();
		gShotBulletPic=new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GShot Bullet.PNG").getImage();		
		bTankBulletPic=new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BTank Bullet.PNG").getImage();
		bShotBulletPic=new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BShot Bullet.PNG").getImage();		
//Lasers---
		gLaserBeamPic=new ImageIcon("RoboTech Sprites/Pics/Goodguy Pics/GLaser Beam.PNG").getImage();
		bLaserBeamPic=new ImageIcon("RoboTech Sprites/Pics/Badguy Pics/BLaser Beam.PNG").getImage();		
//Healthbars---
		try {
    		gFullHealthPic=ImageIO.read(new File("RoboTech Sprites/Pics/Goodguy Pics/GFull Healthbar.PNG"));
    		gEmptyHealthPic=ImageIO.read(new File("RoboTech Sprites/Pics/Goodguy Pics/GEmpty Healthbar.PNG"));
    		bFullHealthPic=ImageIO.read(new File("RoboTech Sprites/Pics/Badguy Pics/BFull Healthbar.PNG"));
    		bEmptyHealthPic=ImageIO.read(new File("RoboTech Sprites/Pics/Badguy Pics/BEmpty Healthbar.PNG"));
		} 
		catch (IOException e){}	
	
	
	
//Objects-----
		pReel= new Reel(true);
		eReel= new Reel(false);

    	player= new MainRobo(playerstartx,playerstarty,true,gMainPics); //player side
    	shot= new MiniRobo("ShotBot",shotstartx,shotstarty,true,gShotPics,3);
    	playerbots.add(shot);
    	tank= new MiniRobo("TankBot",tankstartx,tankstarty,true,gTankPics,4);
    	playerbots.add(tank);
    	laser= new MiniRobo("LaserBot",laserstartx,laserstarty,true,gLaserPics,4);
    	playerbots.add(laser);
    	
    	enemy= new MainRobo(enemystartx,enemystarty,false,bMainPics); //enemy side
    	eshot= new MiniRobo("ShotBot",eshotx,eshoty,false,bShotPics,1);
    	enemybots.add(eshot);
    	etank= new MiniRobo("TankBot",etankx,etanky,false,bTankPics,1);
    	enemybots.add(etank);
    	elaser= new MiniRobo("LaserBot",elaserx,elasery,false,bLaserPics,1);
    	enemybots.add(elaser);
    	
    	addMouseMotionListener(this);
		addMouseListener(this);
		setSize(windoww,windowh);
	}
	public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }

//Mouse movement methods-----
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e){}
    
	public void mouseReleased(MouseEvent e) {
    	mouseDown=false;
    }     	 
    public void mousePressed(MouseEvent e){
    	mouseDown=true;
    }
//Mouse motion methods-----    	
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
//Get and Set methods-----
	public ArrayList<MiniRobo> getPbots(){
		return playerbots;
	}
	public ArrayList<MiniRobo> getEbots(){
		return enemybots;
	}
	public MainRobo getPmain(){
		return player;
	}
	public MainRobo getEmain(){
		return enemy;
	}
	public void setEnergyL(int level){ //sets the level of energy leak that is currently happening
		energylevel=level;
	}
	public void setEnergy(boolean tf){ 
		energy=tf;
	}
//Reel Methods-----
	public  void getResults(Reel reel){ //returns the results of the reel object rolling
		ArrayList<String> results=reel.checkRoll();
		if(results.size()>0){
			if(results.contains("ShotBot") || results.contains("TankBot") || results.contains("LaserBot")){
				reelRobo(results,reel.getAff(),this);
			}
			else if(!(results.contains("ShotBot") || results.contains("TankBot") || results.contains("LaserBot"))){
				reelEffect=new ReelEffect(results,reel.getAff(),this);
			}
		}
	}
	public MiniRobo checkRobo(ArrayList<MiniRobo> bots,String type,boolean aff){
	/* This method checks if an already existing type of MiniRobo is on a given side, returning
	 * the robo if it exists and null otherwise. */
		for(int i=0;i<bots.size();i++){
			if(bots.get(i).gett().equals(type)){
				if(bots.get(i).geta()==aff){
					return bots.get(i);
				}
			}
		}
		return null;
	}
	public void reelRobo(ArrayList<String> results,boolean aff,RoboPanel panel){
	/* This method creates a MiniRobo object of a specific type and level depending on the number of times
	 * a MiniRobo object of that specific type was rolled or already heals an existing MiniRobo object of
	 * the same type, with the amount healed determined in the same way */
		int shotLevel=0,tankLevel=0,laserLevel=0;
		for(int i=0;i<3;i++){ //determine the degree of which the effect will occur
			if(results.get(i).equals("ShotBot")){
				shotLevel+=1;
			}
			else if(results.get(i).equals("TankBot")){
				tankLevel+=1;
			}
			else if(results.get(i).equals("LaserBot")){
				laserLevel+=1;
			}
		}
		//SHOTBOT
		if(shotLevel!=0){//Only create a MiniRobo of that type if it was rolled
			if(aff==true){
				if(checkRobo(playerbots,"ShotBot",true)==null){
					playerbots.add(new MiniRobo("ShotBot",shotstartx,shotstarty,true,gShotPics,shotLevel));
				}
				else if(checkRobo(playerbots,"ShotBot",true)!=null){ //heal the MiniRobo by a certain degree if it already exists
					MiniRobo temp=checkRobo(playerbots,"ShotBot",true);
					if(shotLevel<=temp.getl()){
						if(shotLevel==1){
							temp.heal(5); 
						}
						else if(shotLevel==2){
							temp.heal(10); 
						}
						else if(shotLevel==3){
							temp.heal(25); 
						}
					}
					else if(shotLevel>temp.getl()){ //create the MiniRobo otherwise
						playerbots.remove(temp);
						playerbots.add(new MiniRobo("ShotBot",shotstartx,shotstarty,true,gShotPics,shotLevel));
					}
				}
			}
			if(aff==false){//enemyside
				if(checkRobo(playerbots,"ShotBot",false)==null){
					enemybots.add(new MiniRobo("ShotBot",shotstartx,shotstarty,false,bShotPics,shotLevel));
				}
				else if(checkRobo(playerbots,"ShotBot",false)!=null){
					MiniRobo temp=checkRobo(playerbots,"ShotBot",false);
					if(shotLevel<=temp.getl()){
						if(shotLevel==1){
							temp.heal(7); 
						}
						else if(shotLevel==2){
							temp.heal(15); 
						}
						else if(shotLevel==3){
							temp.heal(25); 
						}
					}
					else if(shotLevel>temp.getl()){
						enemybots.remove(temp);
						enemybots.add(new MiniRobo("ShotBot",shotstartx,shotstarty,false,bShotPics,shotLevel));
					}
				}
			}
		}
		//TANKBOT
		if(tankLevel!=0){
			if(aff==true){
				if(checkRobo(playerbots,"TankBot",true)==null){
					playerbots.add(new MiniRobo("TankBot",tankstartx,tankstarty,true,gTankPics,tankLevel));
				}
				else if(checkRobo(playerbots,"TankBot",true)!=null){
					MiniRobo temp=checkRobo(playerbots,"TankBot",true);
					if(tankLevel<=temp.getl()){
						if(tankLevel==1){
							temp.heal(7);
						}
						else if(tankLevel==2){
							temp.heal(20);
						}
						else if(tankLevel==3){
							temp.heal(30);
						}
					}
					else if(tankLevel>temp.getl()){
						playerbots.remove(temp);
						playerbots.add(new MiniRobo("TankBot",tankstartx,tankstarty,true,gTankPics,tankLevel));
					}
				}
			}
			if(aff==false){
				if(checkRobo(playerbots,"TankBot",false)==null){
					enemybots.add(new MiniRobo("TankBot",tankstartx,tankstarty,false,bTankPics,tankLevel));
				}
				else if(checkRobo(playerbots,"TankBot",false)!=null){
					MiniRobo temp=checkRobo(playerbots,"Tank",false);
					if(tankLevel<=temp.getl()){
						if(tankLevel==1){
							temp.sethp(temp.gethp()+7); 
						}
						else if(tankLevel==2){
							temp.sethp(temp.gethp()+20); 
						}
						else if(shotLevel==3){
							temp.sethp(temp.gethp()+30); 
						}
					}
					else if(tankLevel>temp.getl()){
						enemybots.remove(temp);
						enemybots.add(new MiniRobo("TankBot",tankstartx,tankstarty,false,bTankPics,tankLevel));
					}
				}
			}
		}
		//LASERBOT
		if(laserLevel!=0){
			if(aff==true){
				if(checkRobo(playerbots,"LaserBot",true)==null){
					playerbots.add(new MiniRobo("LaserBot",laserstartx,laserstarty,true,gLaserPics,laserLevel));
				}
				else if(checkRobo(playerbots,"LaserBot",true)!=null){
					MiniRobo temp=checkRobo(playerbots,"LaserBot",true);
					if(laserLevel<=temp.getl()){
						if(laserLevel==1){
							temp.heal(7); 
						}
						else if(laserLevel==2){
							temp.heal(15);
						}
						else if(shotLevel==3){
							temp.heal(25);
						}
					}
					else if(laserLevel>temp.getl()){
						playerbots.remove(temp);
						playerbots.add(new MiniRobo("LaserBot",laserstartx,laserstarty,true,gLaserPics,laserLevel));
					}
				}
			}
			if(aff==false){
				if(checkRobo(playerbots,"LaserBot",false)==null){
					enemybots.add(new MiniRobo("LaserBot",laserstartx,laserstarty,false,bLaserPics,laserLevel));
				}
				else if(checkRobo(playerbots,"LaserBot",false)!=null){
					MiniRobo temp=checkRobo(playerbots,"LaserBot",false);
					if(laserLevel<=temp.getl()){
						if(laserLevel==1){
							temp.heal(7);
						}
						else if(laserLevel==2){
							temp.heal(15);
						}
						else if(shotLevel==3){
							temp.heal(25); 
						}
					}
					else if(laserLevel>temp.getl()){
						enemybots.remove(temp);
						enemybots.add(new MiniRobo("LaserBot",laserstartx,laserstarty,false,bLaserPics,laserLevel));
					}
				}
			}
		}
	}
//Running methods-----
   	public void input(){
   		if (mouseDown==true){
   			if (decision==false){
   				if(currentPhase.equals("StartReel")){
   					rolling=true;		
   					pReel.roll();
	   				currentPhase="stopReel";
	   				decision=true;
   				}
   			}
   			if (decision==false){
   				if(currentPhase.equals("stopReel")){
   					System.out.println("Stopping");
	   				rolling=false;
	   				currentPhase="pReelEffect";				
   				}  							
   			}
   			if (incombat==false){ //allow for the combat sequence to start if the player clicks when combat is currently not occuring
   				calcAttack(playerbots,enemybots,enemy);
   				calcAttack(enemybots,playerbots,player);
   				incombat=true;
   			}   		
   		}
   		decision=false;
   	}
   	public void update(){//moves all objects on screen 
   		if(rolling==true){		
   			pReel.roll();
   		}
   		if (rolling==false){
   			pReel.stop();
   		}
   		for (int i=0;i<playerbots.size();i++){ //player robos
   			if (playerbots.get(i).gett().equals("LaserBot")){
   				if (playerbots.get(i).getf()==true){ //LaserBots only deal damage once their attack animation is finished
   					playerbots.get(i).attack(this);
   				}
   			}
   			if (playerbots.get(i).gett().equals("TankBot")){
   				if (playerbots.get(i).getattacking()==true){
   					playerbots.get(i).attack(this);
   				}
   			}
   			if (playerbots.get(i).gett().equals("ShotBot")){
   				if (playerbots.get(i).getattacking()==true){
   					if (playerbots.get(i).bullettimer()==true){ //Shotbots have to wait for their attack delay before they can shoot again
   						playerbots.get(i).attack(this);
   					}	
   				}
   			}
   			if (playerbots.get(i).alive()==false){//Remove any minirobos that are dead at any point in time 
   				playerbots.remove(i);
   			}   			
   		}
   		for (int i=0;i<enemybots.size();i++){ //enemy robos
   			if (enemybots.get(i).gett().equals("LaserBot")){
   				if (enemybots.get(i).getf()==true){
   					enemybots.get(i).attack(this);
   				}
   			}
   			if (enemybots.get(i).gett().equals("TankBot")){ 
   				if (enemybots.get(i).getattacking()==true){
   					enemybots.get(i).attack(this);
   				}
   			}
   			if (enemybots.get(i).gett().equals("ShotBot")){
   				if (enemybots.get(i).getattacking()==true){
   					if (enemybots.get(i).bullettimer()==true){
   						enemybots.get(i).attack(this);
   					}	
   				}
   			}
   			if (enemybots.get(i).alive()==false){ //remove dead enemyrobos from the game at any point in time
   				enemybots.remove(i);
   			}
   		}	
   		for (int i=0;i<bulletlist.size();i++){ //move all RoboBullets currently on screen		
   			bulletlist.get(i).move();
   			if (bulletlist.get(i).collision()){			
   				bulletlist.remove(bulletlist.get(i));
   			}
   			/*else if (bulletlist.get(i).checkInWindow(windoww,windowh)==false){
   				bulletlist.remove(bulletlist.get(i));
   			}*/
   			
   		}
   		if (incombat==true){
   		   	for (int i=0;i<playerbots.size();i++){ //combat ends when everyone has finished attacking and no bullets remaing on screen
	   			if (playerbots.get(i).getattacking()==true){
	   				break;
	   			}
	   		}
	   		for (int i=0;i<enemybots.size();i++){
	   			if (playerbots.get(i).getattacking()==true){
	   				break;
	   			}
	   		}
	   		if (bulletlist.isEmpty()==true){
	   			incombat=false;
	   		}	
   		}
   		if (afterflag==true){ //perform all after combat processes once combat has finished
   		   	afterMath(playerbots,enemybots);
   		   	afterflag=false;
   		   		
   		}
   	}
//Calculation methods-----
    public void HTDsortRobots(ArrayList<MiniRobo>robos){ //method that sorts a side's MiniRobos based on attack power in descending order
    	Collections.sort(robos);
    }
    public void HIDsortRobots(ArrayList<MiniRobo>robos){ //method that sorts a side's MiniRobos based on instant attack power in descending order
    	Collections.sort(robos,MiniRobo.HIDComparator);
    }
    
    public void calcAttack(ArrayList<MiniRobo>attackers,ArrayList<MiniRobo>targets,MainRobo mainTarget){
    /* This method performs the attack phase's calculations in order to determine which robos attack which. 
     * It will assign each attacking robot a target based on the target with the current highest attack power. 
     * If a target were to die, the next attacking robot will choose the next highest power target */
    	ArrayList<MiniRobo>testTargets= new ArrayList<MiniRobo>(); //hypothetical list of defending robos that will be attacked 
    	HIDsortRobots(attackers); //order attackers and targets by highest instance power to avoid mistakes in calculation
    	HTDsortRobots(targets); //order the targets by the highest total power to priortize the biggest threats
    	for (int i=0;i<targets.size();i++){ //create a list of robots that will be hypothetically attacked in order to do calculations
    		MiniRobo current= targets.get(i);
    		MiniRobo testRobo= new MiniRobo(current.gett(),current.getx(),current.gety(),current.geta(),current.getpics(),current.getl());
    		testRobo.sethp(targets.get(i).gethp());
    		testTargets.add(testRobo);
    	}
    	for (int i=0;i<attackers.size();i++){ //each attacker will go through the possible targets, 
    		if (testTargets.isEmpty() && mainTarget.alive()){//attack the main target if they have no defending MiniRobos
    			    if (attackers.get(i).gett().equals("ShotBot")){ //ShotBot will loop through the available targets for each bullet that it has
    					calcShot(attackers.get(i),mainTarget);
    				}
    				else{
    					attackers.get(i).planattack(mainTarget);    					
    				}
    		}
    		for (int j=0;j<testTargets.size();j++){
    			if (testTargets.get(testTargets.size()-1).alive()==false){ //attack the opposing MainRobo if all enemy MiniRobos have died this combat sequence
				    if (attackers.get(i).gett().equals("ShotBot")){
    					calcShot(attackers.get(i),mainTarget);
    				}
    				else{
    					attackers.get(i).planattack(mainTarget);    					
    				}
    			}
    			else if (testTargets.get(j).gethp()>0 && attackers.get(i).getattacking()==false){ //only attack the test target if it is not already dead and if a target has not been chosen yet
    				if (attackers.get(i).gett().equals("ShotBot")){
    					if (attackers.get(i).getbn()>0){
							calcShot(attackers.get(i),testTargets,targets,mainTarget);
						}	
					}
					else{
						testTargets.get(j).takeDamage(attackers.get(i).getp());
						if (testTargets.get(j).gethp()<=0){ //give bonus exp for sucessfully killing a target
							attackers.get(i).giveKillExp(testTargets.get(j));
						} 
    					attackers.get(i).planattack(targets.get(j)); //set the attacking robot's actual target to the one that it was tested to attack	
					}
    			}
    		}
    	}
    }
    public void calcShot(MiniRobo shot,ArrayList<MiniRobo>targets,ArrayList<MiniRobo>realTargets,MainRobo mainTarget){
    /*Specialized attack calculation method for ShotBot to calculate each of its bullet's targets seperately*/
    	for (int i=0;i<shot.getbn();i++){
    		for (int j=0;j<targets.size();j++){
    			if (targets.get(j).alive()){
    				targets.get(j).takeDamage(shot.getbp()); //Damage is dealt based on each individual bullet's power, not the ShotBot's total power
    				if (targets.get(j).gethp()<=0){
						shot.giveKillExp(targets.get(j));
					} 
    				shot.planattack(realTargets.get(j));
    				break;
    			}
    			if (targets.get(targets.size()-1).alive()==false && mainTarget.alive()){ //proceed to attack opposing MainRobot if all targets are dead
    				shot.planattack(mainTarget);
    			}
    		}
    	}
    }
    public void calcShot(MiniRobo shot, MainRobo mainTarget){ //overloaded ShotBot calculation for MainRobo
    	for (int i=0;i<shot.getbn();i++){
    		if (mainTarget.alive()){
    			shot.planattack(mainTarget);	
    		}
    	}
    }
    public void afterMath(ArrayList<MiniRobo>goodguys, ArrayList<MiniRobo>badguys){
    /* This methods deals with all calculations after the combat sequence is finished. This
     * includes awarding exp to surviving MiniRobo and deciding who's turn it will be next */
    	for (int i=0;i<goodguys.size();i++){
    		if (goodguys.get(i).alive()){
    			if (goodguys.get(i).gete()>0){ //only check experience if MiniRobo participated in combat
    				goodguys.get(i).expCheck();
    			}
    		}
    	}
    	for (int i=0;i<badguys.size();i++){
    		if (badguys.get(i).alive()){
    			if (badguys.get(i).gete()>0){
    				badguys.get(i).expCheck();
    			}
    		}
    	}
    }
//Creation methods-----
    public void makeBullet(MiniRobo shooter, GenRobo target,int speed){ //make RoboBullets that fly at a specific target
    	if (shooter.geta()==true){ //playerside
    		if (shooter.gett()=="ShotBot"){ //Image of a RoboBullet changes depending on the type of it's shooter
    			RoboBullet bullet= new RoboBullet(shooter,target,speed,gShotBulletPic);
    			bulletlist.add(bullet);
    		}
    		if (shooter.gett()=="TankBot"){
    			RoboBullet bullet= new RoboBullet(shooter,target,speed,gTankBulletPic);
    			bulletlist.add(bullet);
    		}
    	}
    	else{ //enemyside
    		if (shooter.gett()=="ShotBot"){
    			RoboBullet bullet= new RoboBullet(shooter,target,speed,bShotBulletPic);
    			bulletlist.add(bullet);
    		}
    		if (shooter.gett()=="TankBot"){
    			RoboBullet bullet= new RoboBullet(shooter,target,speed,bTankBulletPic);
    			bulletlist.add(bullet);
    		}
    	}
	}
//Drawing methods-----
	public void drawReel(Graphics g){//displays both side's reels
    	pReel.drawReel(g,this);
    	eReel.drawReel(g,this);
    }
	public void drawHealth(Graphics2D g,MainRobo main){ //displays a MainRobo's health points
		BufferedImage healthbar,currentbar;
		if (main.geta()==true){
			healthbar=gEmptyHealthPic; 
			currentbar=gFullHealthPic;	
		}
		else{
			healthbar=bEmptyHealthPic;
			currentbar=bFullHealthPic;
		}
		int healthbarw=healthbar.getWidth();
		int healthbarh=healthbar.getHeight();
    	double percenthealth=((double)main.gethp()/(double)main.getmaxhp()); //percentage of the health bar that will be filled (percentage of health remaining)
    	g.drawImage(healthbar,(main.getx()+main.getw()/2)-healthbarw/2-1,windowh-70,this); //-1 and -70 are for centering purposes(magical numbers) 
    	if (percenthealth*100>0){
    		currentbar=currentbar.getSubimage(0,0,(int)(percenthealth*healthbarw),healthbarh);
    		g.drawImage(currentbar,(main.getx()+main.getw()/2)-healthbarw/2-1,windowh-70,this);
    	}
    }
    public void drawLaser(Graphics2D g,MiniRobo robo){ //draws a laser created by a LaserBot
    	if (robo.geta()==true){ //playerside
    		robo.drawLaser(g,gLaserBeamPic,this);
    	}
    	
    	else{ //enemyside
    		robo.drawLaser(g,bLaserBeamPic,this);
    	}
    }
    public void drawBullet(Graphics2D g,RoboBullet bullet){ //draws a bullet created by a MiniRobo
    	bullet.drawBullet(g,this);
    }
    @Override
    public void paintComponent(Graphics g1d){ //paints the window
    	Graphics2D g= (Graphics2D)g1d;
    	g.setColor(new Color(0,0,0));
    	g.fillRect(groundx,groundy,1080,120); //ground
    	g.setColor(new Color(51,255,255));
    	g.fillRect(0,0,1080,600);
    	drawReel(g);

    	if (player.alive()){ //only draw player MainRobo if it is alive
    		player.drawRobo(g,this);
    	}
    	if (enemy.alive()){ //only draw enemy MainRobo if it is alive
    		enemy.drawRobo(g,this);
    	}
		drawHealth(g,player);
		drawHealth(g,enemy);
		
    	for (int i=0;i<playerbots.size();i++){ //get lasers to draw on top of robo bodies
    		if (playerbots.get(i).alive()){
    			playerbots.get(i).drawRobo(g,this);
    		}
    	}
    	for (int i=0;i<enemybots.size();i++){
    		if (enemybots.get(i).alive()){
    			enemybots.get(i).drawRobo(g,this);
    		}
    	}
    	for (int i=0;i<playerbots.size();i++){ //draw laser animations on the player side
    		if (playerbots.get(i).gett().equals("LaserBot")){
    			if (playerbots.get(i).getattacking()==true){ 
					if (playerbots.get(i).lasertimer()==false){
						drawLaser(g,playerbots.get(i));
					}	
    			}
    		}
    	}
    	for (int i=0;i<enemybots.size();i++){ //draw laser animations on the enemy side
    		if (enemybots.get(i).gett().equals("LaserBot")){
    			if (enemybots.get(i).getattacking()==true){ 
					if (enemybots.get(i).lasertimer()==false){
						drawLaser(g,enemybots.get(i));
					}	
    			}
    		}
    	}
    	for (int i=0;i<bulletlist.size();i++){
    		drawBullet(g,bulletlist.get(i));
    	}
    }
}
//"If Loop" - Andy Chen Li, 2015
/*if(in doubt){
/*	loop;
 *}
 *-Dr. Dreronha */