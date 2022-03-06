package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

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
		List<Term> args = new ArrayList<Term>();
		for (Term arg : termPredicate.getPredicate().getArguments()) {
			//si t est une varible
			if (arg instanceof TermVariable) {
				if (x.equals((TermVariable)arg)) {
					return true;
				}
			}else {
				if (arg.accept(this)) {
					return true;
				}
			}
			
		}
		return false;
	}
}
