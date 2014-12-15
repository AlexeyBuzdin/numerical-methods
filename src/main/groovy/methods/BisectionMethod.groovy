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

        if (xStart > xEnd) throw new IllegalArgumentException()
        double f_xEnd = eq.apply(xEnd)
        double f_xStart = eq.apply(xStart)

        if (f_xEnd == 0) {
            printf ("Root %f is found with %d iterations\n", xEnd, 0)
            return xEnd
        }
        if (f_xStart == 0) {
            printf ("Root %f is found with %d iterations\n", xStart, 0)
            return xStart
        }

        if (!differentSigns(f_xStart, f_xEnd))
            throw new ArithmeticException()

        double xMid;
        for (int i = 1; i <= NMAX; i++) {
            xMid = (xStart + xEnd) / 2
            double f_xMid = eq.apply(xMid)
            double xDelta = (xEnd - xStart) / 2
            if(f_xMid == 0 || xDelta < tolerance) {
                printf ("Root %f is found with %d iterations\n", xMid, i)
                return xMid
            }

            f_xStart = eq.apply(xStart)
            if(!differentSigns(f_xMid, f_xStart)) {
                xStart = xMid
            } else {
                xEnd = xMid
            }
        }
        throw new ArithmeticException()
    }
}
