package strategies;

import models.Slot;
import models.Vehicle;
import models.EntryGate;
import models.ParkingLot;

public interface SlotAllocationStrategy {
    Slot findSlot(Vehicle vehicle, EntryGate gate, ParkingLot parkingLot);
}
