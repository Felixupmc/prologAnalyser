package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class TermApply implements TermVisitor<Term> {
	Environnement env;
	
	public TermApply(Environnement env) {
		super();
		this.env = env;
	}

	@Override
	public Term visit(TermVariable termVariable) {
		if (env.contains(termVariable)) {
			return env.get(termVariable);
		}
		return termVariable;
	}

	@Override
	public Term visit(TermPredicate termPredicate) {
		TermPredicate res;
		Predicate p = termPredicate.getPredicate();
		List<Term> args = new ArrayList<>();
		for (Term t : p.getArguments()) {
			args.add(t.accept(this));
		}
		res = new TermPredicate(new Predicate(p.getSymbol(), args, p.getPosition()), termPredicate.getPosition());
		return res;
	}

}
