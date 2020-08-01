package bpm.example;

import bpm.api.Event;
import bpm.api.Rule;
import bpm.fsm.core.TransitionImpl;

import java.util.Map;
import java.util.function.Predicate;

public class SlotMachineRule extends TransitionImpl implements Rule {
    private Event sourceEvent;
    private Event targetEvent;
    private Predicate<Map<String, Object>> condition;

    public Event getSourceEvent() {
        return sourceEvent;
    }

    public Event getTargetEvent() {
        return targetEvent;
    }

    public Predicate<Map<String, Object>> getCondition() {
        return condition;
    }

    @Override
    public void setSourceEvent(Event event) {
        this.sourceEvent = event;
    }

    @Override
    public void setTargetEvent(Event event) {
        this.targetEvent = event;
    }

    @Override
    public void setCondition(Predicate<Map<String, Object>> condition) {
        this.condition = condition;
    }
}
