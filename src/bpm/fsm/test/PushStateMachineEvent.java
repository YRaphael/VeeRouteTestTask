package bpm.fsm.test;

import bpm.fsm.api.AbstractStateMachineEvent;

class PushStateMachineEvent extends AbstractStateMachineEvent {

    public PushStateMachineEvent() {
        super("PushEvent");
    }

    protected PushStateMachineEvent(String name) {
        super(name);
    }


}