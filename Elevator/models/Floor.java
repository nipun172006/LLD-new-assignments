package models;

import components.FloorButtonPanel;

public class Floor {
    private int floorNumber;
    private FloorButtonPanel panel;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.panel = new FloorButtonPanel();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public FloorButtonPanel getPanel() {
        return panel;
    }
}
