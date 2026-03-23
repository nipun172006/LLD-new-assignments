package strategies;

import enums.SlotType;

public interface PricingStrategy {
    double calculatePrice(SlotType slotType, long durationInHours);
}
