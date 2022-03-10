// Generated from PrologANTLRGrammar.g4 by ANTLR 4.7.2

package pcomp.prolog.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PrologANTLRGrammarParser}.
 */
public interface PrologANTLRGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PrologANTLRGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PrologANTLRGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologANTLRGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PrologANTLRGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assertion}
	 * labeled alternative in {@link PrologANTLRGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterAssertion(PrologANTLRGrammarParser.AssertionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assertion}
	 * labeled alternative in {@link PrologANTLRGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitAssertion(PrologANTLRGrammarParser.AssertionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Goal}
	 * labeled alternative in {@link PrologANTLRGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterGoal(PrologANTLRGrammarParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Goal}
	 * labeled alternative in {@link PrologANTLRGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitGoal(PrologANTLRGrammarParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Atom}
	 * labeled alternative in {@link PrologANTLRGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterAtom(PrologANTLRGrammarParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Atom}
	 * labeled alternative in {@link PrologANTLRGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitAtom(PrologANTLRGrammarParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Structure}
	 * labeled alternative in {@link PrologANTLRGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterStructure(PrologANTLRGrammarParser.StructureContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Structure}
	 * labeled alternative in {@link PrologANTLRGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitStructure(PrologANTLRGrammarParser.StructureContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Var}
	 * labeled alternative in {@link PrologANTLRGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterVar(PrologANTLRGrammarParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link PrologANTLRGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitVar(PrologANTLRGrammarParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Pred}
	 * labeled alternative in {@link PrologANTLRGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterPred(PrologANTLRGrammarParser.PredContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Pred}
	 * labeled alternative in {@link PrologANTLRGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitPred(PrologANTLRGrammarParser.PredContext ctx);
}