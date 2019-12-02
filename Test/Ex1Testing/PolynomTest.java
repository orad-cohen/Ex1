package Ex1Testing;

import Ex1.Polynom;
import Ex1.Monom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PolynomTest  {
    private Polynom p1,p2,p3,p4;

    @BeforeEach
    void  setup() throws Exception{
        p1 = new Polynom();
        p2 = new Polynom("7x^2-5x^3+1x^12+8x^4");
        p3 = new Polynom("0+15x-3+15x^3+12x^4");
        p4 = new Polynom("3x^6-4+x^3-16x^4");

    }

    @Test
    void f() {
        assertEquals(3380292.383,p2.f(3.5),0.001);
        assertEquals(p3.f(0),-3);
        assertEquals(p4.f(1),-16);

    }

    @Test
    void add() { //Monoms
        String[] monoms = {"1","x","x^2", "0.5x^2","5", "1.7x","3.2x^2","-3","-1.5x^2","2", "-x","-3.2x^2","4","-1.5x^2","3x^14"};
        for(int i=0;i<monoms.length;i++) {
            Monom m = new Monom(monoms[i]);
            p1.add(m);}
        assertEquals(p1.toString(),new Polynom("3x^14-2x^2+1.7x^1+9").toString());
    }

    @Test
    void testAdd() {//Polynoms
        p2.add(p3);
        p4.add(p3);
        assertEquals(p2.toString(),new Polynom("7x^2+10x^3+15x-3+20x^4+x^12").toString());
        assertEquals(p4.toString(),new Polynom("3x^6-7+16x^3-4x^4+15x").toString());
    }

    @Test
    void substract() {
        p3.substract(p3);
        p4.substract(p2);
        assertEquals(p3.toString(),"0");
        assertEquals(p4.toString(), new Polynom("-24x^4"));
    }

    @Test
    void multiply() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void isZero() {
    }

    @Test
    void root() {
    }

    @Test
    void copy() {
    }

    @Test
    void derivative() {
    }

    @Test
    void area() {
    }

    @Test
    void iteretor() {
    }

    @Test
    void testMultiply() {
    }

    @Test
    void testToString() {
    }

    @Test
    void initFromString() {
    }

    @Test
    void sortbykey() {
    }
}