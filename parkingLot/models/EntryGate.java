package models;

public class EntryGate {
    private String gateId;
    private int nearestFloor; // which floor this gate is closest to

    public EntryGate(String gateId, int nearestFloor) {
        this.gateId = gateId;
        this.nearestFloor = nearestFloor;
    }

    public String getGateId() {
        return gateId;
    }

    public int getNearestFloor() {
        return nearestFloor;
    }

    @Override
    public String toString() {
        return "Gate-" + gateId;
    }
}
