public class GenericTest{
	
	public static void main(String args[]){
		GenericQueue<Student> studentQueue = new GenericQueue<Student>();
		studentQueue.enqueue(new ForeignStudent("Rene", 4, "France", new MyDate("12/12/12")));
		studentQueue.enqueue(new ForeignStudent("Li", 3, "China", new MyDate("9/9/9")));
		studentQueue.enqueue(new ForeignStudent("Rakesh", 2, "India", new MyDate("01/01/01")));
		System.out.println(studentQueue.toString());
		studentQueue.dequeue();
		studentQueue.enqueue(new CanadianStudentUnder65("Lynne", 5));
		studentQueue.enqueue(new CanadianStudentUnder65("Tanya", 5));
		System.out.println(studentQueue.toString());
		studentQueue.dequeue();
		studentQueue.enqueue(new CanadianStudentUnder65("Chris", 5));
		studentQueue.enqueue(new CanadianStudentUnder65("Ryan", 5));
		System.out.println(studentQueue.toString());
		studentQueue.dequeue();
		studentQueue.enqueue(new SeniorStudent("Bob", 5, 45000));
		studentQueue.enqueue(new SeniorStudent("Tyler", 3, 62000));
		studentQueue.enqueue(new SeniorStudent("Mary", 4, 65000));
		System.out.println(studentQueue.toString());
		studentQueue.dequeue();
		studentQueue.dequeue();
		studentQueue.dequeue();

		System.out.println(studentQueue.toString());
		studentQueue.dequeue();
		studentQueue.dequeue();
		studentQueue.dequeue();
		studentQueue.dequeue();
		studentQueue.dequeue();
		studentQueue.dequeue();
		studentQueue.dequeue();
		studentQueue.dequeue();
		studentQueue.dequeue();
		studentQueue.dequeue();
		System.out.println(studentQueue.toString());
	}
}