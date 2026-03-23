import enums.VehicleType;
import enums.SlotType;
import exceptions.ParkingFullException;
import models.*;
import strategies.*;
import service.ParkingService;

public class Main {

    public static void main(String[] args) {

        // ─── Build the parking lot ───
        ParkingLot lot = new ParkingLot();

        // Floor 1: 2 bike slots, 2 car slots, 1 bus slot
        Floor floor1 = new Floor(1);
        floor1.addSlot(new Slot("F1-B1", SlotType.BIKE, 1));
        floor1.addSlot(new Slot("F1-B2", SlotType.BIKE, 1));
        floor1.addSlot(new Slot("F1-C1", SlotType.CAR, 1));
        floor1.addSlot(new Slot("F1-C2", SlotType.CAR, 1));
        floor1.addSlot(new Slot("F1-BU1", SlotType.BUS, 1));

        // Floor 2: 1 bike slot, 2 car slots, 1 bus slot
        Floor floor2 = new Floor(2);
        floor2.addSlot(new Slot("F2-B1", SlotType.BIKE, 2));
        floor2.addSlot(new Slot("F2-C1", SlotType.CAR, 2));
        floor2.addSlot(new Slot("F2-C2", SlotType.CAR, 2));
        floor2.addSlot(new Slot("F2-BU1", SlotType.BUS, 2));

        lot.addFloor(floor1);
        lot.addFloor(floor2);

        // Entry gates
        EntryGate gateA = new EntryGate("A", 1); // closest to floor 1
        EntryGate gateB = new EntryGate("B", 2); // closest to floor 2
        lot.addEntryGate(gateA);
        lot.addEntryGate(gateB);

        // ─── Initialize service with strategies ───
        SlotAllocationStrategy allocator = new NearestSlotAllocator();
        PricingStrategy pricing = new HourlyPricingStrategy();
        ParkingService service = new ParkingService(lot, allocator, pricing);

        // ─── Demo: Park vehicles ───
        System.out.println("─── PARKING VEHICLES ───\n");

        Ticket t1 = service.park(new Vehicle("KA-01-1234", VehicleType.CAR), gateA);
        Ticket t2 = service.park(new Vehicle("KA-02-5678", VehicleType.BIKE), gateA);
        Ticket t3 = service.park(new Vehicle("KA-03-9999", VehicleType.BUS), gateB);
        Ticket t4 = service.park(new Vehicle("KA-04-1111", VehicleType.BIKE), gateB);

        // ─── Check status ───
        service.status();

        // ─── Demo: Exit a vehicle ───
        System.out.println("─── EXITING VEHICLES ───\n");
        service.exit(t1.getTicketId());
        service.exit(t2.getTicketId());

        // ─── Check status after exits ───
        service.status();

        // ─── Demo: Bike relaxation (fill all bike slots, bike goes to car slot) ───
        System.out.println("─── BIKE RELAXATION DEMO ───\n");

        // Fill remaining bike slots
        service.park(new Vehicle("KA-05-AAAA", VehicleType.BIKE), gateA);
        service.park(new Vehicle("KA-06-BBBB", VehicleType.BIKE), gateA);
        // Next bike should go to a CAR slot (relaxation rule)
        Ticket bikeInCar = service.park(new Vehicle("KA-07-CCCC", VehicleType.BIKE), gateA);
        System.out.println("  ↳ Bike " + bikeInCar.getVehicle().getVehicleId()
                + " parked in " + bikeInCar.getSlot().getType() + " slot " + bikeInCar.getSlot().getSlotId());

        service.status();

        // ─── Demo: Parking full exception ───
        System.out.println("─── PARKING FULL DEMO ───\n");
        try {
            // Fill all bus slots
            service.park(new Vehicle("KA-10-BUS2", VehicleType.BUS), gateA);
            // This should throw ParkingFullException
            service.park(new Vehicle("KA-11-BUS3", VehicleType.BUS), gateA);
        } catch (ParkingFullException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }
}
