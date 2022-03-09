package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class Exercice7 {
	public static TermPredicate a,b,f,g,h,k,q,r,p;
	public static TermVariable u,v,w,x,y,z;
	
	public static void init() {
		a = new TermPredicate(new Predicate("a", null), null);
		b = new TermPredicate(new Predicate("b", null), null);
		u = new TermVariable("u", null);
		v = new TermVariable("v", null);
		w = new TermVariable("w", null);
		x = new TermVariable("x", null);
		y = new TermVariable("y", null);
		z = new TermVariable("z", null);
	}

	public static Equations equation1() {
		Term left,right;
		List<Term> tmp = new ArrayList<Term>();
		tmp.add(w);
		f = new TermPredicate(new Predicate("f", tmp, null), null);
		
		tmp = new ArrayList<Term>();
		tmp.add(z);
		tmp.add(w);
		h = new TermPredicate(new Predicate("h", tmp, null), null);
		
		tmp = new ArrayList<Term>();
		tmp.add(z);
		tmp.add(h);
		tmp.add(f);
		left = new TermPredicate(new Predicate("p", tmp, null), null);
		
		tmp = new ArrayList<Term>();
		tmp.add(a);
		f = new TermPredicate(new Predicate("f", tmp, null), null);
		
		tmp = new ArrayList<Term>();
		tmp.add(y);
		tmp.add(f);
		h = new TermPredicate(new Predicate("h", tmp, null), null);
		
		tmp = new ArrayList<Term>();
		tmp.add(x);
		f = new TermPredicate(new Predicate("f", tmp, null), null);
		
		tmp = new ArrayList<Term>();
		tmp.add(f);
		tmp.add(h);
		tmp.add(y);
		right = new TermPredicate(new Predicate("p", tmp, null), null);
		
		Equations s = new Equations();
		s.add(left, right);
		return s;
	}
	
	public static void main(String[] args) {
		init();
		System.out.println(equation1());
	}

}
