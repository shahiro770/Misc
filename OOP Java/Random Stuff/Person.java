public class Person {
	private String name;
	private int age;
	
	public Person(String name, String ageString) throws BadAgeException{
		try{
			this.name = name;
			this.age = Integer.parseInt(ageString);
		}catch(NumberFormatException e){
			throw new BadAgeException("invalid age string ", ageString);
		}
	}
	
	public String toString(){
		return ("name = " + name + "; age = " + age);
	}
	
	public static void main(String args[]){
		Person p = null;
		try{
			p = new Person("John", "xyz");
			System.out.println(p);
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println("method getBadAge returns " + ((BadAgeException)e).getAgeString());
		}
		System.out.println(p);
	}
}
