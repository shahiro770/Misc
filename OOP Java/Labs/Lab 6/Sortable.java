/*
 * Shahir Chowdhury
 * 2017-06-28
 * Sortable.java
 *
 * This program creates the interface Sortable. It designates that a class will have a way to relate two objects by a given value,
 * allowing for the determination of one object of a class having a value less than another.
*/

public interface Sortable{
	public boolean lessThan(Object other);
}