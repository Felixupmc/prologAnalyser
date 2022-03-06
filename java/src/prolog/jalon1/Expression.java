package prolog.jalon1;

public abstract class Expression {
	public final String label;
	
	public Expression(String label) {
		this.label = label;
	}
	
	public String toString() {
		return label;
	}
}
