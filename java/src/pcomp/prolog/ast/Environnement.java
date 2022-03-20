package pcomp.prolog.ast;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * AST : classe des environnement
 * 
 * un environnement contient des remplacements a effectuer
 */
public class Environnement {
	private Map<String, Term> env;
	
	public Environnement() {
		env = new HashMap<String, Term>();
	}
	
	public Term add(String var, Term t) {
		return env.put(var, t);
	}
	public Term add(TermVariable var, Term t) {
		return add(var.getName(), t);
	}
	
	public Term get(String var) {
		return env.get(var);
	}
	public Term get(TermVariable var) {
		return get(var.getName());
	}
	
	public Set<String> keySet() {
		return env.keySet();
	}
	
	public boolean contains(TermVariable key) {
		return contains(key.getName());
	}
	
	public boolean contains(Object key) {
		return env.containsKey(key);
	}
	
	public void putAll(Environnement env) {
		this.env.putAll(env.env);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		sb.append('{');
		for (Entry<String, Term> entry : env.entrySet()) {
			if (first) {
				first = false;
			}else {
				sb.append("; ");
			}
			sb.append(entry.getKey());
			sb.append(" -> ");
			sb.append(entry.getValue().toString());
		}
		sb.append('}');
		return sb.toString();
	}
}
