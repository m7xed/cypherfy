package backend.ciphers;

public class CaesarCipher {
    private static final int KEY = 1;

    public Character[] caesarCipher(Character[] data) {
        for (int j = 0; j < data.length; j++) {
            int ascii = (int) data[j];

            if (ascii >= 65 && ascii <= 90) {
                ascii = (ascii + KEY);
            } else if(ascii >= 97 && ascii <= 122) {
                ascii = (ascii + KEY);
            }

            if (ascii >= 91 && ascii <= 96) {
                ascii -= 26;
            } else if(ascii > 122) {
                ascii -= 26;
            }

            data[j] = (char) ascii;
            }
        Character[] newData = new Character[data.length];
        System.arraycopy(data, 0, newData, 0, data.length);

        return newData;
    }
}
