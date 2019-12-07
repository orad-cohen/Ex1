package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComplexFunctionTest {


    @BeforeEach
    void setUp(){
        Polynom p1 = new Polynom("3x^2+1");
        Polynom p2 = new Polynom("4x^5-2");
    }

    @Test
    void plus() {
        Polynom p1 = new Polynom("3x^2+1");
        Polynom p2 = new Polynom("4x^5-2");
        Monom m1 = new Monom("3x^4");
        ComplexFunction cf = new ComplexFunction(p1);
        cf.plus(p2);
        cf.plus(p2);
        cf.div(p1);
        cf.mul(m1);
        System.out.println(cf.toString());

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