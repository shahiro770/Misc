/* Make a program that

Pops a JFrame up
The JFrame has a button that says “Click Me”
The JFrame also has a text field that is originally empty
When the button is clicked, the text field says “Button clicked: ” + number of times the button has been clicked through the run of the program
Make it as minimalistic as possible, I don’t care about positioning or colours or sizes too much

*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MyFrame extends JFrame{
	private JButton myBut;
	private JTextField myField;
	private int clickNum;

	public MyFrame(){
		super("This is My Frame, not your Frame");
		setSize(260, 100);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		myField = new JTextField("", 10);
		add(myField);

		myBut = new JButton("Click Me");
		myBut.setPreferredSize(new Dimension(62,22));
		myBut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clickNum++;
				myField.setText("" + clickNum);
			}
		});
		add(myBut);	
		setVisible(true);
	}
	public static void main(String[] args){
		MyFrame frame = new MyFrame();
	}

}
