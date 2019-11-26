package myMath;

import java.util.HashMap;
import java.util.Iterator;

public class PolynomTest {
    public static void main(String[] args) {
        String[] GoodPoly = {"1+x+x^1-x^3", "7x^2-5x^3+1x^122+8x^12","122-13+17x^4-9x^2",
                "15x^676+3","0x^0","0+15x-3+15x^22+12x^4"};
        String[] SmallPoly = {"x^3+5x-6","3x^6-4+x^3-16x^4","4x+3-2x^2","3x^4-6x+1","2x^6-17x^2"};

        String[] BadPoly = {"3x^-7","5x^x-3","5*x-2.24^2.58","2^","120^blah","hinga^dinga","30x^999999999999999999999999999999",
                "(3x^2)*(10x^4)","(2)(x)","s2"};
        String[] GoodMonoms = {"1","x","x^2", "0.5x^2","5", "1.7x","3.2x^2","-3","-1.5x^2","2", "-x","-3.2x^2","4","-1.5x^2","3x^14"};
        String[] BadMonoms = {"x^-2","4x^2.2","4.5^3","4xx","   ","-3.2"};
        test1(GoodMonoms);
        //test1(BadMonoms);
        test2(GoodMonoms);
        //test2(BadPoly);
        MathTest(GoodPoly);
        //MathTest(BadPoly);
        ComplexMath(SmallPoly);
        rootTest();
        Polynom x= new Polynom();
        Iterator<Monom> O = x.iteretor();






    }
    public static void test1(String[] monoms) {
        Polynom p1 = new Polynom();
        for(int i=0;i<monoms.length;i++) {
            Monom m = new Monom(monoms[i]);
            p1.add(m);}
        double aa = p1.area(0, 1, 0.0001);
        System.out.println(p1.toString());
        p1.substract(p1);
        System.out.println(p1.toString());
    }
    public static void test2(String[] monoms1) {
        Polynom p1 = new Polynom(), p2 =  new Polynom();

        for(int i=0;i<monoms1.length/2;i++) {
            Monom m = new Monom(monoms1[i]);
            p1.add(m);
        }
        for(int i=monoms1.length/2;i<monoms1.length;i++) {
            Monom m = new Monom(monoms1[i]);
            p2.add(m);
        }
        System.out.println("p1: "+p1.toString());
        System.out.println("p2: "+p2.toString());
        p1.add(p2);
        System.out.println("p1+p2: "+p1.toString());
        Monom m = new Monom(monoms1[3]);
        System.out.println("p2: "+p2.toString());
        p1.multiply(p2);
        System.out.println("(p1+p2)*p2: "+p1.toString());
        String s1 = p1.toString();
        Polynom_able pp1 = new Polynom(s1);
        System.out.println("from string: "+pp1);


    }
    public static void CopyTester(String s) {
        Polynom_able p = new Polynom(s);
        Polynom_able copy = new Polynom();
        System.out.println("copy still not a copy of the polynom and should be 0, is it ?..."+ copy.isZero());

        copy = p.copy();
        System.out.println("Testing copy method, entered Polynom is " + p.toString()+ "while the copy is"
                + copy.toString());

        System.out.println("is copy still 0 ?..."+copy.isZero());


        System.out.println("do copy and original equel one another ? ..."+p.equals(copy));

        Monom m = new Monom("4x^2");
        copy.add(m);
        System.out.println("Add a Monom valued at 4x^2 to the copy. Copy is now: "+copy.toString());

        System.out.println("Again, is copy and original the same? ... " +copy.equals(p));

        Polynom temp = new Polynom("4x^2");
        copy.substract(temp);
        System.out.println("Used substrack method with Polynom 4x^2, now its: " + copy.toString());

        System.out.println("Is copy and original the same again? ... "+p.equals(copy));
        Polynom temp1 = new Polynom("x+4x^2");
        p.multiply(temp1);
        System.out.println("before add\n"+ p.toString()+"\n"+copy.toString());
        p.add(temp1);
        copy.add(temp1);
        System.out.println("after add\n"+ p.toString()+"\n"+copy.toString());

        Monom zero = new Monom("0");
        System.out.println("("+p.toString()+") * 0 = ");
        p.multiply(zero);
        System.out.print(p.toString() + " is it really zero? "+ p.isZero());

    }
    public static void ToStringTester(String s) {
        Polynom_able p = new Polynom(s);
        System.out.println("testing to string method, this Polynom is: "+p.toString());

        Monom zero = new Monom("2x^2");
        System.out.println("("+p.toString()+") * "+zero.toString()+" = ");
        p.multiply(zero);
        System.out.println(p.toString() + " is it really zero? "+ p.isZero());

        Polynom_able temp = new Polynom("x+4x^4");
        p.add(temp);
        System.out.println(p.toString() + "+ x+4x^4 = "+ p.isZero());
        Polynom_able zero1 = new Polynom("5x^7-3x+1.222");
        System.out.print("("+p.toString()+")*("+zero1.toString()+") = ");
        p.multiply(zero1);
        System.out.println(p.toString());
        p.multiply(new Monom("0"));
        System.out.println(p.toString() + " is zero? " +p.isZero());
    }

    public static void MathTest(String[] s){
        for(int i = 0;i<s.length;i+=2){

                Polynom_able p1 = new Polynom(s[i]);
                Polynom_able p2 = new Polynom(s[i+1]);
                System.out.println("p1 = "+ p1.toString()+ "\np2 = "+ p2.toString());
                p1.multiply(p2);
                System.out.println("p1*p2= "+ p1.toString());
                p2.add(p1);
                System.out.println("p1*p2+p2= "+ p2.toString());
                p2.substract(p1);
                System.out.println("p1*p2+p2-p1*p2= "+ p2.toString());
                System.out.println("(p1*p2)' = " + p1.derivative().toString());




        }


    }
    public static void ComplexMath(String[] s){
        for(int i =0;i<s.length;i++){
            Polynom p1 = new Polynom(s[i]);
            System.out.println("f(x) = "+p1.toString());
            System.out.println("f(1) = " +p1.f(1));
            System.out.println("f(7) = " +p1.f(7));
            System.out.println("f(9) = " +p1.f(1));
            System.out.println("f(1.5) = " +p1.f(1));

        }

    }
    public static void rootTest(){
        Polynom fx = new Polynom("2x^3-6x+1");
        System.out.println("f(x) = Polynom: " +fx.toString());
        System.out.println("root of f between 1 and 2"+fx.root(1, 2, 0.001));
        double res = fx.f(fx.root(1, 2, 0.0001));
        System.out.println("is root f(root)<eps=0.0001?  "+(res<0.0001) );

    }
}
