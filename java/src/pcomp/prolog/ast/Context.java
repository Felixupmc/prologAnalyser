package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class Context {
	public final List<Predicate> goals;
	public final List<DeclAssertion> rules;
	public final Environnement env;
	
	public Context(List<Predicate> goals, List<DeclAssertion> rules, Environnement env) {
		this.goals=new ArrayList<>();
		this.rules=new ArrayList<>();
		this.env=new Environnement();
		this.goals.addAll(goals);
		this.rules.addAll(rules);
		this.env.putAll(env);
	}
}
