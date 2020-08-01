package bpm.fsm.test;

import bpm.fsm.api.EventHandler;

class Unlock implements EventHandler<CoinStateMachineEvent> {

    public void handleEvent(CoinStateMachineEvent event) {
        System.out.println("Notified about event '" + event.getName() + "'");
        System.out.println("Unlocking turnstile..");
    }

}