package bpm.api;

import bpm.fsm.core.State;

import java.util.Map;

public abstract class Event extends State {

    public Event(String name) {
        super(name);
    }

    public abstract boolean canExecute(Executor executor);

    public abstract Map<String, Object> execute(Executor executor);

    public abstract void setProperties(Map<String, Object> properties);

    public abstract Map<String, Object> getProperties();

}
