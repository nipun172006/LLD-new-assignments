package components;

import java.util.HashMap;
import java.util.Map;

public class ElevatorPanel {
    private Map<Integer, Button> floorButtons;
    private Button openDoorButton;
    private Button closeDoorButton;
    private Button emergencyButton;

    public ElevatorPanel(int totalFloors) {
        this.floorButtons = new HashMap<>();
        for (int i = 0; i <= totalFloors; i++) {
            this.floorButtons.put(i, new Button());
        }
        this.openDoorButton = new Button();
        this.closeDoorButton = new Button();
        this.emergencyButton = new Button();
    }

    public void pressFloor(int floor) {
        if (floorButtons.containsKey(floor)) {
            floorButtons.get(floor).press();
        }
    }

    public void pressOpen() {
        openDoorButton.press();
    }

    public void pressClose() {
        closeDoorButton.press();
    }

    public void pressEmergency() {
        emergencyButton.press();
    }
}
