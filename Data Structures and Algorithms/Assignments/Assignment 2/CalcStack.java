/*
 *CalcStack.java
 *Last Updated: 2017-10-16
 *Shahir Chowdhury
 *
 *This program constructs a calculator that evaluates infix arithmetic expressions by converting them to postfix notation using a stack. 
 *The expressions are assumed to have each operator and operand seperated by space characters. 
*/
import java.util.*;

public class CalcStack{
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);

		while (kb.hasNextLine()){
			String input = kb.nextLine();
			if (!(input.equals(""))){
				System.out.println("Validation:");
				boolean check = validate(input);

				if (check == true){
					System.out.println("Valid!");
					System.out.println("Post Conversion:");
					String post = toPost(input);
					System.out.println("Evaluation:");
					eval(post);
				}
				else{
					System.out.println("Invalid!");
				}

				System.out.println("Getting ready for next expression!");
			}
		}
		System.out.println("Have a nice day!");
	}	

	//checks whether or not a mathematical expression is valid; expression is assumed to be space delimited
	public static boolean validate(String expression){
		StackGeneric<String> infix = new StackGeneric<String>();	//stack containing all infix characters
		String copy = expression;									//copy of the expression for probably no reason
		StringTokenizer st = new StringTokenizer(copy, " ");

		while (st.hasMoreTokens()){									//push all expression tokens onto the stack
			String curr = st.nextToken();
			infix.push(curr);
		}

		int rParCount = 0;	//counter for ) brackets
		int lParCount = 0;	//counter for ( brackets

		while (infix.isEmpty() == false){	
			String curr = infix.pop();

			if (!(isOperator(curr))){								//non operator characters must be followed by an operator character
				if (infix.isEmpty() == false){
					if (isOperator(infix.top()) != true){
						return false;
					}
				}
			}
			else if (isOperator(curr)){
				//if the last character is an operator character, it can only be ( or -
				if (infix.getSize() == 0){
					if (!(curr.equals("(") || curr.equals("-"))){
						return false;
					}
				}
				//if following an operator character is another operator, check various cases
				else if (isOperator(infix.top()) && !(curr.equals("(")) && !(infix.top().equals(")"))){ 	
					if (curr.equals(")")){					// ) can only be followed by )
						if (infix.top().equals(")") == false){
							return false;
						}
					}
					else if (curr.equals("-")){				// - can only have a ( following
						if (infix.top().equals("(") == false){
							return false;
						}
					}
					else{				// in no other circumstance may an operator follow another operator
						return false;
					}
				}
				if (curr.equals("(")){	
					lParCount++;
				}
				if (curr.equals(")")){
					rParCount++;
				}
			}
			System.out.println(infix);	
		}
		if (rParCount != lParCount){	//if  ( and ) are not equal, expression is invalid
			return false;		
		}
		return true;
	}

	//converts an infix expression to postfix notation
	public static String toPost(String expression){
		StackGeneric<String> opStack = new StackGeneric<String>();	//stack to hold all operators
		String copy = expression;									//copy of expression for probably no reason
		String result = "";											
		String lastToken = ""; 										//track the last token for determining unary negations
		StringTokenizer st = new StringTokenizer(copy, " ");		
		int tokens = st.countTokens();								//total number of tokens for determining unary negations
		
		while (st.hasMoreTokens()){
			String curr = st.nextToken();
			if (isOperator(curr) == false){		//add it to the stack if its a number
				result += curr + " ";
			}
			else{
				//change to unary negation if negative occurs at the start
				if (curr.equals("-") && opStack.isEmpty() && expression.substring(0,1).equals("-") && st.countTokens() == tokens - 1){
					curr = "~";
				}
				//change to unary negation if the negative is not following a ) bracket or a number
				//unary negations are only recognized if they are contained inside brackets if they don't occur at the start
				//e.g. - 1 will throw an error but ( - 1 ) will not
				if (curr.equals("-")){
					if (!(lastToken.equals(")") || isOperator(lastToken) == false)){
						curr = "~" ;
					}
				}
				if (curr.equals("(")){			//push all ( to the stack
					opStack.push(curr);
				}
				else if (curr.equals(")")){		//pop all operators before ( if a ) is encountered
					while(opStack.top().equals("(") == false){
						result += opStack.pop() + " "; 
					}
					opStack.pop();				//remove the ( before continuing
				}
				else{
					//pop all operators with greater or equal precedence to the operator that is about to be pushed
					while (opStack.isEmpty() == false && higherOp(curr, opStack.top())){
						result += opStack.pop() + " ";
					}
					opStack.push(curr);
				}
			}
			lastToken = curr;
			System.out.println(opStack);	
		}
		while (opStack.isEmpty() == false){	//add on any remaining operators in the stack
			result += opStack.pop() + " ";
			System.out.println(opStack);
		}
		return result;
	}

	//evalutes a postfix expression
	public static int eval(String expression){
		int result = 0;
		StackGeneric<String> resStack = new StackGeneric<String>();	//stack containing the numbers being operated on
		StringTokenizer st = new StringTokenizer(expression, " ");

		while (st.hasMoreTokens()){
			String curr = st.nextToken();
			if (isOperator(curr) == false){
				resStack.push(curr);
			}
			else{
				// + - * / and ^ require two operands to apply to
				if (curr.equals("+") || curr.equals("-") || curr.equals("*") || curr.equals("/") || curr.equals("^")){
					int first = Integer.parseInt(resStack.pop());			//1st operand from the top
					int second = Integer.parseInt(resStack.pop());			//2nd operand from the top
					if (curr.equals("+")){
						resStack.push(Integer.toString(second + first));	//the 2nd operand from the top always is operated on first
					}
					else if (curr.equals("-")){
						resStack.push(Integer.toString(second - first));
					}
					else if (curr.equals("*")){
						resStack.push(Integer.toString(second * first));
					}
					else if (curr.equals("/")){
						resStack.push(Integer.toString(second / first));
					}
					else if (curr.equals("^")){
						resStack.push(Integer.toString((int)Math.pow(second, first)));
					}
				}
				//~ only needs one operand to apply to
				else if (curr.equals("~")){
					int first = Integer.parseInt(resStack.pop());
					resStack.push(Integer.toString(first * -1));
				}
			}
			System.out.println(resStack);
		}
		return Integer.parseInt(resStack.pop());
	}

	//checks if a character is an operator by this program's standards
	public static boolean isOperator(String op){
		if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("^") || op.equals("~") || op.equals("(") || op.equals(")")){
			return true;
		}
		return false;
	}

	/* Compares two operators to determine which has higher precedence; 
	   If op2 is lower than op1, returns false. If op2 is greater than op1 or of equal precendence, return true */
	public static boolean higherOp(String op1, String op2){
		int op1prec = 0;
		int op2prec = 0;
		switch (op1){
			case "+":
				op1prec = 1;
				break;
			case "-":
				op1prec = 1;
				break;
			case "*":
				op1prec = 2;
				break;
			case "/":
				op1prec = 2;
				break;
			case "~":
				op1prec = 3;
				break;
			case "^":
				op1prec = 4;
				break;
			case "(": 			//excludes ) as ) is never added to the stack
				op1prec = 5;
				break;
		}
		switch (op2){
			case "+":
				op2prec = 1;
				break;
			case "-":
				op2prec = 1;
				break;
			case "*":
				op2prec = 2;
				break;
			case "/":
				op2prec = 2;
				break;
			case "~":
				op2prec = 3;
				break;
			case "^":
				op2prec = 4;
				break;
			case "(": 			//excludes ) as ) is never added to the stack
				op1prec = 5;
				break;
		}
		return op2prec >= op1prec;
	}
}
