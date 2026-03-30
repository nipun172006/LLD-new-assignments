package models;

import components.Door;
import components.ElevatorPanel;
import enums.Direction;
import enums.ElevatorState;

import java.util.Collections;
import java.util.TreeSet;

public class ElevatorCar {
    private int id;
    private int currentFloor;
    private ElevatorState state;
    private Direction direction;
    private TreeSet<Integer> upRequests;
    private TreeSet<Integer> downRequests;
    private Door door;
    private ElevatorPanel panel;

    private final double MAX_WEIGHT = 700.0;
    private double currentWeight;

    public ElevatorCar(int id, int totalFloors) {
        this.id = id;
        this.currentFloor = 0; // Ground floor
        this.state = ElevatorState.IDLE;
        this.direction = Direction.NONE;
        this.door = new Door();
        this.panel = new ElevatorPanel(totalFloors);
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>(Collections.reverseOrder());
        this.currentWeight = 0.0;
    }

    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public ElevatorState getState() { return state; }
    public Direction getDirection() { return direction; }

    public void addWeight(double weight) {
        if (currentWeight + weight > MAX_WEIGHT) {
            System.out.println("Elevator " + id + ": OVERLOAD ALARM! Max weight is 700kg. Cannot add " + weight + "kg.");
            return;
        }
        currentWeight += weight;
        System.out.println("Elevator " + id + ": Weight added. Current load: " + currentWeight + "kg.");
    }

    public void removeWeight(double weight) {
        currentWeight = Math.max(0, currentWeight - weight);
    }

    public void openDoor() {
        panel.pressOpen();
        door.open();
    }

    public void closeDoor() {
        panel.pressClose();
        door.close();
    }

    public void addRequest(int floor) {
        if (state == ElevatorState.MAINTENANCE || state == ElevatorState.EMERGENCY) {
            return; // Reject requests if unavailable
        }

        panel.pressFloor(floor);

        if (floor > currentFloor) {
            upRequests.add(floor);
        } else if (floor < currentFloor) {
            downRequests.add(floor);
        } else {
            // Already at the requested floor
            openDoor();
            closeDoor();
            return;
        }

        if (state == ElevatorState.IDLE) {
            if (floor > currentFloor) {
                direction = Direction.UP;
                state = ElevatorState.MOVING_UP;
            } else if (floor < currentFloor) {
                direction = Direction.DOWN;
                state = ElevatorState.MOVING_DOWN;
            }
        }
    }

    public void triggerEmergency() {
        panel.pressEmergency();
        state = ElevatorState.EMERGENCY;
        direction = Direction.NONE;
        System.out.println("🚨 Elevator " + id + " EMERGENCY STOPPED ALARM TRIGGERED! 🚨");
    }

    public void setMaintenance(boolean isMaintenance) {
        if (isMaintenance) {
            state = ElevatorState.MAINTENANCE;
            direction = Direction.NONE;
            upRequests.clear();
            downRequests.clear();
            System.out.println("Elevator " + id + " is under MAINTENANCE.");
        } else {
            state = ElevatorState.IDLE;
            System.out.println("Elevator " + id + " is back in service.");
        }
    }

    public void move() {
        if (state == ElevatorState.MAINTENANCE || state == ElevatorState.EMERGENCY) return;

        if (direction == Direction.UP) {
            if (!upRequests.isEmpty()) {
                currentFloor = upRequests.pollFirst();
                System.out.println("Elevator " + id + " arrived at UP destination: Floor " + currentFloor);
                openDoor();
                closeDoor();

                if (upRequests.isEmpty() && downRequests.isEmpty()) {
                    state = ElevatorState.IDLE;
                    direction = Direction.NONE;
                } else if (upRequests.isEmpty()) {
                    direction = Direction.DOWN;
                    state = ElevatorState.MOVING_DOWN;
                }
            }
        } else if (direction == Direction.DOWN) {
            if (!downRequests.isEmpty()) {
                currentFloor = downRequests.pollFirst();
                System.out.println("Elevator " + id + " arrived at DOWN destination: Floor " + currentFloor);
                openDoor();
                closeDoor();

                if (downRequests.isEmpty() && upRequests.isEmpty()) {
                    state = ElevatorState.IDLE;
                    direction = Direction.NONE;
                } else if (downRequests.isEmpty()) {
                    direction = Direction.UP;
                    state = ElevatorState.MOVING_UP;
                }
            }
        }
    }
}
