package bpm.example;

import bpm.api.Event;
import bpm.api.Executor;
import bpm.api.Role;

import java.util.HashSet;
import java.util.Set;

public class SlotMachineExecutor implements Executor {
    private Event currentEvent;
    private Set<Role> roles;

    public SlotMachineExecutor() {
        this.currentEvent = null;
        this.roles = new HashSet<Role>();
    }

    public SlotMachineExecutor(Set<Role> roles) {
        this.currentEvent = null;
        this.roles = roles;
    }

    @Override
    public void setCurrentEvent(Event event) {
        this.currentEvent = event;
    }

    @Override
    public Event getCurrentEvent() {
        return this.currentEvent;
    }

    @Override
    public void setRoles(Set<Role> roles) {
        this.roles.addAll(roles);
    }

    @Override
    public Set<Role> getRoles() {
        return this.roles;
    }

}
