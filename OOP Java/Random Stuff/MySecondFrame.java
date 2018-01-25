import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MySecondFrame extends JFrame implements ActionListener{
	JLabel prompt, outputTitle;
	JTextField inputArea;
	JTextArea outputArea;
	JButton myButton;
	
	public MySecondFrame() {
		super("Use of buttons");
		setLayout(flowLayout);
		prompt = new JLabel( "Type a line" );
		add(prompt);
		inputArea = new JTextField(10);
		inputArea.addActionListener(this);
		add(inputArea);
		outputTitle = new JLabel( "Output produced by program" );
		add(outputTitle);
		outputArea = new JTextArea(10, 20);
		add(outputArea);
		myButton = new JButton("Clear Output");
		
		myButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				outputArea.setText("");
			}
		});
		
		add(myButton);
		setSize(250, 300);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		outputArea.append("You typed - " + event. getActionCommand() + " \n");
		inputArea.setText("");
	}
	
	public static void main(String args[]){
		MySecondFrame aFrame = new MySecondFrame();
		}
	}