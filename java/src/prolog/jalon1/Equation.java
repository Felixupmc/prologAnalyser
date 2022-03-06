package prolog.jalon1;

public class Equation {
	private Expression left;
	private Expression right;
	
	
	
	@Override
	public String toString() {
		return left.toString() + " = " + right.toString();
	}
}
