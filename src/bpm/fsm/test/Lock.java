package bpm.fsm.test;

import bpm.fsm.api.EventHandler;

class Lock implements EventHandler<PushStateMachineEvent> {

    public void handleEvent(PushStateMachineEvent event) {
        System.out.println("Notified about event '" + event.getName() + "'");
        System.out.println("Locking turnstile..");
    }

}