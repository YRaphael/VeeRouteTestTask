package bpm.fsm.api;

import bpm.fsm.core.State;

import java.util.Set;

public interface StateMachine<S extends State> {
    void registerTransition(Transition<StateMachineEvent, S> transition);

    void registerFinalState(S finalState);

    Set<S> getStates();

    S getInitialState();

    Set<S> getFinalStates();

    S getCurrentState();

    Set<Transition<StateMachineEvent, S>> getTransitions();

    StateMachineEvent getLastStateMachineEvent();

    Transition<StateMachineEvent, S> getLastTransition();

    S fire(StateMachineEvent stateMachineEvent) throws StateMachineException;
}
