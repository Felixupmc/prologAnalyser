package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class Journal <T> {
	private List<T> stack;
	private int size;
	
	Journal() {
		stack = new ArrayList<>();
		size=0;
	}
	
	public T pop() {
		size--;
		return stack.remove(size);
	}
	
	public boolean put(T object) {
		if (stack.add(object)) {
			size++;
			return true;
		}
		return false;
	}
	
}
