import methods.BisectionMethod
import utils.ConsoleInput

class Main {

    public static void main(String... args) {
        def solver = NonlinearEquationSolver.create(new ConsoleInput(), new BisectionMethod());
    }
}
