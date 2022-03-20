package pcomp.prolog.ast;

public class Interpretes {
	
    public Environnement interprete0(TermPredicate fait, TermPredicate but) {
        Equations eq = new Equations();
        eq.add(fait, but);
        Environnement env = new Environnement();
        return eq.unify(env);
    }
    
    public Environnement interprete1(TermPredicate[] faits, TermPredicate but) {
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
