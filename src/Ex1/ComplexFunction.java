package Ex1;

public class ComplexFunction implements complex_function{

    private function left,right;
    private Operation Ope;
    List list;


    public ComplexFunction(Object obj1){
        if(obj1 instanceof ComplexFunction){
            left =((ComplexFunction) obj1).left;
            right=((ComplexFunction) obj1).right;
            Ope = ((ComplexFunction) obj1).Ope;
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
        return 0;
    }

    @Override
    public function initFromString(String s) {
        Polynom p1 = new Polynom();
        return p1.initFromString(s);




    }

    @Override
    public function copy() {
        return null;
    }

    public String toString(){

        return Ope+"("+left.toString()+","+right.toString()+")";




       /* if(left instanceof ComplexFunction){
            ComplexFunction temp = (ComplexFunction)left;
            return temp.left.toString();
        }
        else{
            return Ope + "("+left.toString()+","+right.toString()+")";

        }*/





    }



    class List{
        ComplexFunction func;
        List Next = null;

        public List(function f1){
            func.left=f1;
            func.Ope=Operation.None;

        }
        public List(String s, function f1, function f2){
            func.left=f1;
            func.right=f2;
            func.Ope = StringOp(s);
        }

        public void addFunc(Operation ope, function f1){
            Next.func.left=func;
            Next.func.right=f1;
            Next.func.Ope = ope;

        }






    }



}
