package pcomp.prolog;
import java.util.ArrayList;
import java.util.List;

import pcomp.prolog.ast.*;

public class Test {
	
	public static void main(String[] args) {
		
		TermVariable tv1 = new TermVariable("Z",new Position(0,0));
		TermVariable tv2 = new TermVariable("W",new Position(0,0));

		List<Term> list1 = new ArrayList<Term>();
		list1.add(tv1);
		list1.add(tv2);
		Predicate p1 = new Predicate("h",list1,new Position(0,0));
		TermPredicate tp1 = new TermPredicate(p1,p1.getPosition());

		List<Term> list2 = new ArrayList<Term>();
		list2.add(tv2);
		Predicate p2 = new Predicate("f",list2,new Position(0,0));
		TermPredicate tp2 = new TermPredicate(p2, p2.getPosition());

		List<Term> list3 = new ArrayList<Term>();
		list3.add(tv1);
		list3.add(tp1);
		list3.add(tp2);
		Predicate p3 = new Predicate("p",list3,new Position(0,0));
		TermPredicate tp3 = new TermPredicate(p3, p3.getPosition());
		
		Equations e = new Equations();
		e.add(tp3, tp3);
		e.add(tp3, tp3);
		System.out.println(e);
		
		tv1 = new TermVariable("Z",new Position(0,0));
		tv2 = new TermVariable("W",new Position(0,0));
		TermVariable tv3 = new TermVariable("Y",new Position(0,0));

		list1 = new ArrayList<Term>();
		list1.add(tv2);

		p1 = new Predicate("f",list1,new Position(0,0));
		tp1 = new TermPredicate(p1,p1.getPosition());

		Environnement env = new Environnement();
		env.add(tv1,tp1);
		env.add(tv2,tv3);
		
		System.out.println(env);
		System.out.println(tp3.accept(new Subst(env)));
		System.out.println("------------------------------------------");
		Equations equation1 = Exercice7.equation1();
		System.out.println(equation1.unify(env));
	}
}
