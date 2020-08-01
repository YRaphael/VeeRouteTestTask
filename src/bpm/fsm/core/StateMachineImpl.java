package bpm.fsm.core;

import bpm.fsm.api.StateMachine;
import bpm.fsm.api.StateMachineEvent;
import bpm.fsm.api.StateMachineException;
import bpm.fsm.api.Transition;

import java.util.HashSet;
import java.util.Set;

public class StateMachineImpl<S extends State> implements StateMachine<S> {

    private final S initialState;
    private final Set<S> states;
    private final Set<S> finalStates;
    private final Set<Transition<StateMachineEvent, S>> transitions;
    private S currentState;
    private StateMachineEvent lastStateMachineEvent;
    private Transition<StateMachineEvent, S> lastTransition;

    public StateMachineImpl(Set<S> states, S initialState) {
        this.states = states;
        this.initialState = initialState;
        currentState = initialState;
        transitions = new HashSet<>();
        finalStates = new HashSet<>();
    }

    @Override
    public void registerTransition(Transition<StateMachineEvent, S> transition) {
        transitions.add(transition);
    }

    @Override
    public void registerFinalState(S finalState) {
        finalStates.add(finalState);
    }

    @Override
    public Set<S> getStates() {
        return this.states;
    }

    @Override
    public S getInitialState() {
        return this.initialState;
    }

    @Override
    public Set<S> getFinalStates() {
        return this.finalStates;
    }

    @Override
    public S getCurrentState() {
        return this.currentState;
    }

    @Override
    public Set<Transition<StateMachineEvent, S>> getTransitions() {
        return this.transitions;
    }

    @Override
    public StateMachineEvent getLastStateMachineEvent() {
        return this.lastStateMachineEvent;
    }

    @Override
    public Transition<StateMachineEvent, S> getLastTransition() {
        return this.lastTransition;
    }

    @Override
    public synchronized S fire(StateMachineEvent stateMachineEvent) throws StateMachineException {
        if (!finalStates.isEmpty() && finalStates.contains(currentState)) {
            System.out.println("FSM is in final state '" + currentState.getName() + "', event " + stateMachineEvent + " is ignored.");
            return currentState;
        }

        if (stateMachineEvent == null) {
            System.out.println("Null event fired, FSM state unchanged");
            return currentState;
        }

        for (Transition<StateMachineEvent, S> transition : transitions) {
            if (
                    currentState.equals(transition.getSourceState()) &&
                            transition.getEventType().equals(stateMachineEvent.getClass()) &&
                            states.contains(transition.getTargetState())
            ) {
                try {
                    if (transition.getEventHandler() != null) {
                        transition.getEventHandler().handleEvent(stateMachineEvent);
                    }
                    currentState = transition.getTargetState();

                    lastStateMachineEvent = stateMachineEvent;
                    lastTransition = transition;

                    break;
                } catch (Exception e) {
                    System.out.println("An exception occurred during handling event " + stateMachineEvent + " of transition " + transition);
                    throw new StateMachineException(transition, stateMachineEvent, e);
                }
            }
        }
        return currentState;
    }
}
