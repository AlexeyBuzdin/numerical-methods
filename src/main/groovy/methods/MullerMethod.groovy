package methods

import equation.Equation
import utils.DataInput

import static java.lang.Math.abs
import static java.lang.Math.pow
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

        double root

        double li, di
        for (int i = 1; i <= NMAX; i++) {
            li = (x2 - x1) / (x1 - x0);
            di = (x2 - x0) / (x1 - x0);

            def f_x0 = eq.apply(x0)
            def f_x1 = eq.apply(x1)
            def f_x2 = eq.apply(x2)

            double mu = f_x0 * pow(li, 2) - f_x1 * pow(di, 2) + f_x2 * (di + li);
            double s = sqrt((pow(mu, 2) - 4 * f_x2 * di * li * (f_x0 * li - f_x1 * di + f_x2)));
            double l = (2 * f_x2 * di) / (-mu + (mu < 0 ? s : -s) )

            root = x2 + l * (x2 - x1);
            printf("At iteration no. %3d, root = %7.5f", i, root);

            if (abs(root - x2) < tolerance) {
                return root;
            }

            x0 = x1
            x1 = x2
            x2 = root
        }

        throw new ArithmeticException()
    }
}
