package models;

import java.util.List;
import java.util.ArrayList;
import enums.SlotType;

public class Floor {
    private int floorNumber;
    private List<Slot> slots;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
    }

    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    // Count available slots of a given type on this floor
    public long getAvailableCount(SlotType type) {
        return slots.stream()
                .filter(s -> s.getType() == type && !s.isOccupied())
                .count();
    }
}
