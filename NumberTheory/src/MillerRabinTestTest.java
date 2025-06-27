import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class MillerRabinTestTest {

	static final int iter = 10;

    @Test
    public void testComposites() {
        long[] values = {1, 25, 2932021007403L, 4501680375506332L, 6910906992394051L,
                4887521073398877L, 5577943644815725L, 6085993686552764L};

        for (long v : values) {
            BigInteger val = BigInteger.valueOf(v);
            assertFalse(MillerRabinTest.millerRabin(val, iter));
        }
    }
    @Test
    public void testPrimes() {
        long[] values = {2, 17, 137, 317, 405857, 2932031007403L, 6333369275038567L};
        for (long v : values) {
            BigInteger val = BigInteger.valueOf(v);
            assertTrue(MillerRabinTest.millerRabin(val, iter));
        }
    }

    // Test all primes
    @Test
    public void testBigPrimes() {
        BigInteger b1 = new BigInteger("423726770669791241889982933129");
        BigInteger b2 = new BigInteger("728801495170617624430641064729");
        BigInteger b3 = new BigInteger("715069831641887124233793734953");
        BigInteger b4 = new BigInteger("214668004859466264786404914307");
        BigInteger b5 = new BigInteger("107280976690907382021651905569");
        BigInteger b6 = new BigInteger("194139053422804244228680212551");
        BigInteger b7 = new BigInteger("225220037755960690862092087151");
        assertTrue(MillerRabinTest.millerRabin(b1, iter));
        assertTrue(MillerRabinTest.millerRabin(b2, iter));
        assertTrue(MillerRabinTest.millerRabin(b3, iter));
        assertTrue(MillerRabinTest.millerRabin(b4, iter));
        assertTrue(MillerRabinTest.millerRabin(b5, iter));
        assertTrue(MillerRabinTest.millerRabin(b6, iter));
        assertTrue(MillerRabinTest.millerRabin(b7, iter));
    }
    // Tests composite numbers which are products of two big primes
    @Test
    public void testBigComposites() {
        BigInteger p1 = new BigInteger("995224294347733"); // all 4 primes
        BigInteger p2 = new BigInteger("990601545052177");
        BigInteger p3 = new BigInteger("924286031819653");
        BigInteger p4 = new BigInteger("408464000499539");


        assertFalse(MillerRabinTest.millerRabin(p1.multiply(p1), iter));
        assertFalse(MillerRabinTest.millerRabin(p1.multiply(p2), iter));
        assertFalse(MillerRabinTest.millerRabin(p1.multiply(p3), iter));
        assertFalse(MillerRabinTest.millerRabin(p1.multiply(p4), iter));

        assertFalse(MillerRabinTest.millerRabin(p2.multiply(p2), iter));
        assertFalse(MillerRabinTest.millerRabin(p2.multiply(p3), iter));
        assertFalse(MillerRabinTest.millerRabin(p2.multiply(p4), iter));

        assertFalse(MillerRabinTest.millerRabin(p3.multiply(p3), iter));
        assertFalse(MillerRabinTest.millerRabin(p3.multiply(p4), iter));

        assertFalse(MillerRabinTest.millerRabin(p4.multiply(p4), iter));
    }

}
