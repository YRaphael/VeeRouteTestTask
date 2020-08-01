package bpm.example;

import bpm.api.Process;
import bpm.api.ProcessBuilder;
import bpm.api.*;

import java.util.Set;

public class SlotMachineProcessBuilder extends ProcessBuilder {
    private final Process slotMachineProcess;

    public SlotMachineProcessBuilder(Set<Event> events, Event startEvent, Executor executor) {
        slotMachineProcess = new SlotMachineProcess(events, startEvent, executor);
    }

    public ProcessBuilder addRule(Rule rule) {
        slotMachineProcess.addRule(rule);
        return this;
    }


    public Process build() {
        return slotMachineProcess;
    }

}
