package Ex1;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {    	
    		functions data = FunctionsFactory();
    		int w=1000, h=600, res=200;
    		Range rx = new Range(-10,10);
    		Range ry = new Range(-5,15);
    		data.drawFunctions(w,h,rx,ry,res);
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
