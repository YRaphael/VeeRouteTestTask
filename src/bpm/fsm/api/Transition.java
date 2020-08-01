package bpm.fsm.api;

import bpm.fsm.core.State;

public interface Transition<E extends StateMachineEvent, S extends State> {
    void setName(String name);

    String getName();

    void setSourceState(S sourceState);

    S getSourceState();

    void setTargetState(S targetState);

    S getTargetState();

    void setEventType(Class<E> eventType);

    Class<E> getEventType();

    void setEventHandler(EventHandler<E> eventHandler);

    EventHandler<E> getEventHandler();
}
