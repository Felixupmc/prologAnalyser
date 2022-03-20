package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class Interpretes {
    
    public Environnement interprete0(Program prog) {
    	DeclAssertion fait = (DeclAssertion)prog.getDeclarations().get(0);
    	DeclGoal but = (DeclGoal)prog.getDeclarations().get(1);
    	Equations eq = new Equations();
    	eq.add(new TermPredicate(fait.getPredicates().get(0),fait.getPosition()),new TermPredicate(but.getPredicates().get(0),but.getPosition()));
    	Environnement env = new Environnement();
    	return eq.unify(env);
    }
    
    public Environnement interprete1(TermPredicate[] faits, TermPredicate but) {
    	List<String> symbols = new ArrayList<String>();
    	for(TermPredicate f:faits) {
    		if(symbols.contains(f.getPredicate().getSymbol())) {
    			System.out.println("Il y a plus d'un seul fait par symbole de prédicat");
    			return null;
    		}
    		symbols.add(f.getPredicate().getSymbol());
    	}
        TermPredicate leFait;
        String symbolBut = but.getPredicate().getSymbol();
        for(TermPredicate t: faits) {
            if(t.getPredicate().getSymbol().equals(symbolBut)) {
                leFait = t;
                return interprete0(leFait,but);
            }
        }    
        return null;
    }
    
    public Environnement interprete2(TermPredicate[] faits, TermPredicate[] but) {
        Environnement env = new Environnement();
        for(TermPredicate t: but) {
            env.putAll(interprete1(faits,t));
        }
        return null;
        
    }
}
