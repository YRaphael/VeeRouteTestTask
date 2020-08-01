package bpm.example;

import bpm.api.Process;
import bpm.api.*;
import bpm.fsm.api.*;
import bpm.fsm.core.StateMachineImpl;
import bpm.fsm.core.TransitionBuilder;

import java.util.*;

public class SlotMachineProcess implements Process {

    private final StateMachine<Event> stateMachine;
    private final Executor executor;
    private final Map<Event, List<Map.Entry<Rule, StateMachineEvent>>> rules;

    public SlotMachineProcess(Set<Event> events, Event startEvent, Executor executor) {
        this.executor = executor;
        rules = new HashMap<>();
        stateMachine = new StateMachineImpl<>(events, startEvent);
    }

    @Override
    public void start() throws Exception {
        if (stateMachine.getInitialState().canExecute(executor)) {
            while (true) {
                Event currentState = stateMachine.getCurrentState();
                Map<String, Object> executeResult = currentState.execute(executor);
                List<Map.Entry<Rule, StateMachineEvent>> entries = rules.get(currentState);
                for (Map.Entry<Rule, StateMachineEvent> entry :
                        entries) {
                    Rule key = entry.getKey();
                    if (key.getCondition().test(executeResult)) {
                        stateMachine.fire(entry.getValue());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void cancel() {
    }

    @Override
    public ProcessInfo getInformation() {
        return new ProcessInfo() {
            @Override
            public Object getInfo() {
                return "Current state: " + stateMachine.getCurrentState() + ";";
            }
        };
    }


    @Override
    public void addRule(Rule rule) {

        EventHandler<StateMachineEvent> eventHandler = new EventHandler<>() {
            @Override
            public void handleEvent(StateMachineEvent event) {
                System.out.println(event.getName());
            }
        };
        StateMachineEvent stateMachineEvent = new AbstractStateMachineEvent("Rule name: " + rule.getName() +
                " From: " + rule.getSourceEvent().getName() + " To: " + rule.getTargetEvent().getName() + " ::" + UUID.randomUUID().toString()) {
        };
        putRule(rule, stateMachineEvent);
        Transition trans = new TransitionBuilder()
                .name(rule.getName())
                .sourceState(rule.getSourceEvent())
                .eventType(stateMachineEvent.getClass())
                .eventHandler(eventHandler)
                .targetState(rule.getTargetEvent())
                .build();
        stateMachine.registerTransition(trans);
    }

    private void putRule(Rule rule, StateMachineEvent stateMachineEvent) {
        if (rules.containsKey(rule.getSourceEvent())) {
            List<Map.Entry<Rule, StateMachineEvent>> entries = rules.get(rule.getSourceEvent());
            entries.add(Map.entry(rule, stateMachineEvent));
        } else {
            rules.put(rule.getSourceEvent(), new ArrayList<Map.Entry<Rule, StateMachineEvent>>(Arrays.asList(Map.entry(rule, stateMachineEvent))));
        }
    }

}
