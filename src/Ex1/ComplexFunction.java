package Ex1;

import java.util.function.Function;

public class ComplexFunction implements complex_function{
    function left,right;
    Operation Ope;

    public ComplexFunction(Object obj1){
        left = (function)obj1;
        Ope = Operation.None;
    }

    public ComplexFunction(String Ope, Object obj1){
        Operation p = StringOp(Ope);
        if(p == Operation.Plus||p == Operation.Div||p == Operation.Mul)
        {
            p = Operation.None;
        }
        left = (function)obj1;
    }

    public ComplexFunction(String Ope, Object obj1, Object obj2) {
        Operation p = StringOp(Ope);
        left = (function) obj1;
        right = (function) obj2;
    }

    public Operation StringOp(String Ope){
        switch (Ope) {
            case "Plus":
                return Operation.Plus;
            case "Div":
                return Operation.Div;
            case "Mul":
                return Operation.Mul;
            case "Max":
                return Operation.Max;
            case "Min":
                return Operation.Min;
            case "":
                return Operation.None;
            default:
                return Operation.Error;
        }
    }



    @Override
    public void plus(function f1) {
        Polynom p;
        p = (Polynom)left();
        p.add((Polynom)f1);
    }

    @Override
    public void mul(function f1) {
        Polynom p;
        p = (Polynom)left();
        p.multiply((Polynom)f1);
    }

    @Override
    public void div(function f1) {

    }

    @Override
    public void max(function f1) {

    }

    @Override
    public void min(function f1) {

    }

    @Override
    public void comp(function f1) {

    }

    @Override
    public function left() { return this.left; }

    @Override
    public function right() { return this.right; }

    @Override
    public Operation getOp() { return this.Ope; }

    @Override
    public double f(double x) {
        return 0;
    }

    @Override
    public function initFromString(String s) {
        function ans;
        Polynom p = new Polynom(s);
        ans = p;

        return ans;
    }

    @Override
    public function copy() {
        return null;
    }
}
