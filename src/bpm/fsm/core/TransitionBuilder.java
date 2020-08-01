package bpm.fsm.core;

import bpm.fsm.api.EventHandler;
import bpm.fsm.api.StateMachineEvent;
import bpm.fsm.api.Transition;

public class TransitionBuilder {
    private final Transition transition;


    public TransitionBuilder() {
        transition = new TransitionImpl();
    }

    public TransitionBuilder name(final String name) {
        transition.setName(name);
        return this;
    }

    public TransitionBuilder sourceState(final State sourceState) {
        transition.setSourceState(sourceState);
        return this;
    }

    public TransitionBuilder targetState(final State targetState) {
        transition.setTargetState(targetState);
        return this;
    }

    public TransitionBuilder eventType(final Class<? extends StateMachineEvent> eventType) {
        transition.setEventType(eventType);
        return this;
    }

    public <E extends StateMachineEvent> TransitionBuilder eventHandler(final EventHandler<E> eventHandler) {
        transition.setEventHandler(eventHandler);
        return this;
    }

    public Transition build() {
        return transition;
    }

}
