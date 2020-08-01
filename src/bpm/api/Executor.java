package bpm.api;


import java.util.Set;

public interface Executor {
    void setCurrentEvent(Event event);

    Event getCurrentEvent();

    void setRoles(Set<Role> roles);

    Set<Role> getRoles();

}
