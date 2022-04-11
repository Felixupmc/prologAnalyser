package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class Context {
	public final List<Predicate> goals;
	public final List<DeclAssertion> rules;
	public final Environnement env;
	
	public Context(List<Predicate> goals, List<DeclAssertion> rules, Environnement env) {
		this.goals=predicatesCopy(goals);
		this.rules=rulesCopy(rules);
		this.env=new Environnement();
		this.env.putAll(env);
	}
	
	private static List<Predicate> predicatesCopy(List<Predicate> l) {
		List<Predicate> res = new ArrayList<>();
		for (Predicate p : l) {
			Predicate copy = new Predicate(p.getSymbol(), termsCopy(p.getArguments()),p.getPosition());
			res.add(copy);
		}
		return res;
	}

	private static List<Term> termsCopy(List<Term> l) {
		List<Term> res = new ArrayList<>();
		for (Term t : l) {
			if (t instanceof TermPredicate) {
				TermPredicate tp = (TermPredicate) t;
				Predicate p = tp.getPredicate();
				TermPredicate copy = new TermPredicate(new Predicate(p.getSymbol(), p.getPosition()), tp.getPosition());
				res.add(copy);
			} else if (t instanceof TermVariable) {
				TermVariable tv = (TermVariable) t;
				TermVariable copy = new TermVariable(tv.getName(), tv.getPosition());
				res.add(copy);
			}
		}
		return res;
	}

	private static List<DeclAssertion> rulesCopy(List<DeclAssertion> l) {
		List<DeclAssertion> res = new ArrayList<>();
		for (DeclAssertion da : l) {
			Predicate head = da.getHead();
			List<Predicate> p = da.getPredicates();
			DeclAssertion copy = new DeclAssertion(new Predicate(head.getSymbol(), termsCopy(head.getArguments()),head.getPosition()), predicatesCopy(da.getPredicates()),da.getPosition());
			res.add(copy);
		}
		return res;
	}
}
