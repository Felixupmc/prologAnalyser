package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {
	
	public static Environnement solve(Journal j, List<Predicate> goals, List<DeclAssertion> rules, Environnement env) throws Exception {
		System.out.println("solve("+j+", "+goals+", "+rules+", "+env+")");
		while (!goals.isEmpty()) {
			Predicate but = goals.remove(0);
			if (j.isEmpty()) throw new Exception("impossible de resoudre le but");
			List<DeclAssertion> faits = j.pop();
			//on teste toutes les regles
			for (DeclAssertion fait : faits) {
				Context context = new Context(goals, rules, env);
				try {
					if (fait.getPredicates().isEmpty()) {
						Predicate head = fait.getHead();
						
						break; //on a trouvé la solution a ce but
					}
					//on resouds les predicats a droite du fait
					Journal jFils = new Journal(fait.getPredicates(),rules);
					env=solve(jFils,fait.getPredicates(),rules,env);
					
				} catch (Exception e) {
					env = context.env;
					goals = context.goals;
					rules = context.rules;
					continue;
				}
			}
		}
		if (env.isEmpty())
    		throw new Exception("aucun environnement existe");
    	return env;
	}
	
	public static Environnement interprete4(Program p) throws Exception{
		List<Predicate> goals = new ArrayList<>();
		List<DeclAssertion> rules = new ArrayList<>();
		for (Decl d : p.getDeclarations()) {
			if (d instanceof DeclAssertion) {
				rules.add((DeclAssertion) d);
			} else if (d instanceof DeclGoal) {
				goals.addAll(((DeclGoal) d).getPredicates());
			}
		}
		Journal j = new Journal(goals, rules);
		return solve(j,goals,rules,new Environnement());	
	}
}

/*
Predicate head = regle.getHead();
if (head.getSymbol()!=but.getSymbol() || head.getArguments().size()!=but.getArguments().size()) continue;
j.put(regle);

			if (head.getSymbol()!=but.getSymbol() || head.getArguments().size()!=but.getArguments().size()) continue;
			//si la regle est une assertion terminale
			if (regle.getPredicates().isEmpty()) {
				Equations eq = new Equations();
				eq.add(new TermPredicate(but, but.getPosition()), new TermPredicate(head, head.getPosition()));
				env=eq.unify(env);
				continue;
			}
			Journal jFils = new Journal();
			for (Predicate sousBut : regle.getPredicates()) {
				for (DeclAssertion assertion : rules) {
					head = assertion.getHead();
					if (head.getSymbol()!=sousBut.getSymbol() || head.getArguments().size()!=sousBut.getArguments().size()) continue;
					
				}
			}
			jFils.reverse();
*/