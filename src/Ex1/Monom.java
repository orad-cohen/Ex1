
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public double get_coefficient() {
		return roundAvoid(this._coefficient);
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x,p);
		return ans;
	}
	public boolean isZero() {return this.get_coefficient() == 0;}


	// ***************** add your code below **********************
	/**
	 * @param s
	 * @author Orad Cohen
	 */
	public Monom(String s) {

		double Co = 1;
		int Po = 1;
		try {
			s = s.toLowerCase();
			int i0 = s.indexOf("x");
			int i2 = s.indexOf("-x");
			int i3 = s.indexOf("x^");
			if(i2>=0) {Co=-1;}
			else {
				if(i0>0) {
					Co = Double.parseDouble(s.substring(0,i0));
				}
				if(i0<0) {
					Co = Double.parseDouble(s);
				}
			}
			if(i3>=0) {
				Po = Integer.parseInt(s.substring(i3+2));
			}
			else {
				if(i0<0)  {Po = 0;}
			}
			set_coefficient(Co);
			set_power(Po);

		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}





		}

	/**
	 * @param m
	 * @author Orad Cohen
	 */
		public void add (Monom m){
			double x = this.get_coefficient();
			double y= m.get_coefficient();
		set_coefficient(x+y);

		}//add the coefficients.


	/**
	 * @param d
	 * @author Orad Cohen
	 */
		public void multiply (Monom d){//add the power and multiply the coefficien
			set_coefficient(get_coefficient()*d.get_coefficient());
			if(get_coefficient()==0){
				set_power(0);
			}
			else{
				set_power(get_power()+d.get_power());}
			}




	/**
	 * @return
	 * @author Orad Cohen
	 */
		public String toString () {
			String Var;
			String Coe;
			Var = get_power() == 0 ? "" : "x^";
			Coe = get_coefficient() == 0 ? "" : ""+roundAvoid(get_coefficient());

			String ans = Coe + Var + this.get_power();
			return ans;
		}
		void set_coefficient(String s){
			try {
				if (s.length() == 1) {
					if (s.charAt(0) == '-') {
						set_coefficient(-1);
					} else if (s.charAt(0) == '+') {
						set_coefficient(1);
					} else {
						set_coefficient(Double.parseDouble(s));
					}
				} else {
					set_coefficient(Double.parseDouble(s));}}
			catch (Exception e){
				System.out.println("Err, Coefficient not found, or Incorrect input");
			}

					}
		void set_power(String s){
			try{set_power(Integer.parseInt(s));}
			catch (Exception e){
				System.out.println(e.toString());
			}

		}
	public static double roundAvoid(double value) {//round a double to the 7th number after decimal
			return value;}
			/*String Dou = ""+value;
			int Dec = Dou.indexOf('.')+8;

			if(Dec>=Dou.length()){return Double.parseDouble(Dou);}
			else{
				return Double.parseDouble(Dou.substring(0, Dec));}



	}*/
	public function initFromString(String s){

			return null;

	}
	public function copy(){

			return null;

	} // clone
	public boolean equals(Object obj){
		return false;
			//return _coefficient==obj.coefficient&&;
	}



		// you may (always) add other methods.

		//****************** Private Methods and Data *****************


		private void set_coefficient ( double a){
			this._coefficient = a;
		}
		private void set_power ( int p){
			if (p < 0) {
				throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
			}
			this._power = p;
		}
		private static Monom getNewZeroMonom () {
			return new Monom(ZERO);
		}
		private double _coefficient;
		private int _power;


	}
