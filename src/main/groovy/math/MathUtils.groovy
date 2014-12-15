package math

import static java.lang.Math.signum

class MathUtils {

    static def sign(double num) {
        return num >= 0;
    }

    static def differentSigns(double a, double b) {
        return signum(a) + signum(b) == 0;
    }
}
