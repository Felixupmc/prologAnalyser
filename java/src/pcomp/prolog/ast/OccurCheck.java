package pcomp.prolog.ast;

/*
 * AST: classe occurcheck
 * 
 * realise un occcurcheck d'une variable sur un terme
 * cette classe suit le desing pattern visiteur
 */

public class OccurCheck implements TermVisitor<Boolean> {
	private TermVariable x;
	
	public OccurCheck(TermVariable x) {
		this.x = x;
	}

	@Override
	public Boolean visit(TermVariable termVariable) {
		return termVariable.equals(x);
	}

	@Override
	public Boolean visit(TermPredicate termPredicate) {
		//pour chaque argument du predicat
		for (Term arg : termPredicate.getPredicate().getArguments()) {
			if (arg.accept(this)) {
				return true;
			}			
		}
		return false;
	}
}
