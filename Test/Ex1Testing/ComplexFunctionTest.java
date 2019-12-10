package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;

class ComplexFunctionTest {
    private Polynom p1,p2;
    private Monom m1,m2;
    private ComplexFunction c1,c2,c3;



    @BeforeEach
    void setUp(){
        p1 = new Polynom("2x^2+1");
        p2 = new Polynom("x^3-2");
        m1 = new Monom("3x^2");
        m2 = new Monom("2x");
        c1 = new ComplexFunction();
        c2 = new ComplexFunction();
        c2 = new ComplexFunction();
    }

    @Test
    void plus() {
        c1.plus(m1);
        System.out.print(c1.f(1)+"....");
        System.out.println(c1.f(2));
        c1.plus(m2);
        System.out.print(c1.f(1)+"....");
        System.out.println(c1.f(2));
        c1.plus(p1);
        System.out.print(c1.f(1)+"....");
        System.out.println(c1.f(2));
        c1.plus(p2);
        System.out.print(c1.f(1)+"....");
        System.out.println(c1.f(2));

        c2 = (ComplexFunction) c1.copy();
        assertNotSame(c1,c2);
        c1.plus(c2);
        System.out.print(c1.f(1)+"....");
        System.out.println(c1.f(2));
    }

    @Test
    void mul() {
    }

    @Test
    void div() {
    }

    @Test
    void max() {
    }

    @Test
    void comp() {
    }
}