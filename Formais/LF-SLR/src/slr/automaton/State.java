package slr.automaton;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import slr.exception.InvalidTransitionException;
import slr.expression.RegularExpression;

/**
 * Estado.
 */
public class State implements Comparable<State> {

	private String name;
	private boolean isFinal;
	private TransitionMap transitions;
	
	/**
	 * @param name nome do estado.
	 * @param isFinal true caso o estado seja final.
	 * @param transitions transições do estado.
	 */
	public State(String name, boolean isFinal, TransitionMap transitions) {
		this.name = name;
		this.isFinal = isFinal;
		this.transitions = transitions;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		TransitionMap t = (TransitionMap) this.transitions.clone();
		State s = (State) new State(this.name, this.isFinal(), t);
		t.replaceTargets(this, s);
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof State) {
			State state = (State) obj;
			return state.getName().equals(this.name);
		}
		
		return false;
	}

	@Override
	public int compareTo(State o) {
		return this.getName().compareTo(o.getName());
	}
	
	/**
	 * @param isFinal true se o estado é final.
	 */
	public void setIsFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	/**
	 * @param name nome do estado.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return nome do estado.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return true caso o estado seja final.
	 */
	public boolean isFinal() {
		return isFinal;
	}
	
	/**
	 * @param symbol símbolo de entrada da transição.
	 * @return conjunto de estados de destino da transição.
	 * @throws InvalidTransitionException se não há uma transição pelo símbolo de entrada especificado.
	 */
	public Set<State> transit(char symbol) throws InvalidTransitionException {
		return this.transitions.get(symbol);
	}
	
	/**
	 * @return conjunto de estados alcançáveis.
	 */
	public Set<State> getReachableStates() {
		return this.transitions.getTargetStates();
	}
	
	/**
	 * @return conjunto de estados alcançáveis por Epsilon.
	 */
	public Set<State> getEpsilonClosure() {
		Set<State> states = new TreeSet<State>();
		states.add(this);
		
		try {
			states.addAll(this.transit(RegularExpression.EPSILON));
		} catch (InvalidTransitionException e) {}
		
		return states;
	}

	/**
	 * @return mapa de transições.
	 */
	public TransitionMap getTransitionMap() {
		return this.transitions;
	}

}
