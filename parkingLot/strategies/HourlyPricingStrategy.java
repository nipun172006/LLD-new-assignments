package strategies;

import enums.SlotType;

public class HourlyPricingStrategy implements PricingStrategy {

    // Rate per hour for each slot type
    private static final double BIKE_RATE = 10.0;
    private static final double CAR_RATE = 20.0;
    private static final double BUS_RATE = 50.0;

    @Override
    public double calculatePrice(SlotType slotType, long durationInHours) {
        // Minimum 1 hour charge
        long billableHours = Math.max(1, durationInHours);

        switch (slotType) {
            case BIKE: return billableHours * BIKE_RATE;
            case CAR:  return billableHours * CAR_RATE;
            case BUS:  return billableHours * BUS_RATE;
            default:   throw new IllegalArgumentException("Unknown slot type: " + slotType);
        }
    }
}
