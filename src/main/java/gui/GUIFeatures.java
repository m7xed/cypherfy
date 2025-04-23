package gui;

public enum GUIFeatures {
    GUI_HEIGHT(600), GUI_WIDTH(800), GUI_MIN_WIDTH(300), GUI_MIN_HEIGHT(300);

    private final int value;

    // Constructor to initialize the value
    GUIFeatures(int value) {
        this.value = value;
    }

    // Getter method to access the value
    public int getValue() {
        return value;
    }
}