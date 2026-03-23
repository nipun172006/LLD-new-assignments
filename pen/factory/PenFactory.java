package factory;

import enums.PenType;
import models.Pen;
import models.BallPen;
import models.GelPen;
import models.Marker;

public class PenFactory {

    public static Pen createPen(PenType type, String color, boolean hasCap) {
        switch (type) {
            case BALL:
                return new BallPen(color, hasCap);
            case GEL:
                return new GelPen(color, hasCap);
            case MARKER:
                return new Marker(color, hasCap);
            default:
                throw new IllegalArgumentException("Unknown pen type: " + type);
        }
    }
}
