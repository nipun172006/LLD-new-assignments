package service;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

import enums.SlotType;
import exceptions.ParkingFullException;
import exceptions.InvalidTicketException;
import models.*;
import strategies.SlotAllocationStrategy;
import strategies.PricingStrategy;

public class ParkingService {
    private SlotAllocationStrategy allocator;
    private PricingStrategy pricingStrategy;
    private ParkingLot parkingLot;
    private Map<String, Ticket> tickets;

    public ParkingService(ParkingLot parkingLot, SlotAllocationStrategy allocator, PricingStrategy pricingStrategy) {
        this.parkingLot = parkingLot;
        this.allocator = allocator;
        this.pricingStrategy = pricingStrategy;
        this.tickets = new HashMap<>();
    }

    // Park a vehicle and return the generated ticket
    public Ticket park(Vehicle vehicle, EntryGate entryGate) {
        Slot slot = allocator.findSlot(vehicle, entryGate, parkingLot);

        if (slot == null) {
            throw new ParkingFullException("No available slot for " + vehicle);
        }

        slot.assignVehicle(vehicle);

        String ticketId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Ticket ticket = new Ticket(ticketId, vehicle, slot, System.currentTimeMillis(), entryGate);
        tickets.put(ticketId, ticket);

        System.out.println("PARKED  | " + vehicle + " → Slot " + slot.getSlotId() + " (Floor " + slot.getFloorNumber() + ") via " + entryGate);
        return ticket;
    }

    // Exit a vehicle and return the cost
    public double exit(String ticketId) {
        Ticket ticket = tickets.remove(ticketId);

        if (ticket == null) {
            throw new InvalidTicketException("No ticket found with ID: " + ticketId);
        }

        long entryTime = ticket.getEntryTime();
        long durationMs = System.currentTimeMillis() - entryTime;
        long durationHours = Math.max(1, durationMs / (1000 * 60 * 60)); // at least 1 hour

        double cost = pricingStrategy.calculatePrice(ticket.getSlot().getType(), durationHours);

        ticket.getSlot().removeVehicle();

        System.out.println("EXITED  | " + ticket.getVehicle() + " from Slot " + ticket.getSlot().getSlotId()
                + " | Duration: " + durationHours + "h | Cost: ₹" + cost);
        return cost;
    }

    // Print available slot counts grouped by floor and slot type
    public void status() {
        System.out.println("\n===== PARKING LOT STATUS =====");
        for (Floor floor : parkingLot.getFloors()) {
            System.out.println("Floor " + floor.getFloorNumber() + ":");
            for (SlotType type : SlotType.values()) {
                long available = floor.getAvailableCount(type);
                long total = floor.getSlots().stream().filter(s -> s.getType() == type).count();
                System.out.println("  " + type + " → " + available + "/" + total + " available");
            }
        }
        System.out.println("==============================\n");
    }
}
