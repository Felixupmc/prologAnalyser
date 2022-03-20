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
        Equations eq = new Equations();
        for(Decl dec:prog.getDeclarations()) {
            if(dec instanceof DeclAssertion) {
                faits.add((DeclAssertion)dec);
            }
            if(dec instanceof DeclGoal) {
                buts.add((DeclGoal)dec);
            }
        }
        for(DeclGoal but:buts) {
            for(DeclAssertion fait:faits) {
                if(fait.getHead().getSymbol().equals(but.getPredicates().get(0).getSymbol())) {
                    eq.add(new TermPredicate(fait.getHead(),fait.getPosition()),new TermPredicate(but.getPredicates().get(0),but.getPosition()));
                }
            }
        }
        Environnement env = new Environnement();
        return eq.unify(env);
        
    }
}
