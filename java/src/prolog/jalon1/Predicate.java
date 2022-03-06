package prolog.jalon1;

public class Predicate extends Expression {
	public Expression[] parameters;	
	
	public Predicate(String label, Expression[] parameters) {
		super(label);
		this.parameters = new Expression[parameters.length];
		for (int i=0; i<parameters.length; i++) {
			this.parameters[i] = parameters[i];
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(label+"(");
		sb.append(parameters[0]);
		for (int i=1; i<parameters.length; i++) {
			sb.append(", "+parameters[i].toString());
		}
		sb.append(")");
		return sb.toString();
	}
}
