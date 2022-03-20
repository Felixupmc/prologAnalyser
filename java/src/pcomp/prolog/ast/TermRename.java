package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

/*
 * AST : renomage de terme
 * ce visiteur permet de renommer toutes les variables d'un terme
 * en leur rajoutant n
 */
public class TermRename implements TermVisitor<Term> {
	private int n;
	public TermRename(int n) {
		this.n=n;
	}

	@Override
	public TermVariable visit(TermVariable termVariable) {
		return new TermVariable(termVariable.getName()+Integer.toString(n), termVariable.getPosition());
	}

	@Override
	public TermPredicate visit(TermPredicate termPredicate) {
		List<Term> args = new ArrayList<Term>();
		for (Term arg : termPredicate.getPredicate().getArguments()) {
			args.add(arg.accept(this));
		}
		return new TermPredicate(new Predicate(termPredicate.getPredicate().getSymbol(), args, termPredicate.getPredicate().getPosition()),termPredicate.getPosition());
	}

}
