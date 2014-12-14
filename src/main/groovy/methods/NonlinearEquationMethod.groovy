package methods

import equation.Equation
import utils.DataInput

interface NonlinearEquationMethod {

    public double solve(Equation eq, DataInput input)

}
