package models;

public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private Slot slot;
    private long entryTime;
    private EntryGate entryGate;

    public Ticket(String ticketId, Vehicle vehicle, Slot slot, long entryTime, EntryGate entryGate) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = entryTime;
        this.entryGate = entryGate;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Slot getSlot() {
        return slot;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public EntryGate getEntryGate() {
        return entryGate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + ticketId + '\'' +
                ", vehicle=" + vehicle +
                ", slot=" + slot.getSlotId() +
                ", gate=" + entryGate +
                '}';
    }
}
