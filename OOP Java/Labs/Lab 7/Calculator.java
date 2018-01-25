/*
 * Shahir Chowdhury
 * 2017-09-09
 * Calculator.java
 * This program creates a postfix calculator. The initial value stored is 0, hence the user only needs to enter a single number
 * followed by an operation to begin computations.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener{
	private int currentResult = 0;				//last number computed
	private String nextNum = "";				//current number being entered
	private JButton[] nums = new JButton[10];	//JButtons for values 0 through 9
	private JButton add, sub, mult, div;		//JButtons for all mathetmatical operations
	private JTextField display;					//displays the last number computed or current number being entered

	public Calculator(){
		super("Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(160, 260);
		setLayout(new FlowLayout());

		//display
		display = new JTextField(10);	
		display.setText("0");
		display.setEditable(false);
		add(display);

		//numbered buttons
		for (int i = 0;i < 10;i++){		
			int val;
			if (i <= 8){		//buttons 1 to 9
				val = i + 1;	//value to be displayed on the given button
				nums[i] = new JButton("" + val);
				nums[i].setPreferredSize(new Dimension(42,22));
			}
			else{
				val = 0;	//the 0 button the calculator is added last
				nums[i] = new JButton("" + val);
				nums[i].setPreferredSize(new Dimension(126,22));
			}
			nums[i].addActionListener(new ActionListener(){	
				public void actionPerformed(ActionEvent e){
					nextNum += val;
					display.setText(nextNum);
				}
			});
			add(nums[i]);
		}

		//operational buttons
		add = new JButton("+");		
		add.setPreferredSize(new Dimension(50,22));
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentResult += Integer.parseInt(nextNum);
				nextNum = "";
				display.setText("" + currentResult);
			}
		});
		add(add);

		sub = new JButton("-");
		sub.setPreferredSize(new Dimension(50,22));
		sub.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentResult -= Integer.parseInt(nextNum);
				nextNum = "";
				display.setText("" + currentResult);
			}
		});
		add(sub);

		mult = new JButton("*");
		mult.setPreferredSize(new Dimension(50,22));
		mult.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentResult *= Integer.parseInt(nextNum);
				nextNum = "";
				display.setText("" + currentResult);
			}
		});
		add(mult);

		div = new JButton("/");
		div.setPreferredSize(new Dimension(50,22));
		div.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentResult /= Integer.parseInt(nextNum);
				nextNum = "";
				display.setText("" + currentResult);
			}
		});
		add(div);
	}

	public void actionPerformed(ActionEvent e){}

	public static void main (String[] args){
		Calculator myCalculator = new Calculator();
		myCalculator.setVisible(true);
	}
}