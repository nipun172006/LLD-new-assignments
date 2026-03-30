package scheduling;

import enums.Direction;
import enums.ElevatorState;
import models.ElevatorCar;
import java.util.List;

public class FirstComeFirstServeScheduler implements ElevatorScheduler {
    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, int requestedFloor, Direction direction) {
        for (ElevatorCar elevator : elevators) {
            if (elevator.getState() != ElevatorState.MAINTENANCE && elevator.getState() != ElevatorState.EMERGENCY) {
                return elevator; // Return the first functional elevator found
            }
        }
        return null;
    }
}
