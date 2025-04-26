package backend.ciphers;

public class ATBashCipher {
    public String atbashCipher(Character[] data) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int ascii = (int) data[i];
            if (ascii >= 65 && ascii <= 90) {
                result.append((char) ('Z' - (data[i] - 'A')));
            } else if (ascii >= 97 && ascii <= 122) {
                result.append((char) ('z' - (data[i] - 'a')));
            }
        }
        return result.toString();
    }
}
