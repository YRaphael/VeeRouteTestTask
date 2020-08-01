package bpm.example;

import bpm.api.Event;
import bpm.api.Executor;
import bpm.api.Role;

import java.util.*;

class CoinEvent extends Event {

    private final Set<Role> roles;

    public CoinEvent(Set<Role> roles) {
        super("CoinEvent");
        this.roles = new HashSet<>();
        this.roles.addAll(roles);
    }

    public CoinEvent(String name, Set<Role> roles) {
        super(name);
        this.roles = new HashSet<>();
        this.roles.addAll(roles);
    }

    @Override
    public boolean canExecute(Executor executor) {
        for (Role role :
                roles) {
            if (roles.contains(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> execute(Executor executor) {
        Scanner scn = new Scanner(System.in);
        String next = scn.next();
        Map<String, Object> map = new HashMap<>();
        if ("c".equals(next)) {
            map.put("coinInserted", true);
            return map;
        }
        if ("p".equals(next)) {
            map.put("buttonPressed", true);
            return map;
        }
        map.put("anything else", true);
        return map;
    }

    @Override
    public void setProperties(Map<String, Object> properties) {
        //TODO: SOME LOGIC
    }

    @Override
    public Map<String, Object> getProperties() {
        //TODO: SOME LOGIC
        return null;
    }

}