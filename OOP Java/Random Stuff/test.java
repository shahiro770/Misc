
public class test {
	public static void main(String[] args){
		
		NumberCarrier obj = new NumberCarrier(){	//this is an anonymous class
			private int number;
			public void setNumber(int value){
				number = value;
			}
			public int getNumber(){
				return number;
			}
		};
		
		NumberCarrier anotherObj = new NumberCarrier(){	//this is an anonymous class
			private int number;
			public void setNumber(int value){
				number = 2 * value;
			}
			public int getNumber(){
				return number;
			}
		};
		
		obj.setNumber(46);
		anotherObj.setNumber(1);
		showNumber(obj);
		showNumber(anotherObj);
		System.out.println("The End.");
	}
	
	public static void showNumber(NumberCarrier o){
		System.out.println(o.getNumber());
	}
}

interface NumberCarrier {
	public void setNumber(int value);
	public int getNumber();
}