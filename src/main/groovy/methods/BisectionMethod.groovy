package methods

import equation.Equation
import utils.DataInput

class BisectionMethod implements NonlinearEquationMethod {

    public static final NMAX = 15
    public static final TOLERANCE = 0.01

    public double solve(Equation eq, DataInput input) {
        double xStart = input.readNum()
        double xEnd = input.readNum()

        double xMid;
        for (int i = 0; i < NMAX; i++) {
            xMid = (xStart + xEnd) / 2
            def tempResult = eq.apply(xMid)
            def toleranceReached = (xEnd - xStart) / 2 < TOLERANCE
            if(tempResult == 0 || toleranceReached) return xMid;

            def startResult = eq.apply(xStart)
            if(sign(tempResult) == sign(startResult)) {
                xStart = xMid
            } else {
                xEnd = xMid
            }
        }
        throw new ArithmeticException();
    }

    def boolean sign(double num) {
        return num >= 0;
    }
}
