import java.util.*;

public interface AddAndIterate<T> extends Iterable<T> {
	public void add(T x);
}