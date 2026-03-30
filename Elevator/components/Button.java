package components;

public class Button {
    private boolean isPressed;

    public void press() {
        this.isPressed = true;
    }

    public void reset() {
        this.isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }
}
