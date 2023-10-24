//import org.apache.commons.math4.legacy.core.Field;
//import org.apache.commons.math4.legacy.core.FieldElement;
//import org.apache.commons.math4.legacy.exception.MathArithmeticException;
//import org.apache.commons.math4.legacy.exception.NullArgumentException;
//import org.apache.commons.numbers.fraction.Fraction;
//
//public class SLDFraction implements FieldElement<SLDFraction> {
//    private Fraction fraction;
//
//    public SLDSLDFraction(int num, int den) {
//        fraction = SLDFraction.of(num, den);
//    }
//
//    @Override
//    public SLDFraction add(SLDFraction a) throws NullArgumentException {
//        return fraction.add(a);
//    }
//
//    @Override
//    public SLDFraction subtract(SLDFraction a) throws NullArgumentException {
//        return fraction.subtract(a);
//    }
//
//    @Override
//    public SLDFraction negate() {
//        return fraction.negate();
//    }
//
//    @Override
//    public SLDFraction multiply(int n) {
//        return fraction.multiply(n);
//    }
//
//    @Override
//    public SLDFraction multiply(SLDFraction a) throws NullArgumentException {
//        return fraction.multiply(a);
//    }
//
//    @Override
//    public SLDFraction divide(SLDFraction a) throws NullArgumentException, MathArithmeticException {
//        return fraction.divide(a);
//    }
//
//    @Override
//    public SLDFraction reciprocal() throws MathArithmeticException {
//        return fraction.reciprocal();
//    }
//
//    @Override
//    public Field<SLDFraction> getField() {
//        return null;
//    }
//}
