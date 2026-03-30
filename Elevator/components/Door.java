package components;

public class Door {
    private boolean isOpen;

    public void open() {
        this.isOpen = true;
        System.out.println("Door is OPENING.");
    }

    public void close() {
        this.isOpen = false;
        System.out.println("Door is CLOSING.");
    }

    public boolean isOpen() {
        return isOpen;
    }
}
