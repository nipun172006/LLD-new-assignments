package models;

import java.util.List;
import java.util.ArrayList;

public class ParkingLot {
    private List<Floor> floors;
    private List<EntryGate> entryGates;

    public ParkingLot() {
        this.floors = new ArrayList<>();
        this.entryGates = new ArrayList<>();
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void addEntryGate(EntryGate gate) {
        entryGates.add(gate);
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public List<EntryGate> getEntryGates() {
        return entryGates;
    }
}
