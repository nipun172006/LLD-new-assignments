package models;

import enums.SeatType;

public class Seat {
    private String seatId;
    private int rowNo;
    private int seatNumber;
    private SeatType type;

    public Seat(String seatId, int rowNo, int seatNumber, SeatType type) {
        this.seatId = seatId;
        this.rowNo = rowNo;
        this.seatNumber = seatNumber;
        this.type = type;
    }

    public String getSeatId() { return seatId; }
    public int getRowNo() { return rowNo; }
    public int getSeatNumber() { return seatNumber; }
    public SeatType getType() { return type; }
}
