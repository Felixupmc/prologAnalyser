package pcomp.prolog.ast;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		for (Entry<String, Term> entry : env.entrySet()) {
			sb.append(entry.getKey());
			sb.append(" -> ");
			sb.append(entry.getValue().toString());
			sb.append("; ");
		}
		//sb.delete(sb.length()-2, sb.length());
		sb.append('}');
		return sb.toString();
	}
}
