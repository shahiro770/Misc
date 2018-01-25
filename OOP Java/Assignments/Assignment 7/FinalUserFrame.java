/*
 * Shahir Chowdhury
 * 2017-09-09
 * FinalUserFrame.java 
 *
 * This program creates a FinalUserFrame object. It provides the user with a GUI for displaying the information of
 * students at a given University.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FinalUserFrame extends JFrame{
	private JLabel description;					//description beside countryInputField
	private JTextField countryInputField;					
	private JTextArea outputField;				//display results from user displaying unsorted list of students or country reps
	private JButton showStudents;				//showStudents displays unsorted list of students
	private JButton aStudents, dStudents;		//aStudents and dStudents display descending and ascending list of students respectively
	private String uniPeeps;					//unsorted list of students

	public FinalUserFrame(University ourUniversity){
		super("Input Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 400);
		setLayout(new FlowLayout());

		uniPeeps = ourUniversity.toString();	//store the unsorted list of students to avoid alteration 

		//JLabel and JTextField for searching for students with a specifc background
		description = new JLabel("Enter name of Country");
		add(description);
		countryInputField = new JTextField(20);
		countryInputField.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e){
				countryInputField.setText("");
				int studentNum = ourUniversity.numberOfStudents(e.getActionCommand());
				outputField.setText("Number of Students from " + e.getActionCommand() + " is " + studentNum + "\n");
			}
		});
		add(countryInputField);

		//displays output
		outputField = new JTextArea(15, 50);
		outputField.setEditable(false);
		add(outputField);

		//list students unsorted
		showStudents = new JButton("Show Students");
		showStudents.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				outputField.setText(uniPeeps);
			}
		});
		add(showStudents);

		//ascending sort
		aStudents = new JButton("Show Students sorted (Ascending)");
		aStudents.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ourUniversity.sortStudents(false);
				OutputFrame output = new OutputFrame (false, ourUniversity.toString());
				output.setVisible(true);
			}
		});
		add(aStudents);

		//descending sort
		dStudents = new JButton("Show students sorted (Descending)");
		dStudents.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ourUniversity.sortStudents(true);
				OutputFrame output = new OutputFrame (true, ourUniversity.toString());
				output.setVisible(true);
			}
		});
		add(dStudents);
	}
}