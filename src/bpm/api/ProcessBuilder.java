package bpm.api;

public abstract class ProcessBuilder {

    public abstract ProcessBuilder addRule(Rule rule);

    public abstract Process build();
}
