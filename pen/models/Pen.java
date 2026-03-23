package models;

// Abstract base class — holds only common properties shared by ALL pens
public abstract class Pen {
    protected String color;
    protected boolean hasCap;

    public Pen(String color, boolean hasCap) {
        this.color = color;
        this.hasCap = hasCap;
    }

    public String getColor() {
        return color;
    }

    public boolean hasCap() {
        return hasCap;
    }
}
