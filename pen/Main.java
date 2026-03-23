import enums.PenType;
import factory.PenFactory;
import models.Pen;
import interfaces.Writable;
import interfaces.Refillable;
import interfaces.Openable;

public class Main {

    public static void main(String[] args) {

        // Ball pen with cap
        Pen ballPen = PenFactory.createPen(PenType.BALL, "Blue", true);
        usePen(ballPen);

        System.out.println("---");

        // Gel pen without cap (click-based)
        Pen gelPen = PenFactory.createPen(PenType.GEL, "Black", false);
        usePen(gelPen);

        System.out.println("---");

        // Marker — only writable
        Pen marker = PenFactory.createPen(PenType.MARKER, "Red", true);
        usePen(marker);
    }

    // Safely uses any Pen by checking capabilities via instanceof
    private static void usePen(Pen pen) {
        System.out.println("Using pen: " + pen.getClass().getSimpleName() + " [" + pen.getColor() + "]");

        if (pen instanceof Openable) {
            ((Openable) pen).start();
        }

        if (pen instanceof Writable) {
            ((Writable) pen).write();
        }

        if (pen instanceof Refillable) {
            ((Refillable) pen).refill();
        }

        if (pen instanceof Openable) {
            ((Openable) pen).end();
        }
    }
}
