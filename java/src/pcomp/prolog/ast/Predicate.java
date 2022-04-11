/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

import java.util.Collections;
import java.util.List;

/*
 * AST : classe des prédicats.
 *
 * Un prédicat a la forme f(term1,...,termN).
 * f est son symbole, et term1 à termN sont ses arguments
 */
public class Predicate {

	// Attributs
	////////////////

	private final Position pos; // position du début du prédicat
	private final String symbol; // symbole du prédicat
	private final List<Term> args; // arguments du prédicat


	// Constructeurs
	/////////////////////////

	// constructeur "symbol(args)"
	public Predicate(String symbol, List<Term> args, Position pos) {
		this.symbol = symbol;
		this.args = args;
		this.pos = pos;
	}

	// constructeur d'une constante, i.e., un prédicat sans argument
	public Predicate(String symbol, Position pos) {
		this(symbol, Collections.emptyList(), pos);
	}


	// Getters
	///////////////

	public String getSymbol() {
		return symbol;
	}

	public List<Term> getArguments() {
		return args;
	}

	public Position getPosition() {
		return pos;
	}
	
	// Égalité structurelle (récursive)
	///////////////////////////////////////////////////

	@Override public boolean equals(Object o) {
		// la position n'est pas prise en compte !
		if (this == o) return true;
		if (!(o instanceof Predicate)) return false;
		Predicate p = (Predicate)o;
		if (!symbol.equals(p.symbol)) return false;
		if (args.size() != p.args.size()) return false;
		for (int i = 0; i < args.size(); i++) {
			if (!args.get(i).equals(p.args.get(i))) return false;
		}
		return true;
	}

	@Override public int hashCode() {
		// la position n'est pas prise en compte !
		int hash = symbol.hashCode();
		for (Term t : args) {
			hash = hash * 75 + t.hashCode();
		}
		return hash;
	}

	// Conversion en chaîne
	/////////////////////////////////////

	@Override public String toString() {
		if (args.isEmpty()) return symbol;
		StringBuffer buf = new StringBuffer();
		buf.append(symbol);
		buf.append("(");
		boolean first = true;
		for (Term t : args) {
			if (first) first = false; else buf.append(", ");
			buf.append(t.toString());
		}
		buf.append(")");
		return buf.toString();
	}
	
}
