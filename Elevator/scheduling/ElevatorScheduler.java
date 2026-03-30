package scheduling;

import enums.Direction;
import models.ElevatorCar;
import java.util.List;

public interface ElevatorScheduler {
    ElevatorCar selectElevator(List<ElevatorCar> elevators, int requestedFloor, Direction direction);
}
