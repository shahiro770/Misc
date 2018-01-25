public class BadAgeException extends Exception{
	String ageString;
	
	public BadAgeException(String message, String ageString){
		super(message + "; supplied age is " + ageString);
		this.ageString = ageString;
	}
	
	public String getAgeString(){
		return ageString;
	}
}