package etu;

public class Add implements Expression{
	private Expression gauche,droite;
	
	public Add(Expression g,Expression d) {
		this.gauche = g;
		this.droite = d;
	}
	
	@Override
	public Constant eval() {
		if(gauche.getClass()==droite.getClass()) {
			if(gauche instanceof ConstantInt && droite instanceof ConstantInt) {
				ConstantInt g = (ConstantInt) gauche;
				ConstantInt d = (ConstantInt) droite;
				return new ConstantInt(g.getVal() + d.getVal());
			}
			if(gauche instanceof ConstantBool && droite instanceof ConstantBool) {
				ConstantBool g = (ConstantBool) gauche;
				ConstantBool d = (ConstantBool) droite;
				return new ConstantBool(g.getVal() || d.getVal());
			}
		}
		System.out.println("gauche et droite non compatibles : Illegal add");
		return null;
	}

}
