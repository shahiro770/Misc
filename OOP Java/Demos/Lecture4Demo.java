import java.util.Scanner;
import java.util.StringTokenizer;

public class Lecture4Demo {
/*
	public static void main(String[] args) {
		
		String line  = "Ryan D Scott, 30, 1000000000";
		StringTokenizer stk = new StringTokenizer(line, ",");
		String tk1 = stk.nextToken();
		String tk2 = stk.nextToken();
		String tk3 = stk.nextToken();
		StringTokenizer stkGetFinal = new StringTokenizer(tk1);
		String theStringWeWant = "";
		while (stkGetFinal.hasMoreTokens()){
			theStringWeWant = stkGetFinal.nextToken(); //get the last token
		}
		System.out.println("Last Name: " + theStringWeWant);
		System.out.println("Age:" + tk2);
		System.out.println("Salary:" + tk3);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a space-separated string to tokenize!");
		String received = sc.nextLine();
		StringTokenizer st = new StringTokenizer(received);
		int tokenCount = 0;
		while (st.hasMoreTokens()){
			System.out.println(st.nextToken());
			tokenCount++;
		}
		System.out.println("We received " + tokenCount + " tokens!");
		

		System.out.println("Enter an a-, b-, or c-separated string to tokenize!"); //cannot separate using abc!
		String received2 = sc.nextLine();
		StringTokenizer st2 = new StringTokenizer(received2, "abc"); //every delimiter here is separate, no "strings" or patterns as a delimiter!
		tokenCount = 0;
		while (st2.hasMoreTokens()){
			System.out.println(st2.nextToken());
			tokenCount++;
		}
		System.out.println("We received " + tokenCount + " tokens!");
		
		//we did this example in the Scanner lecture, but it's back again... why?
		//because scanner actually uses a regex pattern as the delimiter, which is far more powerful than single characters (which is all tokenizer allows, unfortunately) 
		sc.useDelimiter("abc"); //whitespace is no longer a valid delimiter, but the pattern "abc" is!
		System.out.println("Please enter 5 integers, separated using \"abc\": "); //use abc to separate numbers instead!!
		System.out.println("First value: " + sc.nextInt());
		System.out.println("Second value: " + sc.nextInt());
		System.out.println("Third value: " + sc.nextInt());
		System.out.println("Fourth value: " + sc.nextInt());
		System.out.println("Fifth value: " + sc.nextInt());
		sc.close();
		
		String testRegex = "aardvark";
		String regex = "a*ardvark";
		if (testRegex.matches(regex)) System.out.println("aardvark matches the pattern a*ardvark!");
		else System.out.println("It does not match!");
		regex = "a*rdvark";
		if (testRegex.matches(regex)) System.out.println("aardvark matches the pattern a*rdvark!");
		else System.out.println("It does not match!");
	}
	*/
	

	/*	
	public static void main(String a[]){
		String regexPattern = "a|b";
		
		String toCheck = "a";
		System.out.println("1: " + toCheck.matches(regexPattern));
		
		regexPattern = "[abc]";

		toCheck = "a";
		System.out.println("2: " + toCheck.matches(regexPattern));

		toCheck = "b";
		System.out.println("3: " + toCheck.matches(regexPattern));

		toCheck = "c";
		System.out.println("4: " + toCheck.matches(regexPattern));

		toCheck = "d";
		System.out.println("5: " + toCheck.matches(regexPattern));

		regexPattern = "ab*";

		toCheck = "abababab";
		System.out.println("6: " + toCheck.matches(regexPattern));

		regexPattern = "(ab)*";

		toCheck = "abababab";
		System.out.println("7: " + toCheck.matches(regexPattern));

		regexPattern = "(ab)*";

		toCheck = "abbbbb";
		System.out.println("8: " + toCheck.matches(regexPattern));

		regexPattern = "ab*";

		toCheck = "abbbbb";
		System.out.println("9: " + toCheck.matches(regexPattern));

		regexPattern = "[^a]";

		toCheck = "c";
		System.out.println("10: " + toCheck.matches(regexPattern));

		regexPattern = "[^a]";

		toCheck = "a";
		System.out.println("11: " + toCheck.matches(regexPattern));

		regexPattern = "[^a-c0-9]d";

		toCheck = "ad";
		System.out.println("12: " + toCheck.matches(regexPattern));

		toCheck = "cd";
		System.out.println("13: " + toCheck.matches(regexPattern));

		toCheck = "9d";
		System.out.println("14: " + toCheck.matches(regexPattern));

		toCheck = "fd";
		System.out.println("15: " + toCheck.matches(regexPattern));

		regexPattern = "[A-Z]+9";

		toCheck = "ABCDE9";
		System.out.println("16: " + toCheck.matches(regexPattern));

		toCheck = "ABCDEf9";
		System.out.println("17: " + toCheck.matches(regexPattern));

		toCheck = "9";
		System.out.println("18: " + toCheck.matches(regexPattern));

		regexPattern = ".*";

		toCheck = "1234;abcd,2734asdal;kdjasdl;kj";
		System.out.println("19: " + toCheck.matches(regexPattern));

		regexPattern = "\\d*";

		toCheck = "1234";
		System.out.println("20: " + toCheck.matches(regexPattern));

		regexPattern = "\\d*";

		toCheck = "1234a";
		System.out.println("21: " + toCheck.matches(regexPattern));

		regexPattern = "\\W*";

		toCheck = "1234a";
		System.out.println("22: " + toCheck.matches(regexPattern));

		regexPattern = "\\W*";

		toCheck = ",;.!@$*()&!@%";
		System.out.println("23: " + toCheck.matches(regexPattern));

		regexPattern = "\\W*";

		toCheck = ",;.!@$*()&!@%";
		System.out.println("24: " + toCheck.matches(regexPattern));

		regexPattern = "[^\\w]*";

		toCheck = ",;.!@$*()&!@%";
		System.out.println("25: " + toCheck.matches(regexPattern));

		regexPattern = "[^\\w]*";

		toCheck = ",;.!@$*()&!@%a";
		System.out.println("26: " + toCheck.matches(regexPattern));

		regexPattern = "^(Lola).*";

		toCheck = "Lola is a good dog";
		System.out.println("27: " + toCheck.matches(regexPattern));

		regexPattern = "^(Lola).*";

		toCheck = "I found a tick on Lola last weekend";
		System.out.println("28: " + toCheck.matches(regexPattern));

		regexPattern = ".*(\\bdog\\b).*";

		toCheck = "Lola is a good dog";
		System.out.println("29: " + toCheck.matches(regexPattern));

		regexPattern = ".*(\\bdog\\b).*";

		toCheck = "Lola is a good doggie";
		System.out.println("30: " + toCheck.matches(regexPattern));

		regexPattern = ".*(\\bplaying)$";

		toCheck = "Lola had fun playing";
		System.out.println("31: " + toCheck.matches(regexPattern));

		regexPattern = ".*(\\bplaying)$";

		toCheck = "Lola had fun playing in the yard";
		System.out.println("32: " + toCheck.matches(regexPattern));

		regexPattern = ".*(\\bplaying)$";

		toCheck = "Lola had fun displaying";
		System.out.println("33: " + toCheck.matches(regexPattern));

		regexPattern = "\\\\b[a-z]+\\\\b";

		toCheck = "\\baaa\\b";
		System.out.println("34: " + regexPattern + "\t" + toCheck + " " + toCheck.matches(regexPattern));

		regexPattern = "\\b[a-z]+\\b";

		toCheck = "\\baaa\\b";
		System.out.println("35: " + regexPattern + "\t\t" + toCheck + " " + toCheck.matches(regexPattern));

		regexPattern = "a\\*";

		toCheck = "a*";
		System.out.println("36: " + toCheck.matches(regexPattern));

		regexPattern = "(AZ|CA|CO)[0-9]{4}";

		toCheck = "AZ1234";
		System.out.println("37: " + toCheck.matches(regexPattern));

		toCheck = "AZ12345";
		System.out.println("38: " + toCheck.matches(regexPattern));

		toCheck = "CA1111";
		System.out.println("39: " + toCheck.matches(regexPattern));

		toCheck = "CA11115";
		System.out.println("40: " + toCheck.matches(regexPattern));

		toCheck = "CO1122";
		System.out.println("41: " + toCheck.matches(regexPattern));

		toCheck = "CO11234";
		System.out.println("42: " + toCheck.matches(regexPattern));

		

		regexPattern = "L.{3}";

		toCheck = "Lola had fun playing";
		System.out.println("43: " + toCheck.replaceAll(regexPattern, "Ryan"));	//replaceAll, not replace!!

		toCheck = "Lollipops are delicious";
		System.out.println("44: " + toCheck.replaceAll(regexPattern, "Ryan"));

		
	} 
	
	
	
	
	public static void main(String a[]){
		Scanner keyboard;
		String inputString;
		keyboard = new Scanner(System.in);
		System.out.println("Type string to test");
		inputString = keyboard.nextLine();
		System.out.println("Input is " + inputString);
		if (inputString.matches("[tT]rue|[yY]es")){
			// matches true, True, yes, Yes
			System.out.println("matches [tT]rue|[yY]es");
		} else {
			System.out.println("does not match [tT]rue|[yY]es");
		}
		keyboard.close();
	} 
	
	public static void main(String a[]){
		Scanner keyboard;
		String inputString;
		keyboard = new Scanner(System.in);
		System.out.println("Type string to test");
		inputString = keyboard.nextLine();
		System.out.println("Input is " + inputString);
		if (inputString.matches("[a-zA-Z]{3}")){
			// if the string contains exactly three letters
			System.out.println("matches [a-zA-Z]{3}");
		} else {
			System.out.println("does not match [a-zA-Z]{3}");
		}
		keyboard.close();
	}// Could have used "[a-Z][a-Z][a-Z]", they are equivalent
	
	*/
	public static void main(String a[]){
		Scanner keyboard;
		String inputString;
		keyboard = new Scanner(System.in);
		System.out.println("Type string to test");
		inputString = keyboard.nextLine();
		System.out.println("Input is " + inputString);
		if (inputString.matches("^[^\\d].*")){
			// matches if the string does not have a number at the beginning
			System.out.println("matches ^[^\\d].*");
		} else {
			System.out.println("does not match ^[^\\d].*");
		}
		keyboard.close();
	}
	/*
	public static void main(String a[]){
		Scanner keyboard;
		String inputString;
		keyboard = new Scanner(System.in);
		System.out.println("Type string to test");
		inputString = keyboard.nextLine();
		System.out.println("Input is " + inputString);
		//check if the string contains a number less then 300
		if (inputString.matches("[^0-9]*[12]?[0-9]{1,2}[^0-9]*")){
			System.out.println("matches [^0-9]*[12]?[0-9]{1,2}[^0-9]*");
		} else {
			System.out.println("does not match [^0-9]*[12]?[0-9]{1,2}[^0-9]*");
		}
		keyboard.close();
	}
	
	
	//Problem - replace all vowels in a string with the $ symbol:

	public static void main(String a[]){
		Scanner keyboard;
		String inputString, modifiedText;
		keyboard = new Scanner(System.in);
		System.out.println("Type string to test");
		inputString = keyboard.nextLine();
		System.out.println("Input is " + inputString);
		modifiedText = inputString.replaceAll("[aeiou]","\\$");
		System.out.println("modified text is " + modifiedText);
		keyboard.close();
		
		String s = "a*";
		System.out.println("Did we match a*? " + s.matches("a\\*"));	//try "a\*" or "a*", what happens?
	}
	*/
}