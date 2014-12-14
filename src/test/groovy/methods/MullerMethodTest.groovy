package methods

import equation.Equation
import spock.lang.Specification
import utils.DataInput

import static methods.MullerMethod.*


class MullerMethodTest extends Specification {

    NonlinearEquationMethod method
    DataInput input

    void setup() {
        method = new MullerMethod()
        input = Mock(DataInput)
    }


    def "Y = cos(x) - x*exp(x)"() {
        given: Equation equation = { x -> Math.cos(x) - x * Math.exp(x) }
        when: double result = method.solve(equation, input)
        then:
            1 * input.readNum(X0) >> -1
            1 * input.readNum(X1) >> 0
            1 * input.readNum(X2) >> 1
            1 * input.readNum(TOLERANCE) >> 0.0001
            Math.abs(1.521 - result) < 0.001
    }

    def "Y = X^3 - x - 2"() {
        given: Equation equation = { x -> Math.pow(x, 3) - x - 2  }
        when: double result = method.solve(equation, input)
        then:
        1 * input.readNum(X0) >> 1
        1 * input.readNum(X1) >> 2
        1 * input.readNum(X2) >> 2
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
