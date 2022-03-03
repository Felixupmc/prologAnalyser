package etu;

public class ConstantInt implements Constant{
	private int c;
	
	public ConstantInt(int v) {
		this.c = v;
	}
	
	@Override
	public Constant eval() {
		return this;
	}
	
	public int getVal() {
		return c;
	}

}
