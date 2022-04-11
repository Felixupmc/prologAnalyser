 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog;

import java.util.ArrayList;
import java.util.List;

import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.Term;
import pcomp.prolog.ast.TermPredicate;
import pcomp.prolog.ast.TermVariable;
import pcomp.prolog.parser.PrologParser;
import pcomp.prolog.ast.Backtracking;
import pcomp.prolog.ast.Decl;
import pcomp.prolog.ast.DeclAssertion;
import pcomp.prolog.ast.DeclGoal;
import pcomp.prolog.ast.DeclRename;
import pcomp.prolog.ast.Environnement;
import pcomp.prolog.ast.Interpretes;
import pcomp.prolog.ast.Predicate;

/*
 * Point d'entrée d'exemple : construction de l'arbre syntaxique abstrait (AST) à partir
 * d'une chaîne ou d'un fichier texte en utilisant un parser ANTLR 4.
 */

public class Main {


	public static void main(String[] args) throws Exception {
		Program p1 = PrologParser.parseString("?- p(a,f(a,X)).");
		System.out.println(p1);

		Program p2 =PrologParser.parseFile("exemples/classification.pl");
		System.out.println(p2);

		Program p3 = PrologParser.parseFile("exemples/genealogie.pl");
		System.out.println(p3);

		Program p4 = PrologParser.parseFile("exemples/test_list.pl");
		System.out.println(p4);
		
		Program j2p1 = PrologParser.parseString("p(Z,h(Z,W),f(W)).?- p(f(X),h(Y,f(a)),Y).");
		System.out.println(j2p1);
		System.out.println(Interpretes.interprete0(j2p1));
		for (Decl d : j2p1.getDeclarations()) {
			System.out.println(d.accept(new DeclRename(1)));
		}
		// test Q.2.3
		System.out.println("\ntests Q.2.3 :");
		Program p_2_3 = PrologParser.parseString("r(X,Y,h(Z)).q(Z).p(Z,h(Z,W),f(W)).?- p(f(X),h(Y,f(a)),Y),q(f(W)),r(Y,Y,h(Y)).");
		System.out.println(Interpretes.interprete2(p_2_3));
		// test Q.3.2
		System.out.println("\ntests Q.3.2 :");
		Program p = PrologParser.parseString("p(Z,h(Z,W),f(W)) :- r(X,XX),q(XX).r(F,FF).q(G,GG).?- p(f(X),h(Y,a),Y),q(a).");
		List<DeclAssertion> l = new ArrayList<DeclAssertion>();
		l.add((DeclAssertion) p.getDeclarations().get(0));
		Environnement e = new Environnement();
		List<Predicate> res = Interpretes.choose(1, e, ((DeclGoal)(p.getDeclarations().get(3))).getPredicates().get(0), l);
		System.out.println(p);
		System.out.println(res);
		System.out.println(e);
		System.out.println(Interpretes.interprete3(p));
		//TEST TEST TEST TEST TEST TEST TEST
		System.out.println("\nTEST TEST TEST");
		List<Predicate> testpred = new ArrayList<>();
		List<Term> testargs = new ArrayList<>();
		testargs.add(new TermPredicate(new Predicate("church", null), null));
		testargs.add(new TermPredicate(new Predicate("worsley", null), null));
		testpred.add(new Predicate("descendant", testargs, null));
		DeclGoal dec = new DeclGoal(testpred, null);
		p3.getDeclarations().add(dec);
		System.out.println(p3);
		System.out.println(Interpretes.interprete3(p3));
		//TEST TEST TEST TEST TEST TEST TEST
		System.out.println("\nTEST TEST TEST");
		p = PrologParser.parseString("p(X):-q(X).p(X):-r(X).q(a).r(b).?-p(b).");
		testpred = new ArrayList<>();
		testargs = new ArrayList<>();
		System.out.println(p);
		System.out.println(Interpretes.interprete3(p));
		System.out.println(Backtracking.interprete4(p));
	}
}
