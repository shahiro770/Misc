/*
 * Shahir Chowdhury
 * 2017-09-09
 * OutputFrame.java 
 *
 * This program creates a OutputFrame object. It displays the information of students at a university in a given sorted order.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OutputFrame extends JFrame{
	private JLabel description;		//description of contents of OutputFrame
	private JTextArea outputField;	
	private JButton close;

	public OutputFrame(boolean isDescending, String text){
		super("Output Frame");
		this.setSize(600, 500);
		setLayout(new FlowLayout());

		//window description
		if(isDescending == false){	//contents change depending on what the sort was
			description = new JLabel("Sorted List (ascending) of Students");
		}
		else{
			description = new JLabel("Sorted List (descending) of Students");
		}
		add(description);

		//display results of sort
		outputField = new JTextArea(20, 50);
		outputField.setText(text);
		outputField.setEditable(false);
		add(outputField);

		//close button for the OutputFrame
		close = new JButton("Close Window");
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		add(close);

		setVisible(true);
	}
}