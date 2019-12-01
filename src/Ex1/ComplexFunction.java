package Ex1;

public class ComplexFunction implements complex_function{
    Polynom Poly;

    public ComplexFunction(Polynom p1){
        Poly = p1;
    }
    public ComplexFunction(Operation Ope, Polynom p1, Polynom p2)
    {
        Poly = p1;

        switch (Ope){
            case Plus:  this.plus(p2);
            case Div:   this.div(p2);
            case Mul:   this.mul(p2);
            case Max:   this.max(p2);
            case Min:   this.min(p2);
        }

    }


    @Override
    public void plus(function f1) {

    }

    @Override
    public void mul(function f1) {

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
    public function left() {
        return null;
    }

    @Override
    public function right() {
        return null;
    }

    @Override
    public Operation getOp() {
        return null;
    }

    @Override
    public double f(double x) {
        return 0;
    }

    @Override
    public function initFromString(String s) {
        return null;
    }

    @Override
    public function copy() {
        return null;
    }
}
