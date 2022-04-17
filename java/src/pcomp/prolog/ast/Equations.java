package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

/*
 * AST : classe des equations
 * 
 * represente un systeme d'equations
 */
public class Equations {
	private List<Term[]> system;
	
	public Equations() {
		this.system = new ArrayList<Term[]>();
	}
	
	public boolean add(Term left, Term right) {
		//ajout d'une equation au system
		//de la forme "Term[2] = {left,right}" tel que l'equation représenté est "left = right"
		
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
	
	
	
	//=====================================================================
	//les regles de transformation pour l'unification :
	
	public boolean effacer() {
		//si les deux termes sont egaux, on enleve cette equation du systeme
		
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
		//Pour inversé l'orientation d'une equation si right est une variable et left ne l'est pas
		
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
		//Si une des equations est de la forme p(...)=p(...) pour tout p, alors on décompose les egalités de leurs parametres

		boolean changesMade = false;
		List<Term[]> res = new ArrayList<Term[]>();
		
		for (Term[] equation : system) {
			//Sauvegarde de l'equation au depart pour voir si il y a eu des changements a la fin de la boucle
			Term left = equation[0];
			Term right = equation[1];
			//si les deux termes sont deux memes predicat
			if (left instanceof TermPredicate && right instanceof TermPredicate) {
				Predicate leftp = ((TermPredicate) left).getPredicate();
				Predicate rightp = ((TermPredicate) right).getPredicate();
				if (!(leftp.getArguments().isEmpty()) && leftp.getSymbol().equals(rightp.getSymbol()) && leftp.getArguments().size() == rightp.getArguments().size()) {
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
		//on parcours le system et lorsqu'on trouve une variable qui apparait dans l'environement, on la substitue

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
		//fonction pour ajouter notre system à l'environement
		
		boolean changesMade = false;
		
		for (Term[] equation : system) {
			Term left = equation[0];
			Term right = equation[1];
			
			//On ajoute la variable selement si elle n'etais pas deja dans l'environement
			if (left instanceof TermVariable && !env.contains(left)) {
				if (right.accept(new OccurCheck((TermVariable) left))) {
					throw new OccurCheckException("OccurcheckException : "+equation, (TermVariable) left);
				}
				env.add((TermVariable) left, right);
				changesMade = true;
			}
		}
		//On retourne true si on a modifié l'environement, false sinon
		return changesMade;
	}
	
	public Environnement unify(Environnement env) {
		//si le systeme est vide on retourne l'environement de depart
		if (system.isEmpty()) {
			return env;
		}
		
		//variable qui determine si un changement a été effectué sur l'environement lors de la fonction
		//A la fin de notre fonction, cette variable nous sert de condition d'arret de la récursion
		boolean changesMade = false;
		
		// 1 : remplacement
		remplacer(env);
		
		// 2 : Décomposition
		if (!changesMade) {
			changesMade = decomposer();
			
		// 3 : Effacement
		}if (!changesMade) {
			changesMade = effacer();
		
		// 4 : Orientation
		}if (!changesMade) {
			changesMade = orienter();
		
		//Si il n'y a pas eu de changement, fin de la récurtion, on ajoute à l'environement le system grace a la fonction ajoutSubst
		}if (!changesMade) {
			try {
				changesMade = ajoutSubst(env);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Si il y a eu des changements, on rappelle unify
		if (changesMade) {
			return unify(env);
		}else {
			return env;
		}
	}
}
