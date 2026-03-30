package components;

public class FloorButtonPanel {
    private Button upButton;
    private Button downButton;

    public FloorButtonPanel() {
        this.upButton = new Button();
        this.downButton = new Button();
    }

    public void pressUp() {
        upButton.press();
    }

    public void pressDown() {
        downButton.press();
    }

    public void resetUp() {
        upButton.reset();
    }

    public void resetDown() {
        downButton.reset();
    }
}
