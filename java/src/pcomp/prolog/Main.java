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
import pcomp.prolog.parser.PrologParser;
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
		
		// test Q.3.2
		System.out.println("\ntests Q.3.2 :");
		Program p = PrologParser.parseString("p(Z,h(Z,W),f(W)) :- r(X,XX),q(XX).?- p(f(X),h(Y,a),Y),q(a).");
		List<DeclAssertion> l = new ArrayList<DeclAssertion>();
		l.add((DeclAssertion) p.getDeclarations().get(0));
		Environnement e = new Environnement();
		List<Predicate> res = Interpretes.choose(1, e, (DeclGoal) p.getDeclarations().get(1), l);
		System.out.println(p);
		System.out.println(res);
		System.out.println(e);
		
	}
}
