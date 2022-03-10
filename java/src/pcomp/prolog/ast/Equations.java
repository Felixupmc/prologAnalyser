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
		boolean first = true;
		sb.append('{');
		for (Term[] equation : system) {
			if (first) {
				first = false;
			}else {
				sb.append("; ");
			}
			sb.append(equation[0].toString());
			sb.append(" = ");
			sb.append(equation[1].toString());
		}
		sb.append('}');
		return sb.toString();
	}
	
	
	//les regles de transformation pour l'unification
	public boolean effacer() {
		List<Term[]> toRemove = new ArrayList<Term[]>();
		
		for (Term[] equation : system) {
			Term left  = equation[0];
			Term right = equation[1];
			//si les deux termes sont egaux
			if (left.equals(right)) {
				//on enleve cette equation du systeme
				toRemove.add(equation);
			}
		}
		
		if (toRemove.isEmpty()) {
			return false;
		}
		system.removeAll(toRemove);
		return true;

	}
	
	public boolean orienter() {
		List<Term[]> toRemove = new ArrayList<Term[]>();
		for (Term[] equation : system) {
			Term left  = equation[0];
			Term right = equation[1];
			//si right est une variable et left ne l'est pas
			if (right instanceof TermVariable && !(left instanceof TermVariable)) {
				toRemove.add(equation);
			}
		}
		
		if (toRemove.isEmpty()) {
			return false;
		}
		system.removeAll(toRemove);
		for (Term[] equation : toRemove) {
			Term[] term = new Term[2];
			term[0] = equation[1];
			term[1] = equation[0];
			
			system.add(term);
		}
		return true;
	}
	
	public boolean decomposer() {
		boolean changesMade = false;
		List<Term[]> res = new ArrayList<Term[]>();
		
		for (Term[] equation : system) {
			Term left = equation[0];
			Term right = equation[1];
			//si les deux termes sont deux memes predicat
			if (left instanceof TermPredicate && right instanceof TermPredicate) {
				Predicate leftp = ((TermPredicate) left).getPredicate();
				Predicate rightp = ((TermPredicate) right).getPredicate();
				if (!(leftp.getArguments().isEmpty()) && leftp.getSymbol() == rightp.getSymbol() && leftp.getArguments().size() == rightp.getArguments().size()) {
					// on decompose les deux fonctions
					for (int i=0; i<leftp.getArguments().size(); i++) {
						Term[] tmp = new Term[2];
						tmp[0] = leftp.getArguments().get(i);
						tmp[1] = rightp.getArguments().get(i);
						
						res.add(tmp);
					}
					changesMade = true;
				}else {
					res.add(equation);
				}
			}else {
				res.add(equation);
			}
		}
		system = res;
		return changesMade;
	}
	
	public boolean remplacer(Environnement env) {
		boolean changesMade = false;
		
		for (int t=0; t<system.size(); t++) {
			Term[] term = system.get(t);
		    //on applique l'environnmenet
			Term left = term[0].accept((TermVisitor<Term>) new Subst(env));
		    Term right = term[1].accept((TermVisitor<Term>) new Subst(env));
		    changesMade = changesMade || !(left.equals(term[0])) || !(right.equals(term[1]));
		    term[0] = left;
		    term[1] = right;
		}
		  
		return changesMade;
	}	
	
	
	public boolean ajoutSubst(Environnement env) throws OccurCheckException {
		boolean changesMade = false;
		
		for (Term[] equation : system) {
			Term left = equation[0];
			Term right = equation[1];
			
			if (left instanceof TermVariable && !env.contains(left)) {
				if (right.accept(new OccurCheck((TermVariable) left))) {
					throw new OccurCheckException("OccurcheckException : "+equation, (TermVariable) left);
				}
				env.add((TermVariable) left, right);
				changesMade = true;
			}
		}
		return changesMade;
	}
	
	public Environnement unify(Environnement env) {
		boolean changesMade = true;
		while(changesMade && !system.isEmpty()) {
			changesMade = false;
			remplacer(env);
			changesMade = decomposer() || changesMade;
			changesMade = effacer() || changesMade;
			changesMade = orienter() || changesMade;
			try {
				changesMade = ajoutSubst(env) || changesMade;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return env;
	}
}
