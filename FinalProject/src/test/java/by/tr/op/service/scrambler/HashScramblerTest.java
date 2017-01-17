package by.tr.op.service.scrambler;

import org.junit.Test;
import static org.junit.Assert.*;

public class HashScramblerTest {
    
    public HashScramblerTest() {
    }
    
    @Test
    public void testEncrypt() {
        char[] input = "123".toCharArray();
        HashScrambler instance = HashScrambler.getInstance();
        int expResult = 422111;
        int result = instance.encrypt(input);
        assertEquals(expResult, result);
    }
    
}
