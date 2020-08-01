package bpm.fsm.api;


public abstract class AbstractStateMachineEvent implements StateMachineEvent {
    private final String name;

    protected AbstractStateMachineEvent(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Event" +
                "{name='" +
                name +
                "'" +
                '}';
    }
}
