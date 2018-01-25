public class test{
	public static void main (String[]args){
		MyDynamicArray<Integer> myArray = new MyDynamicArray<Integer>(Integer.class);
		System.out.println(myArray.isEmpty());
		myArray.add(5);
		myArray.add(1);
		myArray.add(3);
		/*System.out.println(myArray.isEmpty());
		System.out.println(myArray.get(0));
		System.out.println(myArray.get(1));
		System.out.println(myArray.get(2));
		System.out.println(myArray.size());
		System.out.println(myArray.contains(5));
		System.out.println(myArray.contains(4));*/
		myArray.remove(0);
		System.out.println(myArray.contains(5));
		System.out.println(myArray.get(0));
		//System.out.println(myArray.size());
		myArray.clear();
		//System.out.println(myArray.get(0));
		//System.out.println(myArray.isEmpty());
		myArray.add(5);
		myArray.add(1);
		myArray.add(3);
		myArray.add(2, 4);
		System.out.println(myArray.get(1));
		System.out.println(myArray.get(2));
		System.out.println(myArray.get(3));


	}
}