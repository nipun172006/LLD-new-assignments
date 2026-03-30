import enums.Direction;
import models.ElevatorCar;
import scheduling.FirstComeFirstServeScheduler;
import services.ElevatorSystem;

public class Main {
    public static void main(String[] args) {
        System.out.println("🛗 Initializing Elevator System...");
        
        ElevatorSystem system = new ElevatorSystem(3, 10); // 3 elevators, 10 floors
        
        System.out.println("\n--- Flow 1: External Floor Request ---");
        system.requestElevator(5, Direction.UP);
        system.step(); // Elevator moves
        
        System.out.println("\n--- Flow 2: Internal Elevator Request ---");
        // Passenger gets in and presses 9
        system.selectFloor(1, 9);
        system.step();
        
        System.out.println("\n--- Flow 3: Weight Overload Limit (700kg) ---");
        ElevatorCar car1 = system.getElevator(1);
        car1.addWeight(800.0); // Should trigger overload
        
        System.out.println("\n--- Flow 4: Maintenance Mode ---");
        system.setMaintenance(2, true);
        system.requestElevator(3, Direction.DOWN); // Should assign Elevator 1 or 3, not 2
        
        System.out.println("\n--- Flow 5: Emergency Component Handling ---");
        system.triggerEmergency(3);
        system.requestElevator(4, Direction.DOWN); // Elevator 3 is emergency, 2 is maintenance, 1 handles it
        
        system.step();
        
        System.out.println("\n--- Extending: Changing Scheduler Strategy dynamically ---");
        system.setScheduler(new FirstComeFirstServeScheduler());
        system.requestElevator(8, Direction.DOWN);
    }
}
