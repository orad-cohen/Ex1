package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.HashMap;
import java.util.LinkedList;

import groovy.ui.SystemOutputInterceptor;
import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	HashMap<Integer,Monom> MonomHashMap = new HashMap();



	/**
	 * Zero (empty polynom)
	 * @author Orad Cohen
	 */
	public Polynom() {// initialize a 0 Polynom
		Monom m = new Monom("0");
		MonomHashMap.put(0, m);

	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 * @author Teddy Grossman
	 */
	public Polynom(String s) {//initialize Polynom from a given String
		int pre=0;
		Polynom tmp = new Polynom();
		//divide the String by math operators "+" and "-" and add them to the Polynom using the Polynom add method
		for(int i = 0 ; i<s.length();i++){
			if(i!=0&&(s.charAt(i)=='-'||s.charAt(i)=='+')){
				if(s.charAt(i-1)=='^'){
					continue;
				}
				tmp.add(new Monom(s.substring(pre, i)));
				pre=i;
			}
		}
		tmp.add(new Monom(s.substring(pre)));
		//set this Polynom HashMap to the temp Hahsmap
		this.MonomHashMap=tmp.MonomHashMap;	}

	/**\
	 *
	 * @param x
	 * @return
	 * @author Teddy Grossman
	 */
	@Override
	public double f(double x) {// iterates through the Polynom's monoms and add them together using the Monom F(double) method
		double ans = 0;
		for (Monom key : MonomHashMap.values()) {
			ans += key.f(x);
		}
		return ans;
	}

	/**
	 *
	 * @param p1
	 * @author Orad Cohen
	 */
	@Override
	public void add(Polynom_able p1) {
		Polynom temp = new Polynom(p1.copy().toString());//Convert Polynom_able to Polynom Using toString.
		for(Monom value: temp.MonomHashMap.values()){//Iterate through the Hash Map
			try{
				MonomHashMap.get(value.get_power()).add(value);//try to add using the Monom Add
			}
			catch(Exception e){
				MonomHashMap.put(value.get_power(),value);//if Monom add fail (because there is no value with that key) create to Monom in the hashmap.
			}
		}

		
	}

	/**
	 * This is adddingggggg
	 * @link myMath.add
	 * @param m1 Monom
	 * @author Orad Cohen
	 */

	public void add(Monom m1) {
		try{

			MonomHashMap.get(m1.get_power()).add(m1);//try to add using the Monom Add
		}
		catch(Exception e){
			MonomHashMap.put(m1.get_power(),m1);//if Monom add fail (because there is no value with that key) create the Monom in the hashmap.
		}


		
	}

	/**
	 * @param p1
	 * @author Orad Cohen
	 */
	@Override
	public void substract(Polynom_able p1) {
		Polynom x = new Polynom(p1.toString());
		for(Monom value: x.MonomHashMap.values()){//Iterate through the HashMap
			value.multiply(Monom.MINUS1);//Multiply each Monom by -1;
			try{
				MonomHashMap.get(value.get_power()).add(value);//use the add function normally.
			}
			catch(Exception e){
				MonomHashMap.put(value.get_power(),value);}//if Monom with that power doesnt exist, create one
			if(MonomHashMap.get(value.get_power()).get_coefficient()==0){//if coefficent is now zero, remove Monom from hashmap.
				MonomHashMap.remove(value.get_power());
			}

		}
		
	}

	/**
	 * @param p1
	 * @author Teddy Grossman
	 */
	public void multiply(Polynom_able p1) {
		Polynom ans = new Polynom();
		for (Integer key : MonomHashMap.keySet()) {//Iterate through the HashMap
			Polynom temp = new Polynom(p1.toString());
			temp.multiply(MonomHashMap.get(key));//using Monom's multiply(Monom) method on temp Polynom
			ans.add(temp);//add temp to Polynom ans
		}
		MonomHashMap = ans.MonomHashMap; //set Polynom's ans HahsMap as this Polynom's HashMap

	}

	/**
	 * @param p1
	 * @return
	 * @author Orad Cohen
	 */
	@Override
	public boolean equals(Polynom_able p1) {//checks via Polynom's toString method if both have the same String value
		return this.toString().equals(p1.toString());}

	/**
	 * @return
	 * @author Orad Cohen
	 */
	@Override
	public boolean isZero() {//iterates through Polynom's HashMap values
		for(Monom value: MonomHashMap.values()){
			if(value.f(1)!=0){return false;}} //return false if Monom's f(1) is not zero;
		return true;}

	/**
	 * @param x0    starting point
	 * @param x1    end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * @return
	 * @author Orad Cohen
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		if(Math.abs(f(x0))<eps){return x0;}//End condition if either f(x0) or f(x1) value is less the Epsilon
		else if(Math.abs(f(x1))<eps){return x1;}
		else if(Monom.roundAvoid(f((x0+x1)/2))>0){return Monom.roundAvoid(root(x0,(x0+x1)/2,eps));}//return root that maintains (f(x0)*f(x1)<0, but with smaller distance between x0 and x1;
		else{return Monom.roundAvoid(root((x0+x1)/2,x1,eps));
	}}

	/**
	 * @return
	 * @author Orad Cohen
	 */
	@Override
	public Polynom_able copy() {
		Polynom copy = new Polynom();
		for(Monom value: MonomHashMap.values()){//iterates through Polynom's HashMap Monoms
			Monom m = new Monom(value.toString());//create new Monom using Monoms's toString method on the current HashMap value
			copy.add(m);}//add the new Monom into the copy Polynom and return
		return copy;
	}

	/**
	 * @return
	 */
	@Override
	public Polynom_able derivative() {
		Polynom ans = new Polynom();
		for(Monom value : MonomHashMap.values()){//iterates through Polynom's HashMap values
			Monom temp = value.derivative();//and applies Monom's derivative method on each one
			ans.add(temp);//then adds it to the new ans Polynom
		}
		return ans; //return asn
	}

	/**
	 * @param x0  starting pooint
	 * @param x1  end point
	 * @param eps positive step value
	 * @return
	 * @author Teddy Grossman
	 */

	public double area(double x0, double x1, double eps) {//done with no recursions since space wise it cannot support eps
		double ans = 0;

		if(x0 > x1) {// if x0 is bigger then x1 then ans would be returned as 0 (with been told to do so for this project)
			return ans;
		}
		while(x0 < x1) {//divide [x0,x1] to many rectangles with width of eps and sum all of their areas together
			if(this.f(x0) >= 0) {//checks if the area is in the positive half of the graph
				ans = ans + (this.f(x0) * eps);//return only the positive area's sum (with been told to do so for this project)
			}
			x0 = x0 + eps;
		}
		return ans;
	}

	/**
	 * @return
	 * @author Orad Cohen
	 */
	@Override
	public Iterator<Monom> iteretor() {//return an iterator pf the Polynom's HashMap
		Iterator it = MonomHashMap.entrySet().iterator();

		return it;}

	/**
	 *
	 * @param m1
	 * @author Teddy Grossman
	 */
	@Override
	public void multiply(Monom m1) {
		Polynom p1 = new Polynom();//create an empty Polynom
		if(m1.get_coefficient()==0){//if the Monom to be multiplied has a coefficient of 0
			MonomHashMap.clear();//clear Polynom's HashMap
		}
		else{
			for (Integer key : MonomHashMap.keySet()) {//else iterate through this Polynom's HashMap
				Polynom p2 = new Polynom(copy().toString());//create a copy Polynom
 				p2.MonomHashMap.get(key).multiply(m1);//multiply copy's Monoms using Monoms multiply(Monom) method
				p1.add(p2.MonomHashMap.get(key));//add the results to the empty Polynom
			}
			this.MonomHashMap = p1.MonomHashMap; //set this Polynom's HashMap to be the "once empty" p1 Polynom HashMap

		}

	}

	/**
	 * @return
	 * @author Orad Cohen
	 */
	@Override
	public String toString() {
		Polynom p1 = new Polynom();//create an empty Polynom
		for(Integer key: MonomHashMap.keySet()){//iterate through Polynom's HashMap
			p1.add(MonomHashMap.get(key));}//add the values of this Polynom to the empty Polynom
		String ans = sortbykey(p1);//set String ans as the sortbykey(Polynom) method of the "once empty" p1
		return ans.length()==0? "0":ans; //return the ans String, but if ans String is empty return "0" as a String



	}

	@Override
	public function initFromString(String s) {
		return null;
	}

	//this has been inspired from GeekToGeek Website's code.
	public  String sortbykey(Polynom p1){
		String ans = "";
		Map<Integer, Monom> map = p1.MonomHashMap;
		// Function to sort map by Key
		// TreeMap to store values of HashMap
		TreeMap<Integer, Monom> sorted = new TreeMap<>(map);
		// Copy all data from hashMap into TreeMap
		sorted.putAll(map);
		String[] Reverse = new String[MonomHashMap.size()];
		int count = 0;
		// Display the TreeMap which is naturally sorted
		for (Map.Entry<Integer, Monom> entry : sorted.entrySet())
			if(entry.getValue().get_coefficient()==0){
				continue;}
			else if(entry.getValue().get_coefficient()<0||ans.length()==0){
				Reverse[count++]=entry.getValue().toString();}
		for(int i = count-1; i>=0;i--){
			if(i!=count-1&&Reverse[i].charAt(0)!='-'){
				ans+= "+"+Reverse[i];
			}
			else{
				ans+=Reverse[i];
			}
		}
		return ans;}}








