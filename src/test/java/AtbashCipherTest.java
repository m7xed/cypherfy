import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import backend.AtbashCipher;

public class AtbashCipherTest {

    @Test
    public void testAtbashCipher() {
        AtbashCipher atbashCipher = new AtbashCipher();

        // Test with uppercase letters
        Character[] input1 = {'A', 'B', 'C', 'D', 'E', 'Z'};
        String result1 = atbashCipher.atbashCipher(input1);
        assertEquals("ZYXWVA", result1); // Corrected expected output

        // Test with lowercase letters
        Character[] input2 = {'a', 'b', 'c', 'x', 'y', 'z'};
        String result2 = atbashCipher.atbashCipher(input2);
        assertEquals("zyxcba", result2); // Corrected expected output

        // Test with mixed case
        Character[] input3 = {'A', 'b', 'C', 'x', 'Y', 'z'};
        String result3 = atbashCipher.atbashCipher(input3);
        assertEquals("ZyXcBa", result3); // Corrected expected output
    }
}
