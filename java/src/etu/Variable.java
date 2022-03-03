package etu;

public class Variable implements Expression{
	private String v;
	private Environement env;
	
	public Variable(String val,Environement env) {
		this.v = val;
		this.env = env;
	}

	@Override
	public Constant eval() {
		if(env.getVal(this)==null) {
			System.out.print("Variable non trouvé");
			return null;
		} else {
			return env.getVal(this);
		}
	}

}
