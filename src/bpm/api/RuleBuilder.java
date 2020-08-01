package bpm.api;

import java.util.Map;
import java.util.function.Predicate;

public abstract class RuleBuilder {
    public abstract RuleBuilder name(String name);

    public abstract RuleBuilder sourceEvent(Event event);

    public abstract RuleBuilder targetEvent(Event event);

    public abstract RuleBuilder rule(Predicate<Map<String, Object>> condition);

    public abstract Rule build();
}
