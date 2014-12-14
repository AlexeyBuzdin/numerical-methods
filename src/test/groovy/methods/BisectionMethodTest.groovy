package methods

import equation.Equation
import spock.lang.Specification
import utils.DataInput

import static methods.BisectionMethod.TOLERANCE
import static methods.BisectionMethod.X_END
import static methods.BisectionMethod.X_START

class BisectionMethodTest extends Specification {

    NonlinearEquationMethod method
    DataInput input

    void setup() {
        method = new BisectionMethod()
        input = Mock(DataInput)
    }

    def "Y = X"() {
        given: Equation equation = { x -> x }
        when: def result = method.solve(equation, input)
        then:
            1 * input.readNum(X_START) >> 1
            1 * input.readNum(X_END) >> 1
            1 * input.readNum(TOLERANCE) >> 0.01
            result == 1
    }

    def "Y = X^3 - x - 2"() {
        given: Equation equation = { x -> Math.pow(x, 3) - x - 2  }
        when: double result = method.solve(equation, input)
        then:
            1 * input.readNum(X_START) >> 1
            1 * input.readNum(X_END) >> 2
            1 * input.readNum(TOLERANCE) >> 0.0001
            Math.abs(1.521 - result) < 0.001
    }

    def "Y = 2^3x"() {
        given: Equation equation = { x -> Math.pow(2, 3 * x) }
        when: double result = method.solve(equation, input)
        then:
            1 * input.readNum(X_START) >> 1
            1 * input.readNum(X_END) >> 2
            1 * input.readNum(TOLERANCE) >> 0.0001
            Math.abs(1.521 - result) < 0.001
    }

    def "Y = x^2 * sin(x)"() {
        given: Equation equation = { x -> Math.pow(x, 2) * Math.sin(x) }
        when: double result = method.solve(equation, input)
        then:
        1 * input.readNum(X_START) >> 1
        1 * input.readNum(X_END) >> 2
        1 * input.readNum(TOLERANCE) >> 0.0001
        Math.abs(1.521 - result) < 0.001
    }

    def "Y = x^2 + sin(x)"() {
        given: Equation equation = { x -> Math.pow(x, 2) + Math.sin(x) }
        when: double result = method.solve(equation, input)
        then:
        1 * input.readNum(X_START) >> 1
        1 * input.readNum(X_END) >> 2
        1 * input.readNum(TOLERANCE) >> 0.0001
        Math.abs(1.521 - result) < 0.001
    }
}
