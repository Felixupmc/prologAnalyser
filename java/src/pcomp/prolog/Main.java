 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog;

import java.io.IOException;

import pcomp.prolog.ast.Program;
import pcomp.prolog.parser.PrologParser;
import pcomp.prolog.ast.Decl;
import pcomp.prolog.ast.DeclRename;
import pcomp.prolog.ast.Interpretes;

/*
 * Point d'entrée d'exemple : construction de l'arbre syntaxique abstrait (AST) à partir
 * d'une chaîne ou d'un fichier texte en utilisant un parser ANTLR 4.
 */

public class Main {


	public static void main(String[] args) throws IOException {
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
	}
}
