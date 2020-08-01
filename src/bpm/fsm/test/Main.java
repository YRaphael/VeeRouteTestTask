package bpm.fsm.test;

import bpm.fsm.api.StateMachine;
import bpm.fsm.api.Transition;
import bpm.fsm.core.State;
import bpm.fsm.core.StateMachineBuilder;
import bpm.fsm.core.TransitionBuilder;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        State locked = new State("locked");
        State unlocked = new State("unlocked");

        Set<State> states = new HashSet<>();
        states.add(locked);
        states.add(unlocked);

        Transition unlock = new TransitionBuilder()
                .name("unlock")
                .sourceState(locked)
                .eventType(CoinStateMachineEvent.class)
                .eventHandler(new Unlock())
                .targetState(unlocked)
                .build();

        Transition pushLocked = new TransitionBuilder()
                .name("pushLocked")
                .sourceState(locked)
                .eventType(PushStateMachineEvent.class)
                .targetState(locked)
                .build();

        Transition lock = new TransitionBuilder()
                .name("lock")
                .sourceState(unlocked)
                .eventType(PushStateMachineEvent.class)
                .eventHandler(new Lock())
                .targetState(locked)
                .build();

        Transition coinUnlocked = new TransitionBuilder()
                .name("coinUnlocked")
                .sourceState(unlocked)
                .eventType(CoinStateMachineEvent.class)
                .targetState(unlocked)
                .build();

        StateMachine turnstileStateMachine = new StateMachineBuilder(states, locked)
                .registerTransition(lock)
                .registerTransition(pushLocked)
                .registerTransition(unlock)
                .registerTransition(coinUnlocked)
                .build();

//        System.out.println("Turnstile initial state : " + turnstileStateMachine.getCurrentState().getName());

        Scanner scanner = new Scanner(System.in);
//        System.out.println("Which event do you want to fire?");
//        System.out.println("1. Push [p]");
//        System.out.println("2. Coin [c]");
//        System.out.println("Press [q] to quit tutorial.");
        while (true) {
            String input = scanner.nextLine();
            if (input.trim().equalsIgnoreCase("p")) {
//                System.out.println("input = " + input.trim());
//                System.out.println("Firing push event..");
                turnstileStateMachine.fire(new PushStateMachineEvent());
//                System.out.println("Turnstile state : " + turnstileStateMachine.getCurrentState().getName());
            }
            if (input.trim().equalsIgnoreCase("c")) {
//                System.out.println("input = " + input.trim());
//                System.out.println("Firing coin event..");
                turnstileStateMachine.fire(new CoinStateMachineEvent());
//                System.out.println("Turnstile state : " + turnstileStateMachine.getCurrentState().getName());
            }
            if (input.trim().equalsIgnoreCase("q")) {
//                System.out.println("input = " + input.trim());
//                System.out.println("Bye!");
                System.exit(0);
            }

        }
    }
}
