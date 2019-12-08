package Ex1Testing;

import Ex1.Monom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonomTest {

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
        Monom m4 = new Monom("0");
        assertEquals(m2.derivative().toString(), m3.toString());
        assertEquals("0",m1.derivative().toString());
        assertEquals(m4.derivative().toString(),"0");

    }
    @Test
    void testF() {
        Monom m1 = new Monom("3x^5");
        Monom m2 = new Monom("6");
        Monom m3 = new Monom("4x");
        assertEquals(m1.f(2.4),238.87872,0.00001);
        assertEquals(m2.f(10),6);
        assertEquals(m3.f(7.2),28.8);
    }
    @Test
    void testIsZero() {
        Monom m1 = new Monom("0x^3");
        Monom m2 = new Monom("0");
        assertEquals(m1.toString(),Monom.ZERO.toString());
        assertEquals(m2.toString(),m1.toString());
    }
    @Test
    void testAdd() {
        Monom m1 = new Monom("3");
        Monom m2 = new Monom("8");
        Monom m3 = new Monom("2x^3");
        Monom m4 = new Monom("4x^3");
        Monom m5 = new Monom("2x^1");
        Monom m6 = new Monom("2x");
        m1.add(m2);
        m3.add(m4);
        m5.add(m6);
        assertEquals(m1.toString(),new Monom("11").toString());
        assertEquals(m3.toString(),new Monom("6x^3").toString());
        assertEquals(m5.toString(),new Monom("4x").toString());
        assertEquals(m5.toString(),new Monom("4x^1").toString());
    }
    @Test
    void testMultiply() {
        Monom m1 = new Monom("3");
        Monom m2 = new Monom("8");
        Monom m3 = new Monom("2x^3");
        Monom m4 = new Monom("4x^3");
        m1.multiply(m3);
        m3.multiply(m4);
        m2.multiply(m4);
        assertEquals(m1.toString(),"6.0x^3");
        assertEquals(m3.toString(),"8.0x^6");
        assertEquals(m2.toString(),"32.0x^3");
        m1.multiply(m3);
        assertEquals(m1.toString(),"48.0x^9");

    }

    void testToString() {
        Monom m3 = new Monom("2x^3");
        Monom m4 = new Monom("4x^3");
        assertEquals("2.0x^3",m3.toString());
        assertEquals("4.0x^3",m4.toString());
    }

    void testRoundAvoid() {
    }

    void testInitFromString() {
    }

    void testCopy() {
    }
}
