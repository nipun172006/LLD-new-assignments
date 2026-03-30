package scheduling;

import enums.Direction;
import enums.ElevatorState;
import models.ElevatorCar;
import java.util.List;

public class NearestElevatorScheduler implements ElevatorScheduler {
    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, int requestedFloor, Direction direction) {
        ElevatorCar bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {
            if (elevator.getState() == ElevatorState.MAINTENANCE || elevator.getState() == ElevatorState.EMERGENCY) {
                continue;
            }
            int distance = Math.abs(elevator.getCurrentFloor() - requestedFloor);
            if (distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }
}
