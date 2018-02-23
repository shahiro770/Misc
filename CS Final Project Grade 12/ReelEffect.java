/**
 * @(#)ReelEffect.java
 *
 *
 * @author 
 * @version 1.00 2015/6/4
 *
 * hack DONE
 * shock - blargh bullet animation things
 * energy leak DONE - make flag to reset after turn
 * laser - MORE bullet animation things
 * firewall - how to not let robots be targeted..
 * microwave - I don't get how to use the bullets agh
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*; 
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;
public class ReelEffect{
	
	Random rand=new Random();
	boolean drawShock=false,drawLaser=false,drawFirewall=false,drawMicrowave=false;
	int hackLevel=0,shockLevel=0,energyLevel=0,laserLevel=0,firewallLevel=0,microwaveLevel=0;
	int shockPow,microPow;
	WallRobo firewall;
	GenRobo laserTarget,microTarget;
	ArrayList<MiniRobo>targets=new ArrayList<MiniRobo>();
	
    public ReelEffect(ArrayList<String> results,boolean aff,RoboPanel panel){
		for(int i=0;i<3;i++){
			if(results.get(i).equals("Hack")){
				hackLevel+=1;
			}
			else if(results.get(i).equals("Shock")){
				shockLevel+=1;
			}
			else if(results.get(i).equals("Energy")){
				energyLevel+=1;
			}
			else if(results.get(i).equals("Laser")){
				laserLevel+=1;
			}
			else if(results.get(i).equals("Firewall")){
				firewallLevel+=1;
			}
			else if(results.get(i).equals("Microwave")){
				microwaveLevel+=1;
			}
		}
		if(hackLevel!=0){
    		hack(aff,hackLevel,panel);
    	}
    	if(shockLevel!=0){
    		shock(aff,shockLevel,panel);
    		drawShock=true;
    	}
    	if(energyLevel!=0){
    		energy(aff,energyLevel,panel);
    	}
    	if(laserLevel!=0){
    		laser(aff,laserLevel,panel);
    		drawLaser=true;
    	}
    	if(firewallLevel!=0){
    		firewall(aff,firewallLevel,panel);
    		drawFirewall=true;
    	}
    	if(microwaveLevel!=0){
    		microwave(aff,microwaveLevel,panel);
    		drawMicrowave=true;
    	}
    }
    
    public void drawEffect(Graphics2D g,RoboPanel panel,MainRobo main){
    	if(drawShock==true){
    		for(GenRobo target:targets){
	    		EffectShot shock=new EffectShot(main,target,4,"Shock",shockPow);
	    		shock.drawEffectShot(g);	    		
	    	}
    	}
    	if(drawLaser==true){
    		//Graphics2D g,GenRobo shooter, GenRobo target
    		drawLaser(g,main,laserTarget);
    	}
    	if(drawFirewall==true){
    		firewall.drawRobo(g,panel);
    	}
    	if(drawMicrowave==true){
    		EffectShot microwave=new EffectShot(main,microTarget,3,"Microwave",microPow);
    		microwave.drawEffectShot(g);
    	}
    }
    
    private void hack(boolean aff, int level,RoboPanel panel){
    	int chance=0;
    	MiniRobo max=null;
    	if(aff==true){
    		if(panel.getEbots().size()>0){
    			if(level==1){
    				chance=14*panel.getPbots().size();
    			}
    			if(level==2){
    				chance=28*panel.getPbots().size();
    			}
    			if(level==3){
    				chance=41*panel.getPbots().size();
    			}
    		}
    	}
    	else if(aff==false){
    		if(panel.getPbots().size()>0){
    			if(level==1){
    				chance=14*panel.getEbots().size();
    			}
    			if(level==2){
    				chance=28*panel.getEbots().size();
    			}
    			if(level==3){
    				chance=41*panel.getEbots().size();
    			}
    		}
    	}
    	
    	if(chance!=0){
    		if(chance>100){
	    		chance=100;
	    	}
	    	int r=rand.nextInt(chance)+1;
	    	if(r<=chance){
	    		if(aff=true){
	    			max=panel.getEbots().get(0);
	    			for(MiniRobo i : panel.getEbots()){
	    				if(i.getl()>=max.getl()){
	    					max=i;
	    				}
	    			}		
	    		}
	    		else if(aff=false){
	    			max=panel.getPbots().get(0);
	    			for(MiniRobo j : panel.getPbots()){
	    				if(j.getl()>=max.getl()){
	    					max=j;
	    				}
	    			}		
	    		}
	    		if(max!=null){
	    			if(max.geta()==true){
			    		max.seta(false);
			    		panel.getPbots().remove(max);
			    		panel.getEbots().add(max);
			    	}
			    	if(max.geta()==false){
			    		max.seta(true);
			    		panel.getEbots().remove(max);
			    		panel.getPbots().add(max);
			    	}
	    		}
	    	}
    	}    	
    }
    
    private void shock(boolean aff, int level,RoboPanel panel){
    	MiniRobo max;
    	if(aff==true){
    		max=panel.getEbots().get(0);
    		while(targets.size()<panel.getEbots().size()){
	    		for(MiniRobo i:panel.getEbots()){
	    			if(i.getp()>max.getp()){
	    				max=i;
	    				targets.add(max);
	    			}
	    		}
    		}
    	}
    	else if(aff==false){
    		max=panel.getPbots().get(0);
    		while(targets.size()<panel.getPbots().size()){
	    		for(MiniRobo i:panel.getPbots()){
	    			if(i.getp()>max.getp()){
	    				max=i;
	    				targets.add(max);
	    			}
	    		}
    		}
    	}
    	if(level==1){
    		for(int i=1;i<targets.size();i++){
    			targets.remove(i);
    			shockPow=5;
    		}
    	}
    	if(level==2){
    		for(int i=1;i<targets.size();i++){
    			targets.remove(i);
    			shockPow=7;
    		}
    	}
    	if(level==3){
    		for(int i=3;i<targets.size();i++){
    			targets.remove(i);
    			shockPow=9;
    		}
    	}
    }
    
    private void energy(boolean aff, int level,RoboPanel panel){
    	if(level==1){
    		if(aff==true){
    			for(MiniRobo i:panel.getPbots()){
    				i.setp(i.getp()+2);
    			}
    			for(MiniRobo j:panel.getEbots()){
    				j.setp(j.getp()-2);
    			}
    		}
    		else if(aff==false){
    			for(MiniRobo i:panel.getEbots()){
    				i.setp(i.getp()+2);
    			}
    			for(MiniRobo j:panel.getPbots()){
    				j.setp(j.getp()-2);
    			}
    		}    		
    	}
    	else if(level==2){
    		if(aff==true){
    			for(MiniRobo i:panel.getPbots()){
    				i.setp(i.getp()+3);
    			}
    			for(MiniRobo j:panel.getEbots()){
    				j.setp(j.getp()-3);
    			}
    		}
    		else if(aff==false){
    			for(MiniRobo i:panel.getEbots()){
    				i.setp(i.getp()+3);
    			}
    			for(MiniRobo j:panel.getPbots()){
    				j.setp(j.getp()-3);
    			}
    		}   		
    	}
    	else if(level==3){
    		if(aff==true){
    			for(MiniRobo i:panel.getPbots()){
    				i.setp(i.getp()+5);
    			}
    			for(MiniRobo j:panel.getEbots()){
    				j.setp(j.getp()-5);
    			}
    		}
    		else if(aff==false){
    			for(MiniRobo i:panel.getEbots()){
    				i.setp(i.getp()+5);
    			}
    			for(MiniRobo j:panel.getPbots()){
    				j.setp(j.getp()-5);
    			}
    		}    		
    	}
    	panel.setEnergyL(level);
    	panel.setEnergy(true);			// fields from RoboPanel
    }
    
    private void laser(boolean aff, int level,RoboPanel panel){
    	if(aff==true){
    		if(panel.getEbots().size()>0){
    			laserTarget=panel.getEbots().get(0);
	    		for(MiniRobo i:panel.getEbots()){
	    			if(i.getp()>laserTarget.getp()){
	    				laserTarget=i;
	    			}
	    		}
    		}
    		else{
    			laserTarget=panel.getEmain();
    		}
    		
    	}
    	else if(aff==false){
    		if(panel.getPbots().size()>0){
    			laserTarget=panel.getPbots().get(0);
	    		for(MiniRobo i:panel.getPbots()){
	    			if(i.getp()>laserTarget.getp()){
	    				laserTarget=i;
	    			}
	    		}
    		}
    		else{
    			laserTarget=panel.getPmain();
    		}
    		
    	}
    }
    
    private void firewall(boolean aff, int level,RoboPanel panel){
    	//String type,int robox, int roboy,boolean aff,ArrayList<Image> piclist,int level
    	firewall=new WallRobo("WallRobo",0,0,aff,null,level);
    	if(aff==true){
    		for(MiniRobo i:panel.getEbots()){
    			i.planattack(firewall);
    		}
    	}
    	if(aff==false){
    		for(MiniRobo i:panel.getPbots()){
    			i.planattack(firewall);
    		}
    	}
    }
    
    private void microwave(boolean aff, int level,RoboPanel panel){
    	if(level==1){
    		microPow=6;
    	}
    	else if(level==2){
    		microPow=12;
    	}
    	else if(level==3){
    		microPow=30;
    	}
    	if(aff==true){
    		if(panel.getEbots()!=null){
    			microTarget=panel.getEbots().get(0);
    		}
    		else{
    			microTarget=panel.getEmain();
    		}
    	}
    	else if(aff==false){
    		if(panel.getEbots()!=null){
    			microTarget=panel.getPbots().get(0);
    		}
    		else{
    			microTarget=panel.getPmain();
    		}
    	}

    }
    
    private void drawLaser(Graphics2D g,GenRobo shooter, GenRobo target){ //Draws a laser beam attack
    	//if (targetlist.isEmpty()==false){
    		AffineTransform transform= new AffineTransform(); //fancy shmancy object that rotates stuff using a complicated math formula
	    	int tx=target.getx()+target.getw()/2; //gets the centre of the target (x value)
			int ty=target.gety()+target.geth()/2; //gets the centre of the target (y value)
			int dx= Math.abs((shooter.getx()+shooter.getw()/2)-tx); //x distance from target to robo
			int dy= Math.abs((shooter.gety()+shooter.geth()/2)-ty); //y distance from target to robo
			
			int hyp= (int)(Math.sqrt(dx*dx+dy*dy)); //hypoteneuse 
			Rectangle rect= new Rectangle(0,0,hyp,30); //draws the rect with no translations or changes (this is why it is at 0,0 as a start location)
	    	double angle= Math.atan2(dy,dx); //tan inverse to get the angle
	    	transform.translate(shooter.getx()+shooter.getw()/2,shooter.gety()+shooter.geth()/2); //translates to the rect to where it should be before being rotated 
	    	if (shooter.getx()<=tx){//rotate the rect differently depending on which side the robo is on 
	    		transform.rotate((Math.PI*2)-angle); //playerside
	    	}
	    	else{
	    		transform.rotate(((Math.PI*2)-angle)*-1+Math.PI);//enemyside
	    	}
	    	Shape rotatedRect= transform.createTransformedShape(rect); //creates the shape after being rotated 
	    	//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); // Anti-alias on
	    	//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.05f));
	    	g.fill(rotatedRect);// draws the rect
	    	//for (int i=0;i<10;i++){
	    	//	g.setStroke(new BasicStroke(20-i));
	    	//	g.drawLine(getx(),gety(),tx,ty);
	    	//}
	    	//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
    	//}
	}
}


/*public class WallRobo extends GenRobo{
	public WallRobo(int level,boolean aff){
		if(level==1){
    		hp=15;
    	}
    	else if(level==2){
    		hp=25;
    	}
    	else if(level==3){
    		hp=55;
    	}
    	
    	if(aff==true){
    		robox=450;
    		roboy=300;
    	}
    	else if(aff==false){
    		robox=850;
    		roboy=300;
    	}
	}
}*/
/*
public class EffectShot(){
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
		attpow=pow;
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
}*/