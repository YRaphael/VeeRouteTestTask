package bpm.fsm.core;

import bpm.fsm.api.StateMachine;
import bpm.fsm.api.Transition;

public class TransitionDefinitionValidator {
    public void validateTransitionDefinition(Transition transition, StateMachine stateMachine) {
        String transitionName = transition.getName();
        State sourceState = transition.getSourceState();
        State targetState = transition.getTargetState();

        if (sourceState == null) {
            throw new IllegalArgumentException("No source state is defined for transition '" + transitionName + "'");
        }
        if (targetState == null) {
            throw new IllegalArgumentException("No target state is defined for transition '" + transitionName + "'");
        }
        if (transition.getEventType() == null) {
            throw new IllegalArgumentException("No event type is defined for transition '" + transitionName + "'");
        }
        if (!stateMachine.getStates().contains(sourceState)) {
            throw new IllegalArgumentException("Source state '" + sourceState.getName() + "' is not registered in FSM states for transition '" + transitionName + "'");
        }
        if (!stateMachine.getStates().contains(targetState)) {
            throw new IllegalArgumentException("target state '" + targetState.getName() + "' is not registered in FSM states for transition '" + transitionName + "'");
        }
    }
}
