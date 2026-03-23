package models;

import interfaces.Writable;

// Marker only supports writing — not refillable, not openable (LSP safe)
public class Marker extends Pen implements Writable {

    public Marker(String color, boolean hasCap) {
        super(color, hasCap);
    }

    @Override
    public void write() {
        System.out.println("Marker [" + color + "] writing with bold strokes...");
    }
}
