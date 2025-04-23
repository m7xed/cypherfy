package gui.visualizations;

public enum CipherNames {
    CAESAR("Caesar Cipher"), VIGENERE("Vigenere Cipher"), ATBASH("ATBash Cipher");

    private final String value;

    // Constructor to initialize the value
    CipherNames(String value) {
        this.value = value;
    }

    // Getter method to access the value
    public String getValue() {
        return value;
    }
}
