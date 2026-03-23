package models;

import interfaces.Writable;
import interfaces.Refillable;
import interfaces.Openable;

public class GelPen extends Pen implements Writable, Refillable, Openable {

    public GelPen(String color, boolean hasCap) {
        super(color, hasCap);
    }

    @Override
    public void write() {
        System.out.println("GelPen [" + color + "] writing with gel ink...");
    }

    @Override
    public void refill() {
        System.out.println("GelPen [" + color + "] refilled with gel ink cartridge.");
    }

    @Override
    public void start() {
        if (hasCap) {
            System.out.println("GelPen [" + color + "] cap removed.");
        } else {
            System.out.println("GelPen [" + color + "] clicked open.");
        }
    }

    @Override
    public void end() {
        if (hasCap) {
            System.out.println("GelPen [" + color + "] cap closed.");
        } else {
            System.out.println("GelPen [" + color + "] clicked shut.");
        }
    }
}
