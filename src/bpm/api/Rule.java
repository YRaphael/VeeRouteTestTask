package bpm.api;

import bpm.fsm.api.Transition;

import java.util.Map;
import java.util.function.Predicate;

public interface Rule extends Transition {
    void setSourceEvent(Event event);

    void setTargetEvent(Event event);

    void setCondition(Predicate<Map<String, Object>> condition);

    Event getSourceEvent();

    Event getTargetEvent();

    Predicate<Map<String, Object>> getCondition();
}
