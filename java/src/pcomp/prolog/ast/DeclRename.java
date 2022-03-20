package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

/*
 * AST : renomage declaration
 * ce visiteur permet de renommer toutes les variables
 * des predicats de la declaration
 */
public class DeclRename implements DeclVisitor<Decl> {
	public TermRename tr;
	
	public DeclRename(int n) {
		this.tr = new TermRename(n);
	}
	
	@Override
	public DeclAssertion visit(DeclAssertion a) {
		Predicate head;
		List<Term> args = new ArrayList<>();
		for (Term arg : a.getHead().getArguments()) {
			args.add(arg.accept(tr));
		}
		
		head = new Predicate(a.getHead().getSymbol(), args, a.getHead().getPosition());
		
		List<Predicate> preds = new ArrayList<>();
		for (Predicate p : a.getPredicates()) {
			args = new ArrayList<>();
			for (Term arg : p.getArguments()) {
				args.add(arg.accept(tr));
			}
			preds.add(new Predicate(p.getSymbol(), args, p.getPosition()));
		}
		
		return new DeclAssertion(head, preds, a.getPosition());
	}

	@Override
	public DeclGoal visit(DeclGoal a) {
		List<Term> args = new ArrayList<>();
		List<Predicate> preds = new ArrayList<>();
		for (Predicate p : a.getPredicates()) {
			args = new ArrayList<>();
			for (Term arg : p.getArguments()) {
				args.add(arg.accept(tr));
			}
			preds.add(new Predicate(p.getSymbol(), args, p.getPosition()));
		}
		
		return new DeclGoal(preds, a.getPosition());
	}

}
