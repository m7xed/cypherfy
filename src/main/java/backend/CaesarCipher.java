package backend;

public class CaesarCipher {
    public String caesarCipher(Character[] data, int key) {
        for (int j = 0; j < data.length; j++) {
            int ascii = (int) data[j];

            if (ascii >= 65 && ascii <= 90) {
                ascii = (ascii + key);
            } else if(ascii >= 97 && ascii <= 122) {
                ascii = (ascii + key);
            }

            if (ascii >= 91 && ascii <= 96) {
                ascii -= 26;
            } else if(ascii > 122) {
                ascii -= 26;
            }

            data[j] = (char) ascii;
            }
        StringBuilder sb = new StringBuilder();
        for (Character datum : data) {
            sb.append(datum);
        }
        return sb.toString();
    }
}
