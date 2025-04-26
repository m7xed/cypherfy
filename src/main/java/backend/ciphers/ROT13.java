package backend.ciphers;

public class ROT13 {

    public String doROT13(Character[] data) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < data.length; j++) {
            int ascii = (int) data[j];
            int KEY = 13;

            // Check if character is uppercase
            if (ascii >= 65 && ascii <= 90) {
                ascii = ((ascii - 65 + KEY) % 26) + 65;  // Wrap around within 'A'-'Z'
            }
            // Check if character is lowercase
            else if (ascii >= 97 && ascii <= 122) {
                ascii = ((ascii - 97 + KEY) % 26) + 97;  // Wrap around within 'a'-'z'
            }

            sb.append((char) ascii);
        }
        return sb.toString();
    }
}
