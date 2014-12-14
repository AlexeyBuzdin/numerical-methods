import equation.Equation
import methods.NonlinearEquationMethod

class NonlinearEquationSolver {

    NonlinearEquationMethod method

    public NonlinearEquationSolver(NonlinearEquationMethod method) {
        this.method = method
    }

    public String solve(Equation e) {
        method.solve(e)
    }
}
