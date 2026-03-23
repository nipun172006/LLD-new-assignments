package strategies;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import enums.VehicleType;
import enums.SlotType;
import models.*;

public class NearestSlotAllocator implements SlotAllocationStrategy {

    @Override
    public Slot findSlot(Vehicle vehicle, EntryGate gate, ParkingLot parkingLot) {
        SlotType preferredType = mapVehicleToSlot(vehicle.getType());
        int nearestFloor = gate.getNearestFloor();

        // Sort floors by distance from the entry gate's nearest floor
        List<Floor> sortedFloors = new ArrayList<>(parkingLot.getFloors());
        sortedFloors.sort(Comparator.comparingInt(f -> Math.abs(f.getFloorNumber() - nearestFloor)));

        // Try exact match first
        for (Floor floor : sortedFloors) {
            for (Slot slot : floor.getSlots()) {
                if (!slot.isOccupied() && slot.getType() == preferredType) {
                    return slot;
                }
            }
        }

        // Relaxation: BIKE can park in CAR slot if no BIKE slots available
        if (vehicle.getType() == VehicleType.BIKE) {
            for (Floor floor : sortedFloors) {
                for (Slot slot : floor.getSlots()) {
                    if (!slot.isOccupied() && slot.getType() == SlotType.CAR) {
                        return slot;
                    }
                }
            }
        }

        return null; // no slot found
    }

    private SlotType mapVehicleToSlot(VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE: return SlotType.BIKE;
            case CAR:  return SlotType.CAR;
            case BUS:  return SlotType.BUS;
            default:   throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }
}
