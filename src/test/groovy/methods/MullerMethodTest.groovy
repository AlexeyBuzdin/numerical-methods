package methods

import equation.Equation
import spock.lang.Specification
import utils.DataInput

import static java.lang.Math.*

class MullerMethodTest extends Specification {

    public static final double TOLERANCE_VAL = 0.0001

    MullerMethod method
    DataInput input

    void setup() {
        method = new MullerMethod()
        input = Mock(DataInput)
    }

    def "Y = cos(x) - x*exp(x)"() {
        given: Equation equation = { x -> cos(x) - x * exp(x) }
        when: double result = method.solve(equation, input)
        then:
            input.readNum(_) >>> [-1, 0, 1, TOLERANCE_VAL]
            abs(0.5178 - result) < TOLERANCE_VAL
    }

    def "Y = sin(x) + x/2.0 - 1"() {
        given: Equation equation = { x -> sin(x) + x/2.0 - 1 }
        when: double result = method.solve(equation, input)
        then:
            input.readNum(_) >>> [0.3, 0.8, 0.9, TOLERANCE_VAL]
            abs(0.7045 - result) < TOLERANCE_VAL
    }

    def "Y = 2^3x"() {
        given: Equation equation = { x -> pow(2, 3 * x) }
        when: method.solve(equation, input)
        then:
            input.readNum(_) >>> [-1, 0, 1, TOLERANCE_VAL]
            thrown ArithmeticException
    }

    def "Y = x^2 * sin(x)"() {
        given: Equation equation = { x -> pow(x, 2) * sin(x) }
        when: double result = method.solve(equation, input)
        then:
            input.readNum(_) >>> [1, 2, 4, TOLERANCE_VAL]
            abs(PI - result) < TOLERANCE_VAL
    }

    def "Y = x^2 + sin(x)"() {
        given: Equation equation = { x -> pow(x, 2) + sin(x) }
        when: double result = method.solve(equation, input)
        then:
            input.readNum(_) >>> [-2, -1, -0.5, TOLERANCE_VAL]
            abs(-0.8767 - result) < TOLERANCE_VAL
    }
}
