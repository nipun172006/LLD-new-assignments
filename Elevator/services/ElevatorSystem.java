package services;

import enums.Direction;
import models.ElevatorCar;
import models.Floor;
import scheduling.ElevatorScheduler;
import scheduling.NearestElevatorScheduler;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private List<ElevatorCar> elevators;
    private List<Floor> floors;
    private ElevatorScheduler scheduler;
    private int totalFloors;

    public ElevatorSystem(int numElevators, int totalFloors) {
        this.totalFloors = totalFloors;
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.scheduler = new NearestElevatorScheduler(); // Default

        for (int i = 0; i < numElevators; i++) {
            this.elevators.add(new ElevatorCar(i + 1, totalFloors));
        }

        for (int i = 0; i <= totalFloors; i++) {
            this.floors.add(new Floor(i));
        }
    }

    public void setScheduler(ElevatorScheduler scheduler) {
        this.scheduler = scheduler;
        System.out.println("Scheduler strategy changed to: " + scheduler.getClass().getSimpleName());
    }

    public void requestElevator(int floor, Direction direction) {
        if (floor < 0 || floor > totalFloors) {
            System.out.println("Invalid floor.");
            return;
        }

        System.out.println("🛎️ External Request: Floor " + floor + " going " + direction);
        if (direction == Direction.UP) floors.get(floor).getPanel().pressUp();
        else floors.get(floor).getPanel().pressDown();

        ElevatorCar elevator = scheduler.selectElevator(elevators, floor, direction);
        if (elevator != null) {
            System.out.println("✅ Dispatched Elevator " + elevator.getId() + " to Floor " + floor);
            elevator.addRequest(floor);
        } else {
            System.out.println("❌ All elevators are busy/unavailable.");
        }
    }

    public void selectFloor(int elevatorId, int floor) {
        if (floor < 0 || floor > totalFloors) return;
        System.out.println("🔢 Internal Request: Passenger in Elevator " + elevatorId + " selected Floor " + floor);
        for (ElevatorCar elevator : elevators) {
            if (elevator.getId() == elevatorId) {
                elevator.addRequest(floor);
                return;
            }
        }
    }

    public void step() {
        for (ElevatorCar elevator : elevators) {
            elevator.move();
        }
    }

    public void triggerEmergency(int elevatorId) {
        for (ElevatorCar elevator : elevators) {
            if (elevator.getId() == elevatorId) {
                elevator.triggerEmergency();
                return;
            }
        }
    }

    public void setMaintenance(int elevatorId, boolean isMaintenance) {
        for (ElevatorCar elevator : elevators) {
            if (elevator.getId() == elevatorId) {
                elevator.setMaintenance(isMaintenance);
                return;
            }
        }
    }

    public ElevatorCar getElevator(int id) {
        return elevators.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }
}
