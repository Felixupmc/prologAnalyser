package etu;

import java.util.HashMap;
import java.util.Map;

public class Environement{
	private Map<Variable,Constant> varList = new HashMap<Variable,Constant>();
	
	public void affectation(Variable var,Constant con) {
		varList.put(var, con);
	}
	
	public Map<Variable,Constant> getList(){
		return varList;
	}
	
	public Constant getVal(Variable v) {
		return varList.get(v);
	}
	
}

