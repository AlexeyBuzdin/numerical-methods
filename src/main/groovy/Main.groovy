import methods.BisectionMethod
import methods.MullerMethod
import utils.ConsoleInput
import utils.MockInput

import static java.lang.Math.pow
import static java.lang.Math.sin

class Main {

    public static void main(String... args) {
        def mockInput = new MockInput()
        def input = mockInput

        def solver = NonlinearEquationSolver.create(input, new BisectionMethod());
        ////
        mockInput.addElements(0, 1, 0.01)
        solver.solve({ x -> pow(2, 3 * x) })

        mockInput.addElements(0, 1, 0.0001)
        solver.solve({ x -> pow(2, 3 * x) })

        ////
        mockInput.addElements(1, 4, 0.01)
        solver.solve({ x -> pow(x, 2) * sin(x) })

        mockInput.addElements(1, 4, 0.0001)
        solver.solve({ x -> pow(x, 2) * sin(x) })

        ////
        mockInput.addElements(-2, -0.5, 0.01)
        solver.solve({ x -> pow(x, 2) + sin(x) })

        mockInput.addElements(-2, -0.5, 0.0001)
        solver.solve({ x -> pow(x, 2) + sin(x) })





        solver = NonlinearEquationSolver.create(input, new MullerMethod());
        ////
        mockInput.addElements(0, 0.5, 1, 0.01)
        solver.solve({ x -> pow(2, 3 * x) })

        mockInput.addElements(0, 0.5, 1, 0.0001)
        solver.solve({ x -> pow(2, 3 * x) })

        ////
        mockInput.addElements(1, 2, 4, 0.01)
        solver.solve({ x -> pow(x, 2) * sin(x) })

        mockInput.addElements(1, 2, 4, 0.0001)
        solver.solve({ x -> pow(x, 2) * sin(x) })

        ////
        mockInput.addElements(-2, -1, -0.5, 0.01)
        solver.solve({ x -> pow(x, 2) + sin(x) })

        mockInput.addElements(-2, -1, -0.5, 0.0001)
        solver.solve({ x -> pow(x, 2) + sin(x) })
    }
}
