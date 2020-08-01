package bpm.example;

import bpm.api.Process;
import bpm.api.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class Main {

    private static final String ADMIN_ROLE_NAME = "ADMIN";
    private static final String USER_ROLE_NAME = "USER";
    private static final String WAIT_INSERT_EVENT_NAME = "waitInsert";
    private static final String WAIT_PUSH_EVENT_NAME = "waitPush";
    private static final String UNLOCK_RULE_NAME = "unlock";
    private static final String LOCK_RULE_NAME = "lock";
    private static final String INSERT_LOCK_RULE_NAME = "insertLock";
    private static final String PUSH_LOCK_RULE_NAME = "pushLock";
    private static final String BUTTON_PRESSED_TRIGGER_SYMBOL = "buttonPressed";
    private static final String COIN_INSERTED_TRIGGER_SYMBOL = "coinInserted";

    public static void main(String[] args) throws Exception {
        Role admin = new Role(ADMIN_ROLE_NAME);
        Role user = new Role(USER_ROLE_NAME);

        Executor executor = new SlotMachineExecutor();
        executor.setRoles(new HashSet<>(Arrays.asList(admin, user)));


        Event waitInsert = new CoinEvent(WAIT_INSERT_EVENT_NAME, new HashSet<>(Arrays.asList(admin, user)));
        Event waitPush = new PushEvent(WAIT_PUSH_EVENT_NAME, new HashSet<>(Arrays.asList(admin, user)));

        Set<Event> events = new HashSet<>(Arrays.asList(waitInsert, waitPush));

        Rule unlock = createRule(waitInsert, waitPush, UNLOCK_RULE_NAME, (Map<String, Object> previousResult) -> previousResult.containsKey(COIN_INSERTED_TRIGGER_SYMBOL));
        Rule lock = createRule(waitPush, waitInsert, LOCK_RULE_NAME, (Map<String, Object> previousResult) -> previousResult.containsKey(BUTTON_PRESSED_TRIGGER_SYMBOL));
        Rule insertLock = createRule(waitPush, waitPush, INSERT_LOCK_RULE_NAME, (Map<String, Object> previousResult) -> previousResult.containsKey(COIN_INSERTED_TRIGGER_SYMBOL));
        Rule pushLock = createRule(waitInsert, waitInsert, PUSH_LOCK_RULE_NAME, (Map<String, Object> previousResult) -> previousResult.containsKey(BUTTON_PRESSED_TRIGGER_SYMBOL));

        Process slotMachine = new SlotMachineProcessBuilder(events, waitInsert, executor)
                .addRule(lock)
                .addRule(insertLock)
                .addRule(unlock)
                .addRule(pushLock)
                .build();

        slotMachine.start();
    }

    private static Rule createRule(Event sourceEvent, Event targetEvent, String name, Predicate<Map<String, Object>> condition) {
        return new SlotMachineRuleBuilder()
                .name(name)
                .sourceEvent(sourceEvent)
                .targetEvent(targetEvent)
                .rule(condition)
                .build();
    }
}
