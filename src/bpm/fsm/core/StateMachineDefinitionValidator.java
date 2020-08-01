package bpm.fsm.core;

import bpm.fsm.api.StateMachine;

import java.util.Set;

public class StateMachineDefinitionValidator {
    public void validateStateMachineDefinition(StateMachine<State> stateMachine) {
        Set<State> states = stateMachine.getStates();

        State initialState = stateMachine.getInitialState();
        if (!states.contains(initialState)) {
            throw new IllegalStateException("Initial state '" + initialState.getName() + "' must belong to SM states.");
        }

        for (State finalState : stateMachine.getFinalStates()) {
            if (!states.contains(finalState)) {
                throw new IllegalStateException("Final state '" + finalState.getName() + "' must belong to SM states.");
            }
        }
    }
}
