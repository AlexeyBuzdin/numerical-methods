package methods

import equation.Equation
import spock.lang.Specification
import utils.DataInput

class BisectionMethodTest extends Specification {

    BisectionMethod method
    DataInput input

    void setup() {
        method = new BisectionMethod()
        input = Mock(DataInput)
    }

    def "Y = X"() {
        given: Equation equation = { x -> x }
        when: def result = method.solve(equation, input)
        then:
            1 * input.readNum() >> 1
            result == 1
    }

    def "Y = X^3 - x - 2"() {
        given: Equation equation = { x -> Math.pow(x, 3) - x - 2  }
        when: def result = method.solve(equation, input)
        then:
            1 * input.readNum() >> 1
            1 * input.readNum() >> 2
            result == 1.521
    }
}
