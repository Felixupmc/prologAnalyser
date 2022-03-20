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
 * AST : classe des assertions.
 *
 * Une assertion est une déclaration de la forme "head :- pred, ..., pred."
 */
public class DeclAssertion implements Decl {

	// Attributs
	////////////////

	private final Position pos;
	private final Predicate head; // en-tête, à gauche de :-
	private final List<Predicate> preds; // prédicats, à droite de :-


	// Constructeurs
	/////////////////////////

	// assertion avec membres droit
	public DeclAssertion(Predicate head, List<Predicate> preds, Position pos) {
		this.head = head;
		this.preds = preds;
		this.pos = pos;
	}

	// fait : assertion sans membre droit
	public DeclAssertion(Predicate head, Position pos) {
		this(head, Collections.emptyList(), pos);
	}


	// Getters
	///////////////

	public Predicate getHead() {
		return head;
	}

	public List<Predicate> getPredicates() {
		return preds;
	}
	
	public Position getPosition() {
		return pos;
	}


	// Conversion en chaîne
	/////////////////////////////////////

	@Override public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(head);
		if (!preds.isEmpty()) {
			buf.append(" :- ");
			boolean first = true;
			for (Predicate t : preds) {
				if (first) first = false; else buf.append(", ");
				buf.append(t.toString());
			}
		}
		buf.append(".");
		return buf.toString();
	}

	// Interface du visiteur
	//////////////////////////////////

	@Override public <T> T accept(DeclVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
