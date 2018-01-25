public class MyException extends Exception{
		String errorString;

		public MyException(String errorString, String badInt){
			super("This was the wrong number: " + badInt);
			this.errorString = badInt;
		}

		public String theString(){
			return errorString;
		}
	}