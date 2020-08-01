package bpm.fsm.test;

import bpm.fsm.api.AbstractStateMachineEvent;

class CoinStateMachineEvent extends AbstractStateMachineEvent {

    public CoinStateMachineEvent() {
        super("CoinEvent");
    }

    protected CoinStateMachineEvent(String name) {
        super(name);
    }
}