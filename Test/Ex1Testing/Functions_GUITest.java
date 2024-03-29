package Ex1Testing;

import Ex1.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Note: minor changes (thanks to Amichai!!)
 * The use of "get" was replaced by iterator!
 * 
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author boaz_benmoshe
 *
 */
class Functions_GUITest {


	public static void main(String[] a) {
		functions data = FunctionsFactory();
	//	int w=1000, h=600, res=200;
	//	Range rx = new Range(-10,10);
	//	Range ry = new Range(-5,15);
//		data.drawFunctions(w,h,rx,ry,res);
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		try {
			data.saveToFile(file);
			Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) {e.printStackTrace();}
		
		String JSON_param_file = "GUI_params.txt";
		data.drawFunctions(JSON_param_file);

	}
	private functions _data=null;
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	void testFunctions_GUI() {
		Functions_GUI x = new Functions_GUI();
		Monom m1 = new Monom("x");
		x.add(m1);
		Polynom p1 = new Polynom("x^2-x-12");
		x.add(p1);
		Polynom p2 = new Polynom("2x^2+3x+5");
		x.add(p2);
		ComplexFunction c1 = new ComplexFunction("div",p2,p1);
		x.add(c1);
		ComplexFunction c2 = new ComplexFunction("plus",c1,c1);
		x.add(c2);
		ComplexFunction c3 = new ComplexFunction("div",new Monom("1"),new Polynom("x-1"));
		x.add(c3);
		ComplexFunction c4 = new ComplexFunction("div",new Monom("2"),new Polynom("x^2-x"));
		x.add(c4);
		ComplexFunction c5 = new ComplexFunction("div",new Polynom("x+3"),new Polynom("x-1"));
		x.add(c5);

		Range yy = new Range(-10,10);
		Range xx = new Range(-10,10);
		x.drawFunctions(1000,600,xx,yy,150);
	}

	@Test
	void testAdd() {
		Functions_GUI x = new Functions_GUI();
		ComplexFunction c = new ComplexFunction(new Monom("x"));
		assertTrue(x.isEmpty());
		assertTrue(x.add(c));
		assertTrue(x.add(c));
		assertEquals(x.size(),2);
	}

	@Test
	void testSaveReadFile() {
		Functions_GUI giver = new Functions_GUI();
		Functions_GUI reciver = new Functions_GUI();
		Monom m = new Monom("2x^3");
		ComplexFunction c = new ComplexFunction("plus",m,m);
		giver.add(c);
		try {
			giver.saveToFile("Testfile");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			reciver.initFromFile("Testfile");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Range yy = new Range(-10,10);
		Range xx = new Range(-10,10);
		reciver.drawFunctions(1000,1000,xx,yy,160);
	}

	@Test
	void testDrawFunctions() {
		Functions_GUI x = new Functions_GUI();
		ComplexFunction c1 = new ComplexFunction("div",new Polynom("x^3+2"),new Monom("x"));
		ComplexFunction c2 = new ComplexFunction("plus",c1,new Polynom("7x^3-3x"));
		ComplexFunction c3 = new ComplexFunction("mul",new Polynom("x^3+2"),new Monom("x^2"));
		ComplexFunction c4 = new ComplexFunction("max",c3,c1);
		x.add(c1);
		x.add(c1);
		x.add(c2);
		x.add(c3);
		x.add(c4);
		Range yy = new Range(-10,10);
		Range xx = new Range(-10,10);
		x.drawFunctions(500,500,xx,yy,100);
		x.drawFunctions("NotHereLol.txt");//test default
		x.drawFunctions("GUI_params.txt");
	}

	public static functions FunctionsFactory() {
		functions ans = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		
		ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);		
		return ans;
	}
}
