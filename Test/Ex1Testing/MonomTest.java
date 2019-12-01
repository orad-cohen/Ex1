package Ex1Testing;

import Ex1.Monom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class MonomTest {
    public static void main(String[] args){

    }
    @BeforeEach
    void setUp() {

    }

    void tearDown() {
    }
    @Test
    void testDerivative() {
        Monom m1 = new Monom("5");
        Monom m2 = new Monom("3x^2");
        Monom m3 = new Monom("6x");
        assertEquals(m2.derivative(), m3);

    }

    void testF() {
    }

    void testIsZero() {
    }

    void testAdd() {
    }

    void testMultiply() {
    }

    void testToString() {
    }

    void testRoundAvoid() {
    }

    void testInitFromString() {
    }

    void testCopy() {
    }
}
