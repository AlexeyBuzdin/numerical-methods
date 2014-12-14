package methods

import equation.Equation
import utils.DataInput

import static java.lang.Math.abs
import static java.lang.Math.sqrt

class MullerMethod implements NonlinearEquationMethod {

    public static final String X0 = "x0"
    public static final String X1 = "x1"
    public static final String X2 = "x2"
    public static final String TOLERANCE = "Tolerance"

    public static final NMAX = 100

    @Override
    double solve(Equation eq, DataInput input) {
        double x0 = input.readNum(X0)
        double x1 = input.readNum(X1)
        double x2 = input.readNum(X2)
        double tolerance = input.readNum(TOLERANCE)

        double x

        double li, di, mu, s, l
        for (int itr = 1; itr <= NMAX; itr++) {
            li = (x2 - x1) / (x1 - x0);
            di = (x2 - x0) / (x1 - x0);

            def f_x0 = eq.apply(x0)
            def f_x1 = eq.apply(x1)
            def f_x2 = eq.apply(x2)

            mu = f_x0 * li * li - f_x1 * di * di + f_x2 * (di + li);

            s = sqrt((mu * mu - 4 * f_x2 * di * li * (f_x0 * li - f_x1 * di + f_x2)));
            if (mu < 0) {
                l = (2 * f_x2 * di) / (-mu + s);
            } else {
                l = (2 * f_x2 * di) / (-mu - s);
            }
            x = x2 + l * (x2 - x1);
            printf("At iteration no. %3d, x = %7.5f\n", itr, x);

            if (abs(x - x2) < tolerance) {
                return x;
            }

            x0 = x1
            x1 = x2
            x2 = x
        }

        throw new ArithmeticException()
    }
}
