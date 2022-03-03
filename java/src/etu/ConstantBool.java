package etu;

public class ConstantBool implements Constant{
	private boolean c;
	
	public ConstantBool(boolean b) {
		this.c = b;
	}
	
	@Override
	public Constant eval() {
		return this;
	}

	public boolean getVal() {
		return c;
	}
	
}
