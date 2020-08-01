package bpm.fsm.api;

public interface EventHandler<T extends StateMachineEvent> {
    void handleEvent(T event);
}
