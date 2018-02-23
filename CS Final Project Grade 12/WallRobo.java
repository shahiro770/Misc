/**
 * @(#)WallRobo.java
 *
 *
 * @author 
 * @version 1.00 2015/6/11
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class WallRobo extends GenRobo{
	public WallRobo(String type,int robox, int roboy,boolean aff,ArrayList<Image> piclist,int level){
		super (type,robox,roboy,aff,piclist);
		if(level==1){
    		sethp(15);
    	}
    	else if(level==2){
    		sethp(25);
    	}
    	else if(level==3){
    		sethp(55);
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
}