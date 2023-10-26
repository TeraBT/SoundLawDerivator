import org.apache.commons.math4.legacy.core.Field;
import org.apache.commons.math4.legacy.core.FieldElement;
import org.apache.commons.math4.legacy.exception.MathArithmeticException;
import org.apache.commons.math4.legacy.exception.NullArgumentException;
import org.apache.commons.numbers.fraction.Fraction;


/**
 * FFE stands for FractionFieldElement
 */
public class FFE implements FieldElement<FFE> {
    private final Fraction fraction;

    public static FFE of(int num, int den) {
        return new FFE(Fraction.of(num, den));
    }

    public static FFE of(FFE ffe) {
        return new FFE(ffe.getFraction());
    }
    private FFE(Fraction fraction) {
        this.fraction = Fraction.of(fraction.getNumerator(), fraction.getDenominator());
    }

    @Override
    public FFE add(FFE a) throws NullArgumentException {
        return new FFE(fraction.add(a.getFraction()));
    }

    @Override
    public FFE subtract(FFE a) throws NullArgumentException {
        return new FFE(fraction.subtract(a.getFraction()));
    }

    @Override
    public FFE negate() {
        return new FFE(fraction.negate());
    }

    @Override
    public FFE multiply(int n) {
        return new FFE(fraction.multiply(n));
    }

    @Override
    public FFE multiply(FFE a) throws NullArgumentException {
        return new FFE(fraction.multiply(a.getFraction()));
    }

    @Override
    public FFE divide(FFE a) throws NullArgumentException, MathArithmeticException {
        return new FFE(fraction.divide(a.getFraction()));
    }

    @Override
    public FFE reciprocal() throws MathArithmeticException {
        return new FFE(fraction.reciprocal());
    }

    @Override
    public Field<FFE> getField() {
        return new FFEField();
    }

    public int signum() {
        return fraction.signum();
    }

    private Fraction getFraction() {
        return Fraction.of(fraction.getNumerator(), fraction.getDenominator());
    }

    @Override
    public String toString() {
        return fraction.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof FFE) {
            return fraction.equals(((FFE) other).fraction);
        }
        return false;
    }
}
