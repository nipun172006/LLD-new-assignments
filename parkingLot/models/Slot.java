package models;

import enums.SlotType;

public class Slot {
    private String slotId;
    private SlotType type;
    private boolean isOccupied;
    private Vehicle vehicle;
    private int floorNumber;

    public Slot(String slotId, SlotType type, int floorNumber) {
        this.slotId = slotId;
        this.type = type;
        this.floorNumber = floorNumber;
        this.isOccupied = false;
        this.vehicle = null;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isOccupied = false;
    }

    public String getSlotId() {
        return slotId;
    }

    public SlotType getType() {
        return type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public String toString() {
        return "Slot{" + slotId + ", " + type + ", floor=" + floorNumber + ", occupied=" + isOccupied + "}";
    }
}
