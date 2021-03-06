import equation.Equation
import methods.NonlinearEquationMethod
import utils.DataInput

class NonlinearEquationSolver {

    DataInput input
    NonlinearEquationMethod method

    public static NonlinearEquationSolver create(DataInput input, NonlinearEquationMethod method) {
        return new NonlinearEquationSolver(input, method)
    }

    private NonlinearEquationSolver(DataInput input, NonlinearEquationMethod method) {
        this.input = input
        this.method = method
    }

    public String solve(Equation e) {
        try {
            method.solve(e, input)
        } catch (ArithmeticException ignored) {
            println("Failed to solve the Equation")
        }
    }
}
