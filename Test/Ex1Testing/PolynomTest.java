package Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class PolynomTest  {
    private Polynom p1,p2,p3,p4,p5;
    private Monom m1;

    @BeforeEach
    void  setup() throws Exception{
        p1 = new Polynom();
        p2 = new Polynom("4x^2+3x^3");
        p3 = new Polynom("6-x+6x^2");
        p4 = new Polynom("3x^4+2x^2");
        p5 = new Polynom("x^6+x^5");
        m1 = new Monom("0");

    }

    @Test
    void f() {
        assertEquals(40,p2.f(2));
        assertEquals(6,p3.f(0));
        assertEquals(5,p4.f(1));

    }

    @Test
    void testMonomAdd() { //Monoms
        String[] monoms = {"0","4x","-2x","3","5x^5","5x^5"};
        for(int i=0;i<monoms.length;i++) {
            Monom m = new Monom(monoms[i]);
            p1.add(m);}
        assertEquals(p1,new Polynom("10x^5+2x+3"));
    }

    @Test
    void testPolyAdd() {//Polynoms
        p2.add(p3);
        p4.add(p3);
        p1 = new Polynom("10x^2+3x^3-x+6");
        assertTrue(p2.equals(p1));
    }

    @Test
    void substract() {
        p3.substract(p3);
        assertEquals(p3.toString(),"0");
        p4.substract(p2);
        assertTrue(p4.equals(new Polynom("3x^4-3x^3-2x^2")));
    }

    @Test
    void multiply() {// Multiply by monom
        m1 = new Monom("-3.2x^2");
        p3.multiply(m1);
        assertEquals(p3,new Polynom("-19.2x^2+3.2x^3-19.2x^4"));

        p1 = (Polynom) p4.copy();
        assertNotSame(p1,p4);
        p4.multiply(new Monom("1"));
        assertTrue(p4.equals(p1));
        p4.multiply(new Monom("0"));
        assertEquals(p4,new Polynom("0"));

    }

    @Test
    void testMultiply() {// Multiply by polynom
        p2.multiply(p3);//(4x^2+3x^3)*(6x^2+6-x) = 24x^4+24x^2-4x^3+18x^5+18x^3-3x^4
        p3.multiply(p4);//(6x^2+6-x)*(3x^4+2x^2) = 18x^6+12x^4+18x^4+12x^2-3x^5-2x^3
        assertEquals(p2,new Polynom("24x^4+24x^2-4x^3+18x^5+18x^3-3x^4"));
        assertEquals(p3,new Polynom("18x^6+12x^4+18x^4+12x^2-3x^5-2x^3"));
    }

    @Test
    void testEquals() {
        assertTrue(p1.equals(m1));
        p1 = (Polynom) p4.copy();
        assertTrue(p4.equals(p1));
        assertNotSame(p1,p4);
        p4.add(new Monom("x"));
        p4.substract(new Polynom("x"));
        assertTrue(p4.equals(p1));
    }

    @Test
    void isZero() {
        assertTrue(p1.isZero());
    }

    @Test
    void root() {
        double root;
        double eps = 0.00001;
        p1 = new Polynom("x");
        root = p1.root(-100,100,eps);
        assertTrue(0 <= Math.abs(p1.f(root)) && Math.abs(p1.f(root)) <= eps);
        p1 = new Polynom("x^2-x-12");
        root = p1.root(-100,100,eps);
        assertTrue(0 <= Math.abs(p1.f(root)) && Math.abs(p1.f(root)) <= eps);
        p1 = new Polynom("x^5 - 2x^3 - 2");
        root = p1.root(-100,100,eps);
        assertTrue(0 <= Math.abs(p1.f(root)) && Math.abs(p1.f(root)) <= eps);
    }

    @Test
    void copy() {
        p1 = (Polynom) p4.copy();
        assertTrue(p4.equals(p1));
        assertNotSame(p1,p4);
    }

    @Test
    void derivative() {
        p1 = new Polynom("4x^4+2x^2-x+4");//before
        p2 = new Polynom("16x^3+4x-1");//after
        assertTrue(p1.derivative().equals(p2));
    }

    @Test
    void area() {
        double area;
        area = p4.area(1,3,0.0001);
        System.out.println(area);
        area = p1.area(1,2,0.0001);//Polynom 0
        System.out.println(area);
        p1 = new Polynom("2");
        area = p1.area(0,3,0.0001);//Polynom 0
        System.out.println(area);
    }

    @Test
    void iteretor() {
        Iterator<Monom> ite = p3.iteretor();

        while(ite.hasNext()){
            System.out.println(ite.next().toString());
        }

    }

    @Test
    void testToString() {
        System.out.println(p1.toString());
        System.out.println(p3.toString());
        System.out.println(p4.toString());
    }

    @Test
    void initFromString() {
        String s = "2x^2+4x^5-4";
        Polynom m = (Polynom) p1.initFromString(s);
        p1 = new Polynom(s);
        assertTrue(m.equals(p1));

    }

    @Test
    void sortbykey() {
        p1 = new Polynom("6-x^3+7x^2");
        System.out.println(p1.toString());
    }
}