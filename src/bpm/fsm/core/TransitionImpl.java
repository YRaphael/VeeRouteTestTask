package bpm.fsm.core;

import bpm.fsm.api.EventHandler;
import bpm.fsm.api.StateMachineEvent;
import bpm.fsm.api.Transition;

public class TransitionImpl<E extends StateMachineEvent, S extends State> implements Transition<E, S> {

    private String name;
    private S sourceState;
    private S targetState;
    private Class<E> eventType;
    private EventHandler<E> eventHandler;

    public TransitionImpl() {
    }

    public TransitionImpl(String name) {
        this.name = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setSourceState(S sourceState) {
        this.sourceState = sourceState;
    }

    @Override
    public S getSourceState() {
        return this.sourceState;
    }

    @Override
    public void setTargetState(S targetState) {
        this.targetState = targetState;
    }

    @Override
    public S getTargetState() {
        return this.targetState;
    }

    @Override
    public void setEventType(Class<E> eventType) {
        this.eventType = eventType;
    }

    @Override
    public Class<E> getEventType() {
        return this.eventType;
    }

    @Override
    public void setEventHandler(EventHandler<E> eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public EventHandler<E> getEventHandler() {
        return this.eventHandler;
    }
}
