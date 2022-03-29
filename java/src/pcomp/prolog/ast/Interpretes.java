package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class Interpretes {
    
    public static Environnement interprete0(Program prog) {
    	DeclAssertion fait = (DeclAssertion)prog.getDeclarations().get(0);
    	DeclGoal but = (DeclGoal)prog.getDeclarations().get(1);
    	Equations eq = new Equations();
    	eq.add(new TermPredicate(fait.getHead(),fait.getPosition()),new TermPredicate(but.getPredicates().get(0),but.getPosition()));
    	Environnement env = new Environnement();
    	return eq.unify(env);
    }
    
    public static Environnement interprete1(Program prog) {
    	DeclAssertion fait = null;
    	DeclGoal but = null;
    	Equations eq = new Equations();
    	//on trouve le but
    	for (Decl d : prog.getDeclarations()) {
    		if (d instanceof DeclGoal) {
    			but = (DeclGoal) d;
    			break;
    		}
    	}
    	
    	//on trouve le fait associe
    	for (Decl d : prog.getDeclarations()) {
    		if (d instanceof DeclAssertion) {
    			fait = (DeclAssertion) d;
    			if (fait.getHead().getSymbol().equals(but.getPredicates().get(0).getSymbol())) {
					break;
				}
    		}
    	}
    	
    	//on ajoute l'equation
    	eq.add(new TermPredicate(fait.getHead(),fait.getPosition()),new TermPredicate(but.getPredicates().get(0),but.getPosition()));
    	Environnement env = new Environnement();
    	return eq.unify(env);
    }
    
    public static Environnement interprete2(Program prog) {
        List<DeclAssertion> faits = new ArrayList<DeclAssertion>();
        List<DeclGoal> buts = new ArrayList<DeclGoal>();
        List<Equations> leq = new ArrayList<Equations>();
        for(Decl dec:prog.getDeclarations()) {
            if(dec instanceof DeclAssertion) {
                faits.add((DeclAssertion)dec);
            }
            if(dec instanceof DeclGoal) {
                buts.add((DeclGoal)dec);
            }
        }
        for(DeclGoal but:buts) {
        	for(Predicate p:but.getPredicates()) {
        		Equations eq = new Equations();
        		for(DeclAssertion fait:faits) {
                    if(fait.getHead().getSymbol().equals(p.getSymbol())) {
                        eq.add(new TermPredicate(fait.getHead(),fait.getPosition()),new TermPredicate(p,p.getPosition()));
                        break;
                    }
                }
        		leq.add(eq);
        	}
        }
        Environnement env = new Environnement();
        for (Equations e : leq) {
			env.putAll(e.unify(new Environnement()));
		}
        return env;
    }
    
    public static List<Predicate> choose(int n, Environnement env, DeclGoal but, List<DeclAssertion> regles) throws Exception {
        for(DeclAssertion fait:regles) {
        	for(Predicate p:but.getPredicates()) {
        		if(fait.getHead().getSymbol().equals(p.getSymbol())) {
                    DeclRename visitor = new DeclRename(n);
                    DeclAssertion faitPrim = visitor.visit(fait);
                    Equations eq = new Equations();
                    eq.add(new TermPredicate(faitPrim.getHead(),faitPrim.getPosition()),new TermPredicate(p,p.getPosition()));
                    env.putAll(eq.unify(env));
                    return fait.getPredicates();//TODO : rename all predicates
                }
        	}
        }
        throw new Exception(" aucune règle ne permet de faire l’unification");
    }
    
    public static Environnement solve (List<DeclGoal> goals, List<DeclAssertion> rules) throws Exception {
    	Environnement env = new Environnement();
    	List<Predicate> lp;
    	int n=0;
    	while (!goals.isEmpty()) {
    		lp = choose(n++, env, goals.remove(0), rules);
    		if (!lp.isEmpty()) 
    			goals.add(new DeclGoal(lp, lp.get(0).getPosition()));
    	}
    	if (env.isEmpty())
    		throw new Exception("aucun environnement existe");
    	return env;
    }

    public static Environnement interprete3(Program prog) throws Exception {
		List<DeclAssertion> faits = new ArrayList<DeclAssertion>();
		List<DeclGoal> buts = new ArrayList<DeclGoal>();
    	for (Decl d : prog.getDeclarations()) {
    		if (d instanceof DeclAssertion) {
    			faits.add((DeclAssertion) d);
    		} else if (d instanceof DeclGoal) {
    			buts.add((DeclGoal) d);
    		}
    	}
    	return solve(buts, faits);
    }
    
    
}
