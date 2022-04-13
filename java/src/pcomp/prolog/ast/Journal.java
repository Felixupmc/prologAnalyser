package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Journal {
	private List<List<DeclAssertion>> stack;
	private int size;
	
	Journal() {
		stack = new ArrayList<>();
		size=0;
	}
	
	Journal(List<Predicate> goals, List<DeclAssertion> rules) {
		/*construit le journal permettant de résoudre les buts
		 *selon les règles passées en argument*/
		stack = new ArrayList<>();
		size=0;
		for (Predicate goal : goals) {
			List<DeclAssertion> l = new ArrayList<>();
			for(DeclAssertion rule : rules) {
				Predicate head = rule.getHead();
				if (!head.getSymbol().equals(goal.getSymbol()) || head.getArguments().size()!=goal.getArguments().size()) continue;
				l.add(rule);
			}
			put(l);
		}
		reverse();
	}
	
	public List<DeclAssertion> pop() {
		size--;
		return stack.remove(size);
	}
	
	public boolean put(List<DeclAssertion> object) {
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
