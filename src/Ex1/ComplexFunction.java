package Ex1;

public class ComplexFunction implements complex_function{

    private function left,right;
    private Operation Ope;
    private Polynom p1 = new Polynom();
    //Constructors.
    public ComplexFunction(){
        left = new Polynom("0");
        right = left.copy();
        Ope = Operation.None;
    }
    public ComplexFunction(Object obj1){
        if(obj1 instanceof ComplexFunction){
            left =((ComplexFunction) obj1).left;
            right=((ComplexFunction) obj1).right;
            Ope = ((ComplexFunction) obj1).Ope;
        }
        else if (obj1 instanceof Monom){
            left = new Monom(obj1.toString());

        }
        else{
            left = (function)obj1;

        }

    }

    public ComplexFunction(Operation Oper, Object obj1, Object obj2) {
        left = (function)obj1;
        right = (function)obj2;
        Ope = Oper;

        }

    public ComplexFunction(String Oper, Object obj1, Object obj2) {
            left = (function)obj1;
            right = (function)obj2;
            Ope = StringOp(Oper);
    }
    //helper function for getting Operation Enum from string.
    public Operation StringOp(String Ope){
        switch (Ope) {
            case "plus":
                return Operation.Plus;
            case "div":
                return Operation.Div;
            case "mul":
                return Operation.Mul;
            case "max":
                return Operation.Max;
            case "min":
                return Operation.Min;
            case "":
                return Operation.None;
            default:
                return Operation.Error;
        }
    }



    @Override
    public void plus(function f1) {
        if(right == null){
            right = f1;
            Ope = Operation.Plus;
        }
        else{
            left = new ComplexFunction(Ope,left,right);
            right = f1;
            Ope = Operation.Plus;
        }
    }

    @Override
    public void mul(function f1) {
        if(right == null){
            right = f1;
            Ope = Operation.Mul;
        }
        else{
            left = new ComplexFunction(Ope,left,right);
            right = f1;
            Ope = Operation.Mul;
        }

    }

    @Override
    public void div(function f1) {
        if(right == null){
            right = f1;
            Ope = Operation.Div;
        }
        else{
            left = new ComplexFunction(Ope,left,right);
            right = f1;
            Ope = Operation.Div;
        }

    }

    @Override
    public void max(function f1) {
        if(right == null){
            right = f1;
            Ope = Operation.Max;
        }
        else{
            left = new ComplexFunction(Ope,left,right);
            right = f1;
            Ope = Operation.Max;
        }

    }

    @Override
    public void min(function f1) {
        if(right == null){
            right = f1;
            Ope = Operation.Min;
        }
        else{
            left = new ComplexFunction(Ope,left,right);
            right = f1;
            Ope = Operation.Min;
        }

    }

    @Override
    public void comp(function f1) {
        if(right == null){
            right = f1;
            Ope = Operation.Comp;
        }
        else{
            left = new ComplexFunction(Ope,left,right);
            right = f1;
            Ope = Operation.Comp;
        }

    }

    @Override
    public function left() { return this.left; }

    @Override
    public function right() { return this.right; }

    @Override
    public Operation getOp() {
        return this.Ope;
    }

    @Override
    public double f(double x) {//returns a function value at certain x.
        switch (getOp()){
            case Plus:
                return left.f(x)+right.f(x);

            case Div:
                Double pinf = Double.POSITIVE_INFINITY;
                Double ninf = Double.NEGATIVE_INFINITY;
                if(left.f(x)==pinf||right.f(x)==pinf){//takes care of deviding by zero end-point/
                    return pinf;
                }
                else if (left.f(x)==ninf||right.f(x)==ninf){
                    return  ninf;

            }
                return left.f(x)/right.f(x);
            case Mul:
                return left.f(x)*right.f(x);
            case Max:
                if(left.f(x)>right.f(x)){
                    return left.f(x);
                }
                else{
                    return right.f(x);
                }
            case Min:
                if(left.f(x)<right.f(x)){
                    return left.f(x);
                }
                else{
                    return right.f(x);
                }
            case Comp:
                return left.f(right.f(x));
            case None:
                return left.f(x);

            default:
                throw new RuntimeException();




        }


    }

    @Override
    public function initFromString(String s) {
        if(!s.endsWith(")")){//Stopping condition in case we arrived to a function that is not a complex one.
            return new Polynom(s);
        }
        s = s.toLowerCase().replace(" ","");
        int[] OpeArray = {s.indexOf("plus"), s.indexOf("mul"),s.indexOf("div"),s.indexOf("max"),s.indexOf("min")};
        int first=s.length();
        for(int i = 0;i<OpeArray.length;i++){
            if (first>OpeArray[i]&&OpeArray[i]!=-1){
                first = OpeArray[i];
            }
        }
        int counter = 1;
        int bra1 = s.indexOf('(',first);
        int bra2 = s.lastIndexOf(')');
        String ope = (s.substring(first,bra1));
        int c =0;
        for(int x = bra1+1; x<s.length()&&counter!=0;x++){//find out the comma that separates our two functions.
            if(s.charAt(x)==','){
                counter--;}
            else if(s.charAt(x)=='('){
                counter++;
            }
            c=x;
        }

        if(s.length()>=0&&s.indexOf(")")==s.length()-1){//Stopping condition in case we arrive to a complex function without nested complex functions.
            function f1 =new Polynom(s.substring(bra1+1,c));
            function f2 =new Polynom(s.substring(c+1,bra2 ));
            return new ComplexFunction(ope,f1,f2);
        }
        else{
            return new ComplexFunction(ope,initFromString(s.substring(bra1+1,c )),initFromString(s.substring(c+1,bra2)));
        }}

    @Override
    public function copy() {//uses the init form string to make hard copy.
        String s = toString();
        return initFromString(s);
    }
    @Override
    public boolean equals(Object obj){//checks 400 points to check if equals.
        ComplexFunction cf = new ComplexFunction(obj);
        for(double x =-100;x<=100;x+=0.5){
            if(f(x)!=cf.f(x)){
                return false;
            }}
        return true;
       }



    public String toString(){
        return Ope+"("+left.toString()+","+right.toString()+")";

    }














}
