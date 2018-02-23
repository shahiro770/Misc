/**
 * @(#)SpriteManager.java
 *
 *
 * @Shahir Chowdhury
 * @version 1.00 2015/6/2
 */
 
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SpriteManager {
	private Image BPic,EPic,RAPic,LAPic,RLPic,LLPic,MAPic;
	private ImageIcon IBPic,IEPic,IRAPic,ILAPic,IRLPic,ILLPic,IMAPic;
	String animtype="Idle"; //current cycle of animation that the GenRobo is in

	private int bxdirection; //flag for what direction the GenRobo's body is moving in horizontally (1 is right, -1 is left)
	private int bydirection; //flag for what direction the GenRobo's body is moving in vertically (true is down, false is up)
	private int axdirection; //animation direction for arms horizontally
	private int aydirection; //animation direction for arms vertically
	private int maxdirection; //animation direction for middle appendage horizontally
	private int maydirection; //animation direction for middle appendage vertically
	private int exdirection; //animation direction for eyes horizontally
	private int eydirection; //animation direction for eyes vertically
	private int lxdirection;
	private int lydirection;
	
	private final int DOWN=1;
	private final int UP=-1;
	private final int RIGHT=1;
	private final int LEFT=-1;
	private final int OUT=2;
	private final int IN=-2;
	private final int NULL=0;
	
	private int robox;
	private int roboy;
	private int roboh;
	private int robow;
	
	private int BPicx;
   	private int BPicy;
   	private int EPicx;
   	private int EPicy;
   	private int RAPicx;
   	private int RAPicy;
   	private int LAPicx;
   	private int LAPicy;
   	private int LLPicx;
   	private int LLPicy;
   	private int RLPicx;
   	private int RLPicy;
   	private int MAPicx;
   	private int MAPicy;
   	
   	private int maxbx;//maximum and minimum x values the GenRobo's body can travel for its animations
	private int minbx;
	private int maxby;//maximum and minimum y values the GenRobo's body can travel for its animations
	private int minby;
	private int maxax;
	private int minax;
	private int maxay;
	private int minay;
	private int maxmax;
	private int minmax;
	private int maxmay;
	private int minmay;
	private int maxex;
	private int minex;
	private int maxey;
	private int miney;
	private int maxlx;
	private int minlx;
	private int maxly;
	private int minly;
	 
	private int animcounter; //counter to time animations
	private int specialcount=0; //counter for number of times eye moves up or down during special animation for ShotBot
	
	private GenRobo robo;
	
    public SpriteManager(GenRobo robo,ArrayList<Image> piclist) {
    	this.robo=robo;
    	robox=robo.getx();
    	roboy=robo.gety();
    	roboh=robo.geth();
    	robow=robo.getw();
    	BPic=piclist.get(0); 
    	EPic=piclist.get(1); 	
     	IBPic= new ImageIcon(piclist.get(0));//temporarily create an image icon to get x and y positions 	
    	IEPic= new ImageIcon(piclist.get(1));
    	if (robo.geta()==true){
    		RAPic=piclist.get(2);
    		LAPic=piclist.get(3);
    		IRAPic= new ImageIcon(piclist.get(2));
    		ILAPic= new ImageIcon(piclist.get(3));  
    		if (robo.gett().equals("LaserBot") || robo.gett().equals("MainBot")){
    			RLPic=piclist.get(4);
	    		LLPic=piclist.get(5);
	    		IRLPic= new ImageIcon(piclist.get(4));
	    		ILLPic= new ImageIcon(piclist.get(5));
    		}
    	}
    	else{
    		if (robo.gett().equals("LaserBot") || robo.gett().equals("MainBot")){
    			RLPic=piclist.get(2);
	    		LLPic=piclist.get(3);
	    		IRLPic= new ImageIcon(piclist.get(2));
	    		ILLPic= new ImageIcon(piclist.get(3));
    		}
    		else{
    			RAPic=piclist.get(2);
	    		LAPic=piclist.get(3);
	    		IRAPic= new ImageIcon(piclist.get(2));
	    		ILAPic= new ImageIcon(piclist.get(3));  
    		}
    	}
    	//if robo type is not tank bot, load the third arm
    	if (robo.geta()==false){
    		if (!robo.gett().equals("TankBot")){
    			MAPic=piclist.get(4);
    			IMAPic=new ImageIcon(piclist.get(4));
    		}
    	}
    	
    	setAnimation("Idle");
    	if (robo.geta()==true){
    		if (robo.gett().equals("MainBot")){
	    		BPicx=robox; 
	    		BPicy=roboy;
	    		EPicx=(robox+robow/2)-IEPic.getIconWidth()/2+14;
	    		EPicy=(roboy+roboh/2)-103;
	    		RAPicx=(robox+robow/2)+57;
	    		RAPicy=(roboy+roboh/2)+100;
	    		LAPicx=(robox+robow/2)-(ILAPic.getIconWidth()+48);
	    		LAPicy=(roboy+roboh/2)+95;
	    		RLPicx=(robox+robow/2)+12;
	    		RLPicy=(roboy+roboh/2)+122;
	    		LLPicx=(robox+robow/2)-(ILAPic.getIconWidth()+10);
	    		LLPicy=(roboy+roboh/2)+122;
    		}
	    	if (robo.gett().equals("LaserBot")){
	    		BPicx=robox; 
	    		BPicy=roboy;
	    		EPicx=(robox+robow/2)-IEPic.getIconWidth()/2+7;
	    		EPicy=(roboy+roboh/2)-36;
	    		RAPicx=(robox+robow/2)+37;
	    		RAPicy=(roboy+roboh/2)+20;
	    		LAPicx=(robox+robow/2)-(ILAPic.getIconWidth()+37);
	    		LAPicy=(roboy+roboh/2)+20;
	    		RLPicx=(robox+robow/2)+10;
	    		RLPicy=(roboy+roboh/2)+30;
	    		LLPicx=(robox+robow/2)-(ILLPic.getIconWidth()+10);
	    		LLPicy=(roboy+roboh/2)+30;
	    		
	    	}
	    	if (robo.gett().equals("ShotBot")){
	    		BPicx=robox; 
	    		BPicy=roboy;
	    		EPicx=(robox+robow/2)-IEPic.getIconWidth()/2+3;
	    		EPicy=(roboy+roboh/2)+9;
	    		RAPicx=(robox+robow/2)+18;
	    		RAPicy=roboy+roboh+4;
	    		LAPicx=(robox+robow/2)-(ILAPic.getIconWidth()+18);
	    		LAPicy=roboy+roboh+4; 		
	    	}
	    	if (robo.gett().equals("TankBot")){
	    		BPicx=robox; 
	    		BPicy=roboy;
	    		EPicx=(robox+robow/2)-IEPic.getIconWidth()/2;
	    		EPicy=(roboy+roboh/2)-20;
	    		RAPicx=(robox+robow/2)+60;
	    		RAPicy=roboy+roboh/2;
	    		LAPicx=(robox+robow/2)-(ILAPic.getIconWidth()+60);
	    		LAPicy=roboy+roboh/2;
	    	}
    	} 
    	if (robo.geta()==false){
    		if (robo.gett().equals("ShotBot")){
	    		BPicx=robox; 
	    		BPicy=roboy;
	    		EPicx=(robox+robow/2)-IEPic.getIconWidth()/2-7;
	    		EPicy=(roboy+roboh/2)-6;
	    		RAPicx=(robox+robow/2)+5;
	    		RAPicy=(roboy+roboh/2)+24;
	    		LAPicx=(robox+robow/2)-(ILAPic.getIconWidth()+5);
	    		LAPicy=(roboy+roboh/2)+24;
	    		MAPicx=(robox+robow/2)-8;
	    		MAPicy=(roboy+roboh/2)+24; 		
	    	}
	    	if (robo.gett().equals("TankBot")){
	    		BPicx=robox; 
	    		BPicy=roboy;
	    		EPicx=(robox+robow/2)-IEPic.getIconWidth()/2;
	    		EPicy=(roboy+roboh/2)-40;
	    		RAPicx=(robox+robow/2)+25;
	    		RAPicy=roboy+roboh/2-55;
	    		LAPicx=(robox+robow/2)-(ILAPic.getIconWidth()+25);
	    		LAPicy=roboy+roboh/2-55;	    	
	    	}
	    	if (robo.gett().equals("LaserBot")){
	    		BPicx=robox; 
	    		BPicy=roboy;
	    		EPicx=(robox+robow/2)-IEPic.getIconWidth()/2-15;
	    		EPicy=(roboy+roboh/2)-12;
	    		RLPicx=(robox+robow/2)+26;
	    		RLPicy=(roboy+roboh/2)+16;
	    		LLPicx=(robox+robow/2)-(ILLPic.getIconWidth()+23);
	    		LLPicy=(roboy+roboh/2)+16;
	    		MAPicx=(robox+robow/2)-12;
	    		MAPicy=(roboy+roboh/2)+13; 	
	    		
	    	}
	    	if (robo.gett().equals("MainBot")){
	    		BPicx=robox; 
	    		BPicy=roboy;
	    		EPicx=(robox+robow/2)-IEPic.getIconWidth()/2;
	    		EPicy=(roboy+roboh/2)-90;
	    		RLPicx=(robox+robow/2)+40;
	    		RLPicy=(roboy+roboh/2)+60;
	    		LLPicx=(robox+robow/2)-(ILLPic.getIconWidth()+40);
	    		LLPicy=(roboy+roboh/2)+60;
	    		MAPicx=(robox+robow/2)-(IMAPic.getIconWidth()/2);
	    		MAPicy=(roboy+roboh/2)+60; 			
	    	}
    	}
    	
    	setAnimation("Idle"); 
    	setDirection();
    }
    public void setDirection(){
    	int chance=(int)(Math.random()*100);
    	if (chance<50){ //create a method that decides start direction for you
    		if (robo.geta()==true){
	    		if (robo.gett().equals("ShotBot")){
	    			bxdirection=LEFT;
	    			exdirection=LEFT;
	    			axdirection=LEFT;
	
	    		}
	    		bydirection=UP;
	    		eydirection=UP;
	    		aydirection=UP;	
    		}
			if (robo.geta()==false){
    			if (!robo.gett().equals("TankBot") && !robo.gett().equals("LaserBot")){
	    			maydirection=DOWN;
	    		}
	    		if (robo.gett().equals("MainBot")){
	    			lydirection=UP;
	    		}
	    		bydirection=UP;
	    		eydirection=UP;
	    		aydirection=UP;	
	    		if (robo.gett().equals("TankBot")){
	    			axdirection=IN;
	    		}
    		}		
    	}
    	else{
    		if (robo.geta()==true){
    			if (robo.gett().equals("ShotBot")){
	    			bxdirection=RIGHT;
	    			exdirection=RIGHT;
	    			axdirection=RIGHT;
    			}
    			bydirection=DOWN;
    			eydirection=DOWN;
    			aydirection=DOWN;	
    		}
    		if (robo.geta()==false){
    			if (!robo.gett().equals("TankBot") && !robo.gett().equals("LaserBot")){
	    			maydirection=UP;
	    		}
	    		if (robo.gett().equals("MainBot")){
	    			lydirection=DOWN;
	    		}
	    		if (robo.gett().equals("TankBot")){
	    		//else{
	    			axdirection=OUT;
	    			
	    		}
	    		bydirection=DOWN;
	    		eydirection=DOWN;
	    		aydirection=DOWN; //laser and main don't have any arms	
    		}	
    	}
    }
    public void animateRobo(int frame){
    	if (robo.geta()==true){
	    	if (animcounter%(frame*10)==0 && animcounter!=0 && animtype.equals("Idle") && robo.gett().equals("ShotBot")){
	    	    if (BPicx==robox){
	    			int chance=(int)(Math.random()*100);
	    			if (chance<25){
	    				resetAnimation();
	    				setAnimation("Special Idle");
	    			}
	    	    }
	    	}	
    	}
    	if (animcounter%frame==0 && animcounter!=0){ 
    		checkAnimation();
	    	animateEyes();	
	    	animateBody();
	    	animateArms();
    		if (robo.geta()==false){
    			if (!robo.gett().equals("TankBot") && !robo.gett().equals("LaserBot")){
    				animateMiddle();	
    			}
 		    	if (robo.gett().equals("MainBot")){
	    		animateLegs();
    			} 
    		}
	   	}
    	animcounter+=1;
    }
    public void setAnimation(String set){
    	animtype=set;
    	if (robo.geta()==true){
	    	if (robo.gett().equals("ShotBot")){
		    	if (animtype.equals("Idle")){
		    		maxbx=robox+5;
		    		minbx=robox-5;
		    	    maxby=roboy+3;
		    		minby=roboy-2;
		    		maxex=EPicx+5;
		    		minex=EPicx-5;
		    		maxey=EPicy+3;
		    		miney=EPicy-2;
		    		maxax=RAPicx+5; //base the maximum and minimum x values off of one arm since both arms are moving in the same direction
		    		minax=RAPicx-5;
		    		maxay=RAPicy+2;
		    		minay=RAPicy-6;
		    	}
		    	else if (animtype.equals("Special Idle")){
				   	miney=EPicy-7;
				   	EPicy-=1;
				   	exdirection=NULL;
				   	eydirection=UP;
				   	bxdirection=NULL;
				   	bydirection=NULL;
				   	axdirection=NULL;
				   	aydirection=NULL;
		    	}
	    	}
	    	if (robo.gett().equals("TankBot")){
	    		//maxbx=robox+5;
	    		//minbx=robox-5;
	    	    maxby=roboy+5;
	    		minby=roboy-5;
	    		//maxex=EPicx+5;
	    		//minex=EPicx-5;
	    		maxey=EPicy+5;
	    		miney=EPicy-5;
	    		//maxax=RAPicx+5; //base the maximum and minimum x values off of one arm since both arms are moving in the same direction
	    		//minax=RAPicx-5;
	    		maxay=RAPicy+2;
	    		minay=RAPicy-6;
	    	}
	    	if (robo.gett().equals("LaserBot")){
	    		//maxbx=robox+5;
	    		//minbx=robox-5;
	    	    maxby=roboy+6;
	    		minby=roboy-2;
	    		//maxex=EPicx+5;
	    		//minex=EPicx-5;
	    		maxey=EPicy+6;
	    		miney=EPicy-2;
	    		//maxax=RAPicx+5; //base the maximum and minimum x values off of one arm since both arms are moving in the same direction
	    		//minax=RAPicx-5;
	    		maxay=RAPicy+6;
	    		minay=RAPicy-2;
	    		maxlx=RLPicx+2;
	    		minlx=RLPicx-2;
	    		//maxly=RLPicy+3;
	    		//minly=RLPicy-2;	
	    	}
	    	if (robo.gett().equals("MainBot")){
	    	    maxby=roboy+1;
	    		minby=roboy-10;
	    		maxey=EPicy+1;
	    		miney=EPicy-10;
	    		maxay=RAPicy+1;
	    		minay=RAPicy-5;
	    		maxlx=RLPicx+2;
	    		minlx=RLPicx-2;

	    	}
    	}
    	else{
    		if (robo.gett().equals("ShotBot")){
		    	if (animtype.equals("Idle")){
		    	    maxby=roboy+3;
		    		minby=roboy-2;
		    		maxey=EPicy+3;
		    		miney=EPicy-2;
		    		maxay=RAPicy+3;
		    		minay=RAPicy-3;
		    		maxmay=MAPicy+3;
		    		minmay=MAPicy-3;
		    	}
    		}
    		if (robo.gett().equals("TankBot")){
	    		//maxbx=robox+5;
	    		//minbx=robox-5;
	    	    maxby=roboy+5;
	    		minby=roboy-5;
	    		//maxex=EPicx+5;
	    		//minex=EPicx-5;
	    		maxey=EPicy+5;
	    		miney=EPicy-5;
	    		maxax=RAPicx+5; //base the maximum and minimum x values off of one arm since both arms are moving in the same direction
	    		minax=RAPicx-5;
	    		maxay=RAPicy+5;
	    		minay=RAPicy-5;
	    	}
	    	if (robo.gett().equals("LaserBot")){
	    	    maxby=roboy+6;
	    		minby=roboy-2;
	    		maxey=EPicy+6;
	    		miney=EPicy-2;
	    		maxlx=RLPicx+2;
	    		minlx=RLPicx-2;	
	    	}
	    	if(robo.gett().equals("MainBot")){
	    		maxby=roboy+3;
	    		minby=roboy-3;
	    		maxey=EPicy+3;
	    		miney=EPicy-3;
	    		maxly=RLPicy+6;
	    		minly=RLPicy-6;
	    		maxmay=MAPicy+6;
	    		minmay=MAPicy-6;
	    	}
    	}	
    }
    public void checkAnimation(){
    	if (animtype.equals("Special Idle")){
			if (robo.gett().equals("ShotBot") && robo.geta()==true){
				if (EPicy==(roboy+roboh/2)+9){
		   			if (specialcount==1){
			   			resetAnimation();
			   			setAnimation("Idle");
						setDirection();
				    	specialcount=0;
				    }
				    else{
		   				specialcount+=1;
		   				eydirection=UP;
		   				EPicy-=1;
			   		} 
	   			}
			}    		
    	}	
    }
    public void resetAnimation(){
    	if (robo.gett().equals("ShotBot")){
    		BPicx=robox;
	    	BPicy=roboy;
	    	EPicx=(robox+robow/2)-IEPic.getIconWidth()/2+3;
	    	EPicy=(roboy+roboh/2)+9;
	    	RAPicx=(robox+robow/2)+18;
	    	RAPicy=roboy+roboh+4;
	    	LAPicx=(robox+robow/2)-(ILAPic.getIconWidth()+18);
	    	LAPicy=roboy+roboh+4;	
    	}
    }
    public void animateBody(){
		 if (bxdirection==RIGHT){ //x values body
		 	BPicx+=1;
		 }
		 if (bxdirection==LEFT){
		 	BPicx-=1;
		 }
		 if (bydirection==DOWN){ //y values body
		 	BPicy+=1;
		 }
		 if (bydirection==UP){
		 	BPicy-=1;
		 }
		 if (BPicy==maxby){
		 	bydirection=UP;
		 	BPicy-=1;
		 }
		 if (BPicy==minby){
		 	bydirection=DOWN;
		 	BPicy+=1;
		 }
		 if (BPicx==maxbx){
		 	bxdirection=LEFT;
		 	BPicx-=1;
		 }
		 if (BPicx==minbx){
		 	bxdirection=RIGHT;
		 	BPicx+=1;
		 }
    }
    public void animateEyes(){
		if (exdirection==RIGHT){
	   		EPicx+=1;
	   	}
	   	if (exdirection==LEFT){
	   		EPicx-=1;
	   	}
   	   	if (eydirection==DOWN){
		 	EPicy+=1;
		}
   		if (eydirection==UP){
		 	EPicy-=1;
		}
   		if (EPicx==maxex){
   			exdirection=LEFT;
   			EPicx-=1;
   		}
   		if (EPicx==minex){
   			exdirection=RIGHT;
   			EPicx+=1;
   		}
		if (EPicy==maxey){
		 	eydirection=UP;
			EPicy-=1;
		}
		if (EPicy==miney){
		 	eydirection=DOWN;
		 	EPicy+=1;
		}
   	}
    public void animateArms(){
    	if (robo.gett().equals("TankBot") && robo.geta()==false){
    		if (axdirection==OUT){
				RAPicx+=1;
				LAPicx-=1;
			}
			if (axdirection==IN){
				RAPicx-=1;
				LAPicx+=1;
			}
			if (RAPicx==maxax){
				axdirection=IN;
				RAPicx-=1;
				LAPicx+=1;
			}
			if (RAPicx==minax){
				axdirection=OUT;
				RAPicx+=1;
				LAPicx-=1;
			}
    	}
    	else{
    		if (axdirection==RIGHT){
				RAPicx+=1;
		 		LAPicx+=1;
			}
			if (axdirection==LEFT){
			 	RAPicx-=1;
			 	LAPicx-=1;
			}
			if (RAPicx==maxax){
			 	axdirection=LEFT;
			 	RAPicx-=1;
			 	LAPicx-=1;
			}
			if (RAPicx==minax){
			 	axdirection=RIGHT;
			 	RAPicx+=1;
			 	LAPicx+=1;
			}
    	}
		if (aydirection==DOWN){
		 	RAPicy+=1;
		 	LAPicy+=1;
		}
		if (aydirection==UP){
			RAPicy-=1;
		 	LAPicy-=1;
		}
		if (RAPicy==maxay){
		 	aydirection=UP;
		 	RAPicy-=1;
			LAPicy-=1;
		}
		if (RAPicy==minay){
			aydirection=DOWN;
		 	RAPicy+=1;
		 	LAPicy+=1;
    	}	
    }
    
   	public void animateLegs(){
		if (lxdirection==RIGHT){
			RLPicx+=1;
		 	LLPicx+=1;
		}
		if (lxdirection==LEFT){
		 	RLPicx-=1;
		 	LLPicx-=1;
		}
		if (RLPicx==maxlx){
		 	lxdirection=LEFT;
		 	RLPicx-=1;
		 	LLPicx-=1;
		}
		if (RLPicx==minlx){
		 	lxdirection=RIGHT;
		 	RLPicx+=1;
		 	LLPicx+=1;
		}
		if (lydirection==DOWN){
		 	RLPicy+=1;
		 	LLPicy+=1;
		}
		if (lydirection==UP){
			RLPicy-=1;
		 	LLPicy-=1;
		}
		if (RLPicy==maxly){
		 	lydirection=UP;
		 	RLPicy-=1;
			LLPicy-=1;
		}
		if (RLPicy==minly){
			lydirection=DOWN;
		 	RLPicy+=1;
		 	LLPicy+=1;
    	}	
    }
    public void animateMiddle(){
		if (maxdirection==RIGHT){
	   		MAPicx+=1;
	   	}
	   	if (maxdirection==LEFT){
	   		MAPicx-=1;
	   	}
   	   	if (maydirection==DOWN){
		 	MAPicy+=1;
		}
   		if (maydirection==UP){
		 	MAPicy-=1;
		}
   		if (MAPicx==maxmax){
   			maxdirection=LEFT;
   			MAPicx-=1;
   		}
   		if (MAPicx==minmax){
   			maxdirection=RIGHT;
   			MAPicx+=1;
   		}
		if (MAPicy==maxmay){
		 	maydirection=UP;
			MAPicy-=1;
		}
		if (MAPicy==minmay){
		 	maydirection=DOWN;
		 	MAPicy+=1;
		}
    }
    public void drawRobo(Graphics2D g,RoboPanel panel){
    	g.drawImage(RAPic,RAPicx,RAPicy,panel);
    	g.drawImage(LAPic,LAPicx,LAPicy,panel);
    	if (robo.geta()==true){
    	    if (robo.gett().equals("LaserBot") || robo.gett().equals("MainBot")){
    	   		g.drawImage(RLPic,RLPicx,RLPicy,panel);
    			g.drawImage(LLPic,LLPicx,LLPicy,panel);	
    	    }
    	    g.drawImage(BPic,BPicx,BPicy,panel);
		    g.drawImage(EPic,EPicx,EPicy,panel);
    	}
    	if (robo.geta()==false){
    		if (!robo.gett().equals("MainBot")){
	    		if (!robo.gett().equals("TankBot")){ //or does not equal tankbot
	    			g.drawImage(MAPic,MAPicx,MAPicy,panel);
	    			//System.out.println("Drawing middle!");
	    		}
	    		if (robo.gett().equals("LaserBot") || robo.gett().equals("MainBot")){
	    	   		g.drawImage(RLPic,RLPicx,RLPicy,panel);
	    			g.drawImage(LLPic,LLPicx,LLPicy,panel);	
	    	    }
		    	g.drawImage(BPic,BPicx,BPicy,panel);
		    	g.drawImage(EPic,EPicx,EPicy,panel);
    		}
    		else{ //special case for MainBot to draw legs over body
    			g.drawImage(BPic,BPicx,BPicy,panel);
		    	g.drawImage(EPic,EPicx,EPicy,panel);
    			g.drawImage(MAPic,MAPicx,MAPicy,panel);
    			g.drawImage(RLPic,RLPicx,RLPicy,panel);
	    		g.drawImage(LLPic,LLPicx,LLPicy,panel);	
    		}
    	}
    }
}