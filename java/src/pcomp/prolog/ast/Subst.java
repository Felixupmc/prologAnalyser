package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class Subst implements TermVisitor<Term> {
	private Environnement env;
	
	public Subst(Environnement env) {
		this.env = env;
		
	}

	@Override
	public Term visit(TermVariable termVariable) {
		if (env.contains(termVariable)) {
			Term term = env.get(termVariable);
			if (! term.accept(new OccurCheck(termVariable))) {
				return term.accept(this);
			}
		}
		return termVariable;
	}

	@Override
	public Term visit(TermPredicate termPredicate) {
		List<Term> args = new ArrayList<Term>();
		Predicate pred = termPredicate.getPredicate();
		for (Term arg : pred.getArguments()) {
			args.add(arg.accept(this));
		}
		
		return new TermPredicate(new Predicate(pred.getSymbol(),args,pred.getPosition()), termPredicate.getPosition());
	}
	
}
