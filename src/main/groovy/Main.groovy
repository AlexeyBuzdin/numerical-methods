import methods.BisectionMethod
import utils.Input

class Main {

    public static void main(String... args) {
        new NonlinearEquationSolver(new Input(), new BisectionMethod());
    }
}
