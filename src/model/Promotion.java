package model;

public class Promotion {

    public enum Type {
        FIXED,
        PERCENTAGE
    }

    private final Type type;
    private final double value;

    public Promotion(Type type, double value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public double getValue() {
        return value;
    }
}