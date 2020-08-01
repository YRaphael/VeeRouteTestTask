package bpm.api;

public interface Process {

    void start() throws Exception;

    void cancel();

    ProcessInfo getInformation();

    void addRule(Rule rule);
}
