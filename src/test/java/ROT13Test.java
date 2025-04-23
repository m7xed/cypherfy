import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import backend.ROT13;

public class ROT13Test {

    @Test
    public void testROT13Uppercase() {
        ROT13 rot13 = new ROT13();

        // Test with uppercase letters
        Character[] input1 = {'A', 'B', 'C', 'X', 'Y', 'Z'};
        String result1 = rot13.doROT13(input1);
        assertEquals("NOPQRSTUVWXYZ", result1);

        // Test with single uppercase letter
        Character[] input2 = {'A'};
        String result2 = rot13.doROT13(input2);
        assertEquals("N", result2);
    }

    @Test
    public void testROT13Lowercase() {
        ROT13 rot13 = new ROT13();

        // Test with lowercase letters
        Character[] input1 = {'a', 'b', 'c', 'x', 'y', 'z'};
        String result1 = rot13.doROT13(input1);
        assertEquals("nopqrstuvwxyz", result1);

        // Test with single lowercase letter
        Character[] input2 = {'a'};
        String result2 = rot13.doROT13(input2);
        assertEquals("n", result2);
    }

    @Test
    public void testROT13MixedCase() {
        ROT13 rot13 = new ROT13();

        // Test with mixed case letters
        Character[] input1 = {'A', 'b', 'C', 'x', 'Y', 'z'};
        String result1 = rot13.doROT13(input1);
        assertEquals("NobkjZm", result1);
    }

    @Test
    public void testROT13Empty() {
        ROT13 rot13 = new ROT13();

        // Test with empty input
        Character[] input = {};
        String result = rot13.doROT13(input);
        assertEquals("", result);
    }

    @Test
    public void testROT13SameLetterAfterDouble() {
        ROT13 rot13 = new ROT13();

        // Test that applying ROT13 twice returns the original letter
        Character[] input1 = {'A', 'b', 'C', 'x', 'Y', 'z'};
        String result1 = rot13.doROT13(input1);
        String result2 = rot13.doROT13(result1.toCharArray());

        // Convert result2 back to a string
        assertEquals("AbCxyz", result2);
    }
}
