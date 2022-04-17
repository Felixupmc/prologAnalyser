package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Journal {
	private List<Context> stack;
	private int size;
	
	Journal() {
		stack = new ArrayList<>();
		size=0;
	}
	
	public Context pop() {
		size--;
		return stack.remove(size);
	}
	
	public boolean put(Context object) {
		if (stack.add(object)) {
			size++;
			return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public void reverse() {
		Collections.reverse(stack);
	}
	
	public String toString() {
		return stack.toString();
	}
	
}
