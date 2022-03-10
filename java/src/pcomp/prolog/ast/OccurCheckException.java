package pcomp.prolog.ast;

@SuppressWarnings("serial")
public class OccurCheckException extends Exception {
	TermVariable var;

	public OccurCheckException(String errorMsg,TermVariable x) {
		super(errorMsg);
		var=x;
	}
}
