/**
 * @(#)MainRobo.java
 *
 *
 * @Shahir Chowdhury
 * @version 1.00 2015/4/21
 */
 
 /* This program creates a MainRobo object, the head robot that is the main target of the opposing MainRobo's
  * forces. It is used as a logic device to make attack calculations easier. */
  
import java.util.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainRobo extends GenRobo{
    public MainRobo(int mainx,int mainy,boolean affinity,ArrayList<Image> piclist){
    	super ("MainBot",mainx,mainy,affinity,piclist); //MainRobo is a "MainBot" type GenRobo by default
    }
}