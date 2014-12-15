package methods

import equation.Equation
import spock.lang.Specification
import utils.DataInput

import static java.lang.Math.*
import static java.lang.Math.PI
import static java.lang.Math.abs

class BisectionMethodTest extends Specification {

    public static final BigDecimal TOLERANCE_VAL = 0.0001

    BisectionMethod method
    DataInput input

    void setup() {
        method = new BisectionMethod()
        input = Mock(DataInput)
    }

    def "Y = X^3 - x - 2"() {
        given: Equation equation = { x -> pow(x, 3) - x - 2  }
        when: double result = method.solve(equation, input)
        then:
            input.readNum(_) >>> [1, 2, TOLERANCE_VAL]
            abs(1.5214 - result) < TOLERANCE_VAL
    }

    def "Y = 2^3x"() {
        given: Equation equation = { x -> pow(2, 3 * x) }
        when: method.solve(equation, input)
        then:
            input.readNum(_) >>> [-1, 1, TOLERANCE_VAL]
            thrown ArithmeticException
    }

    def "Y = x^2 * sin(x)"() {
        given: Equation equation = { x -> pow(x, 2) * sin(x) }
        when: double result = method.solve(equation, input)
        then:
            input.readNum(_) >>> [1, 4, TOLERANCE_VAL]
            abs(PI - result) < TOLERANCE_VAL
    }

    def "Y = x^2 * sin(x) _actual"() {
        given: Equation equation = { x -> pow(x, 2) * sin(x) }
        when: double result = method.solve(equation, input)
        then:
        input.readNum(_) >>> [0, PI, TOLERANCE_VAL]
        abs(0 -result) < TOLERANCE_VAL
    }

    def "Y = x^2 + sin(x)"() {
        given: Equation equation = { x -> pow(x, 2) + sin(x) }
        when: double result = method.solve(equation, input)
        then:
            input.readNum(_) >>> [-2, -0.5, TOLERANCE_VAL]
            abs(-0.8767 - result) < TOLERANCE_VAL
    }

    def "Y = x^2 + sin(x) _actual"() {
        given: Equation equation = { x -> pow(x, 2) + sin(x) }
        when: double result = method.solve(equation, input)
        then:
            input.readNum(_) >>> [0, (3/2)*PI, TOLERANCE_VAL]
            abs(0 - result) < TOLERANCE_VAL
    }
}
