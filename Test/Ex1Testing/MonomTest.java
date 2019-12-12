package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonomTest {
    private Monom m1,m2,m3,m4,m5,m6;
    private Polynom p1;
    private ComplexFunction c1;

    @BeforeEach
    void  setup() throws Exception{
        m1 = new Monom();
        m2 = new Monom();
        m3 = new Monom();
        m4 = new Monom();
        m5 = new Monom();
        m6 = new Monom();
        p1 = new Polynom();
        c1 = new ComplexFunction();
    }

    void tearDown() {
    }
    @Test
    void testDerivative() {
        m1 = new Monom("5");
        m2 = new Monom("3x^2");
        m3 = new Monom("6x");
        m4 = new Monom("0");
        assertTrue(m2.derivative().equals(m3));
        assertTrue(m1.derivative().equals(m4));


    }
    @Test
    void testF() {
        m1 = new Monom("3x^5");
        m2 = new Monom("6");
        m3 = new Monom("4x");
        assertEquals(m1.f(2.4),238.87872,0.00001);
        assertEquals(m2.f(10),6);
        assertEquals(m3.f(7.2),28.8);
    }
    @Test
    void testIsZero() {
        m1 = new Monom("0x^3");
        m2 = new Monom("0");
        assertTrue(m1.equals(m2));
    }
    @Test
    void testAdd() {
        m1 = new Monom("3");
        m2 = new Monom("8");
        m3 = new Monom("11");
        m4 = new Monom("-4x^2");
        m5 = new Monom("2x^2");
        m6 = new Monom("-2x^2");
        m1.add(m2);
        m4.add(m5);
        assertTrue(m1.equals(m3));
        assertTrue(m4.equals(m6));


    }
    @Test
    void testMultiply() {
        m1 = new Monom("0");
        m2 = new Monom("4x^3");
        m3 = new Monom("2x^3");
        m4 = new Monom("3x^2");
        m5 = new Monom("6x^5");
        m2.multiply(m1);
        m3.multiply(m4);
        assertTrue(m2.equals(m1));
        assertTrue(m3.equals(m5));

    }
    @Test
    void testToString() {
        m3 = new Monom("2x^3");
        m4 = new Monom("4x^3");
        assertEquals("2.0x^3",m3.toString());
        assertEquals("4.0x^3",m4.toString());
    }

    void testRoundAvoid() {
    }
    @Test
    void testInitFromString() {
        String s = "2x^2";
        Monom m = (Monom) m1.initFromString(s);
        m2 = new Monom(s);
        assertTrue(m.equals(m2));

    }
    @Test
    void testCopy() {
        m1 = new Monom("2x^3");
        m2 = (Monom) m1.copy();
        assertTrue(m1.equals(m2));
        m3 = new Monom("x^3");
        m1.add(m3);
        assertFalse(m1.equals(m2));
        assertNotSame(m1,m2);

    }
    @Test
    void equals() {
        m1 = new Monom("2x^3");
        p1 = new Polynom("2x^3");
        c1 = new ComplexFunction(m1);
        assertTrue(m1.equals(p1));
        assertTrue(m1.equals(p1));
        assertTrue(m1.equals(c1));
    }
}