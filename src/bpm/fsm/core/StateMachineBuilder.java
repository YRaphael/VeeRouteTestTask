package bpm.fsm.core;

import bpm.fsm.api.StateMachine;
import bpm.fsm.api.Transition;

import java.util.Set;

public class StateMachineBuilder {

    private final StateMachine stateMachine;
    private final StateMachineDefinitionValidator stateMachineDefinitionValidator;
    private final TransitionDefinitionValidator transitionDefinitionValidator;

    public StateMachineBuilder(final Set<State> states, final State initialState) {
        stateMachine = new StateMachineImpl(states, initialState);
        stateMachineDefinitionValidator = new StateMachineDefinitionValidator();
        transitionDefinitionValidator = new TransitionDefinitionValidator();
    }

    public StateMachineBuilder registerTransition(final Transition transition) {
        transitionDefinitionValidator.validateTransitionDefinition(transition, stateMachine);
        stateMachine.registerTransition(transition);
        return this;
    }

    public StateMachineBuilder registerTransitions(final Set<Transition> transitions) {
        for (Transition transition : transitions) {
            registerTransition(transition);
        }
        return this;
    }

    public StateMachineBuilder registerFinalState(final State finalState) {
        stateMachine.registerFinalState(finalState);
        return this;
    }

    public StateMachineBuilder registerFinalStates(final Set<State> finalStates) {
        for (State finalState : finalStates) {
            registerFinalState(finalState);
        }
        return this;
    }

    public StateMachine build() {
        stateMachineDefinitionValidator.validateStateMachineDefinition(stateMachine);
        return stateMachine;
    }
}
