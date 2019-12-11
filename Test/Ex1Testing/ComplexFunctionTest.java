package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexFunctionTest {
    private Polynom p1,p2;
    private Monom m1,m2;
    private ComplexFunction c1,c2,c3;



    @BeforeEach
    void setUp(){
        p1 = new Polynom("2x^2+1");
        m1 = new Monom("3x^2");
        m2 = new Monom("2x^2");
        c1 = new ComplexFunction();
        c2 = new ComplexFunction();
        c3 = new ComplexFunction();
    }

    @Test
    void plus() {
        m1 = new Monom("4");
        c1 = new ComplexFunction(m1);

        c1.plus(m2);

        c1.plus(p1);

        c2 = (ComplexFunction) c1.copy();
        assertNotSame(c1,c2);
        assertTrue(c1.equals(c2));
        c1.plus(c2);
        //should look like plus(plus(plus(4,2x^2),2x^2+1),plus(plus(4,2x^2),2x^2+1))
        //if x = 1 it should be 18
        assertEquals(18,c1.f(1));

    }

    @Test
    void mul() {
        m1 = new Monom("4");
        c1 = new ComplexFunction(m1);

        c1.mul(m2);

        c1.mul(p1);

        c2 = (ComplexFunction) c1.copy();
        assertNotSame(c1,c2);
        assertTrue(c1.equals(c2));
        c1.mul(c2);
        //should look like mul(mul(mul(4,2x^2),2x^2+1),mul(mul(4,2x^2),2x^2+1))
        //if x = 1 it should be 576
        assertEquals(576,c1.f(1));
        m2 = new Monom("0");
        c3 = new ComplexFunction(m2);
        c1.mul(c3);
        assertEquals(0,c1.f(1));

    }

    @Test
    void div() {
        m1 = new Monom("4");
        c1 = new ComplexFunction(m1);

        c1.plus(m2);

        c1.plus(p1);

        c2 = (ComplexFunction) c1.copy();
        assertNotSame(c1,c2);
        assertTrue(c1.equals(c2));
        c1.plus(c2);
        //now c1 divided by c2 should yeald the result of 2 no matter the input
        c1.div(c2);
        assertEquals(2,c1.f(1));
        assertEquals(2,c1.f(30));
        assertEquals(2,c1.f(-100));
        //checking what div by 0 will provide
        c1 = new ComplexFunction(p1);
        m1 = new Monom("0");
        c2 = new ComplexFunction(m1);
        c1.div(c2);
        assertEquals(Double.POSITIVE_INFINITY,c1.f(1));



    }

    @Test
    void minMax() {
        m1 = new Monom("5x");
        m2 = new Monom("4x^2");
        c1 = new ComplexFunction(m1);
        c1.max(m2);

        assertEquals(5,c1.f(1));//expected is left m1
        assertEquals(16,c1.f(2));//expected is right m2

        c1 = new ComplexFunction(m1);
        c1.min(m2);

        assertEquals(4,c1.f(1));//expected is right m2
        assertEquals(10,c1.f(2));//expected is left m1
    }

    @Test
    void comp() {
        m1 = new Monom("5x");
        m2 = new Monom("4x^2");
        c1 = new ComplexFunction(m1);
        c1.comp(m2);
        assertEquals(80,c1.f(2));//5(42^2) = 80
    }
}