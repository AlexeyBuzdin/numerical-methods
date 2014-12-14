package methods

import equation.Equation
import utils.DataInput

import static math.MathUtils.*

class BisectionMethod implements NonlinearEquationMethod {

    public static final String X_START = "x start"
    public static final String X_END = "x end"
    public static final String TOLERANCE = "Tolerance"

    public static final NMAX = 100

    @Override
    public double solve(Equation eq, DataInput input) {
        double xStart = input.readNum(X_START)
        double xEnd = input.readNum(X_END)
        double tolerance = input.readNum(TOLERANCE);

        double xMid;
        for (int i = 0; i < NMAX; i++) {
            xMid = (xStart + xEnd) / 2
            def tempResult = eq.apply(xMid)
            def toleranceReached = (xEnd - xStart) / 2 < tolerance
            if(tempResult == 0 || toleranceReached) return xMid;

            def startResult = eq.apply(xStart)
            if(sign(tempResult) == sign(startResult)) {
                xStart = xMid
            } else {
                xEnd = xMid
            }
        }
        throw new ArithmeticException()
    }
}
