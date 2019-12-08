package Ex1;

public class ComplexFunction implements complex_function{

    private function left,right;
    private Operation Ope;
    private Polynom p1 = new Polynom();


    public ComplexFunction(Object obj1){
        if(obj1 instanceof ComplexFunction){
            left =((ComplexFunction) obj1).left;
            right=((ComplexFunction) obj1).right;
            Ope = ((ComplexFunction) obj1).Ope;
        }
        else if (obj1 instanceof Monom){
            left = new Polynom(obj1.toString());
            Ope =Operation.None;
        }
        else{
            left = (function)obj1;
            Ope = Operation.None;
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
    public double f(double x) {
        switch (getOp()){
            case Plus:
                return left.f(x)+right.f(x);

            case Div:
                if(right.f(x)==0){
                    return Double.POSITIVE_INFINITY;
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
            default:
                throw new RuntimeException();




        }


    }

    @Override
    public function initFromString(String s) {
        function ff = p1.initFromString(s);
        return ff    ;}

    @Override
    public function copy() {
        Polynom p1 = new Polynom();
        String s = toString();
        function ff = p1.initFromString(s);
        return ff;
    }

    public String toString(){

        return Ope+"("+left.toString()+","+right.toString()+")";

    }













}
