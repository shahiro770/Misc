
import java.util.*;

public class MyDynamicArray<T>{ //implements List<T>{
	private Object theArray[] = new Object[0];

    private final Class<T> type;

    public MyDynamicArray(Class<T> type){
        this.type = type;
    }

    public Class<T> getMyType() {
         return this.type;
     }

	//@Override
    public int size() {
        int count = 0;
        for (int i = 0;i < theArray.length;i++){
            if (theArray[i] == null){
                break;
            }
            count++;
        }
        return count;
    }

    //@Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    //@Override
    public boolean contains(Object o) {
        if (o == null){               //the same and in the same order
            return false;
        }
        else if(type != o.getClass()){
            return false;
        }
        else{
            T otherObject = (T)o;
            for (int i = 0;i < this.size();i++){
                if (theArray[i].equals(otherObject)){
                    return true;
                }
            } 
        }
        return false;
    }

   /* //@Override
    public Iterator iterator() {
        return null;
    }

   //@Override
    public Object[] toArray() {
        return new Object[0];
    }*/

    //@Override
    public boolean add(Object o) {
        if (o == null){               //the same and in the same order
            return false;
        }
        else if(type != o.getClass()){
            return false;
        }
        else{
            T otherObject = (T)o;
            for (int i = 0;i < theArray.length;i++){
                if (theArray[i] == null){
                    theArray[i] = o;
                    return true;
                }
            }
            this.resize();
            for (int i = 0;i < theArray.length;i++){
                if (theArray[i] == null){
                    theArray[i] = o;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean resize(){
        Object newArray[] = new Object[theArray.length * 2];
        if (newArray.length == 0){
            Object newNewArray[] = new Object[1];
            newArray = newNewArray;
        }
        for (int i = 0;i < theArray.length;i++){
            newArray[i] = theArray[i];
        }
        theArray = newArray;
        return true;
    }

    //@Override
    /*public boolean remove(Object o) {
         if (o == null){               //the same and in the same order
            return false;
        }
        else if(type != o.getClass()){
            return false;
        }
        else{
            T otherObject = (T)o;
            for (int i = 0;i < this.size();i++){
                if (theArray[i].equals(otherObject)){
                    for (int j = i;j < this.size() - 1;j++){
                        theArray[j] = theArray[j + 1];
                    }
                    return true;
                }
            } 
        }
        return false;
    }

    //@Override
    public boolean addAll(Collection c) {
        return false;
    }

    //@Override
    public boolean addAll(int index, Collection c) {
        return false;
    } */

    //@Override
    public void clear() {
        int loops = this.size();
        for (int i = 0;i < loops;i++){
            theArray[i] = null;
        }
    }

    //@Override
    public T get(int index) {
        return (T)theArray[index];
    }

    //@Override
    public Object set(int index, Object element) {
        if (element == null){               //the same and in the same order
            return null;
        }
        else if (type != element.getClass()){
            return null;
        }
        else{
            Object oldElement = theArray[index];
            theArray[index] = element;
            return oldElement;
        }
        
    }

    //@Override
    public void add(int index, Object element) {
        if (element == null){               //the same and in the same order
            return;
        }
        else if (type != element.getClass()){
            return;
        }
        else{
            if (this.size() + 1 > theArray.length){
                resize();
            }
            int loops = this.size();
            for (int i = index;i < loops;i++){
                theArray[i + 1] = theArray[i];
            }
            theArray[index] = element;
            return;
        }
    }

    public T remove(int index) {
        T toRemove = (T)theArray[index];
        for (int j = index;j < theArray.length - 1;j++){
            theArray[j] = theArray[j + 1];
        }
        return toRemove;
    }

    /*@Override
    public int indexOf(Object o) {

        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }*/
}