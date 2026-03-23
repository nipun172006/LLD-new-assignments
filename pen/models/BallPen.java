package models;

import interfaces.Writable;
import interfaces.Refillable;
import interfaces.Openable;

public class BallPen extends Pen implements Writable, Refillable, Openable {

    public BallPen(String color, boolean hasCap) {
        super(color, hasCap);
    }

    @Override
    public void write() {
        System.out.println("BallPen [" + color + "] writing smoothly...");
    }

    @Override
    public void refill() {
        System.out.println("BallPen [" + color + "] refilled with new ink cartridge.");
    }

    @Override
    public void start() {
        if (hasCap) {
            System.out.println("BallPen [" + color + "] cap removed.");
        } else {
            System.out.println("BallPen [" + color + "] clicked open.");
        }
    }

    @Override
    public void end() {
        if (hasCap) {
            System.out.println("BallPen [" + color + "] cap closed.");
        } else {
            System.out.println("BallPen [" + color + "] clicked shut.");
        }
    }
}
