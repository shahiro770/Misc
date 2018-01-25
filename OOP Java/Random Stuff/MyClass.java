public class MyClass{
	private int myInt;

	public MyClass(String x) throws MyException{
		try{
			this.myInt = Integer.parseInt(x);
		}catch(NumberFormatException e){
			throw new MyException("invalid int ;", x);
		}
	}

	public String toString(){
		return "" + myInt;
	}

	public static void main(String[] args){
		try{
			MyClass c = new MyClass("Yolo");
			System.out.println(c);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println("method getException returns " + ((MyException)e).theString());
		}
	}
}