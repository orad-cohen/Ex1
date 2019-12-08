package Ex1Testing;

import Ex1.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(p1,new Polynom("3x^14-2x^2+1.7x^1+9"));
    }

    @Test
    void testAdd() {//Polynoms
        p2.add(p3);
        p4.add(p3);
        assertEquals(p2,new Polynom("7x^2+10x^3+15x-3+20x^4+x^12"));
        assertEquals(p4,new Polynom("3x^6-7+16x^3-4x^4+15x"));
    }

    @Test
    void substract() {
        p3.substract(p3);
        p4.substract(p2);
        assertEquals(p3.toString(),"0");
        assertEquals(p4, new Polynom("-24x^4-x^12+6x^3+3x^6-7x^2-4"));
    }

    @Test
    void multiply() {// Multiply by monom
        Monom m1 = new Monom("-3.2x^2");
        p3.multiply(m1);
        p4.multiply(new Monom("0"));
        assertEquals(p3,new Polynom("-48x^3+9.6x^2-48x^5-38.4x^6"));
        assertEquals(p4,new Polynom("0"));
    }

    @Test
    void testMultiply() {// Multiply by polynom
        p2.multiply(p3);
        p3.multiply(p4);
        assertEquals(p2,new Polynom("12.0x^16+15.0x^15+15.0x^13-3.0x^12+96.0x^8+60.0x^7+9.0x^6+225.0x^5-99.0x^4+120.0x^3-21.0x^2"));
        assertEquals(p3,new Polynom("36.0x^10+45.0x^9-192.0x^8-183.0x^7+6.0x^6-240.0x^5+15.0x^4-63.0x^3-60.0x^1+12.00"));
    }

    @Test
    void testEquals() {

    }

    @Test
    void isZero() {
        assertTrue(p1.isZero());
    }

    @Test
    void root() {
        double[][] res = {{0,0.2135},{0,0.83334},{2.404,0.9999}};
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
    void testToString() {
    }

    @Test
    void initFromString() {//Div(Plus(Plus(3.0x^2+1.00,4.0x^5-2.00),Plus(3.0x^2+1.00,4.0x^5-2.00)),Plus(3.0x^2+1.00,4.0x^5-2.00),Div(3.0x^2+1.00,4.0x^5-2.00))
        String s = "Div(Plus(Plus(3.0x^2+1.00,4.0x^5-2.00),Plus(3.0x^2+1.00,4.0x^5-2.00)),Plus(3.0x^2+1.00,4.0x^5-2.00))";
        function f = p1.initFromString(s);
        System.out.println(f);
    }

    @Test
    void sortbykey() {
    }
}