import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import backend.CaesarCipher;

class CaesarCipherTest {

    @Test
    void testBasicShift() {
        CaesarCipher cc = new CaesarCipher();
        String result = cc.caesarCipher(new Character[]{'a', 'b', 'c', 'd', 'e'}, 1);
        assertEquals("bcdef", result);
    }

    @Test
    void testWrapAround() {
        CaesarCipher cc = new CaesarCipher();
        String result = cc.caesarCipher(new Character[]{'y', 'z', 'a'}, 1);
        assertEquals("zab", result);
    }

    @Test
    void testUpperCaseLetters() {
        CaesarCipher cc = new CaesarCipher();
        String result = cc.caesarCipher(new Character[]{'A', 'B', 'C', 'D', 'E'}, 1);
        assertEquals("BCDEF", result);
    }

    @Test
    void testSingleCharacter() {
        CaesarCipher cc = new CaesarCipher();
        String result = cc.caesarCipher(new Character[]{'a'}, 3);
        assertEquals("d", result);
    }

    @Test
    void testEmptyInput() {
        CaesarCipher cc = new CaesarCipher();
        String result = cc.caesarCipher(new Character[]{}, 1);
        assertEquals("", result);  // Ensure an empty array results in an empty string
    }
}
