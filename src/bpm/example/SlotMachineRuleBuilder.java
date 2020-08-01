package bpm.example;

import bpm.api.Event;
import bpm.api.Rule;
import bpm.api.RuleBuilder;

import java.util.Map;
import java.util.function.Predicate;

public class SlotMachineRuleBuilder extends RuleBuilder {
    private Rule rule;

    public SlotMachineRuleBuilder() {
        this.rule = new SlotMachineRule();
    }

    @Override
    public RuleBuilder name(String name) {
        rule.setName(name);
        return this;
    }

    @Override
    public RuleBuilder sourceEvent(Event event) {
        rule.setSourceEvent(event);
        return this;
    }

    @Override
    public RuleBuilder targetEvent(Event event) {
        rule.setTargetEvent(event);
        return this;
    }

    @Override
    public RuleBuilder rule(Predicate<Map<String, Object>> condition) {
        rule.setCondition(condition);
        return this;
    }

    @Override
    public Rule build() {
        return rule;
    }
}
