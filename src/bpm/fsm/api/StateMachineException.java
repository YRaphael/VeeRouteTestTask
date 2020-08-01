package bpm.fsm.api;

public class StateMachineException extends Exception {
    private Transition transition;
    private StateMachineEvent stateMachineEvent;
    private Throwable cause;

    public StateMachineException(final Transition transition, final StateMachineEvent stateMachineEvent, final Throwable cause) {
        this.transition = transition;
        this.stateMachineEvent = stateMachineEvent;
        this.cause = cause;
    }

    public Transition getTransition() {
        return transition;
    }

    public StateMachineEvent getStateMachineEvent() {
        return stateMachineEvent;
    }

    public Throwable getCause() {
        return cause;
    }
}
