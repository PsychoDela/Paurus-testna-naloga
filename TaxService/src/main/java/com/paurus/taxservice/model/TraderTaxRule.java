package com.paurus.taxservice.model;

public class TraderTaxRule {
    public enum Type { GENERAL, WINNINGS }
    public enum Mode { RATE, AMOUNT }

    public Type type;
    public Mode mode;
    public double value;

    public TraderTaxRule(Type type, Mode mode, double value) {
        this.type = type;
        this.mode = mode;
        this.value = value;
    }
}

