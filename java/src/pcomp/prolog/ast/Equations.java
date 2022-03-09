package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class Equations {
	private List<Term[]> system;
	
	public Equations() {
		this.system = new ArrayList<Term[]>();
	}
	
	public boolean add(Term left, Term right) {
		Term[] equation = new Term[2];
		equation[0] = left;
		equation[1] = right;
		
		return system.add(equation);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		for (Term[] equation : system) {
			sb.append(equation[0].toString());
			sb.append(" = ");
			sb.append(equation[1].toString());
			sb.append("; ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append('}');
		return sb.toString();
	}
	
	public Environnement unify(Environnement env) {
		for (Term[] term : system) {
			//on applique l'environnmenet
			term[0].accept((TermVisitor<Term>) new Subst(env));
			term[1].accept((TermVisitor<Term>) new Subst(env));			
			//on verifie si on peut ajouter une substitution a env
			if (term[0] instanceof TermVariable) {
				if (term[1].accept(new OccurCheck((TermVariable) term[0]))) {
					env.add((TermVariable) term[0], term[1]);
				}
			}else if (term[1] instanceof TermVariable) {
				if (term[0].accept(new OccurCheck((TermVariable) term[1]))) {
					env.add((TermVariable) term[1], term[0]);
				}
			}else { //ou on decompose les termes
				Predicate tp1 = ((TermPredicate) term[0]).getPredicate();
				Predicate tp2 = ((TermPredicate) term[1]).getPredicate();
				if (tp1.getSymbol() == tp2.getSymbol() && tp1.getArguments().size() == tp2.getArguments().size()) {
					system.remove(term);
					for (int i=0; i<tp1.getArguments().size(); i++) {
						this.add(tp1.getArguments().get(i), tp2.getArguments().get(i));
					}
				}
			}			
		}
		
		
		return env;
	}
}
