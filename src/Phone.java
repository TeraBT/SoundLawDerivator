import org.apache.commons.math4.legacy.linear.FieldMatrix;
import org.apache.commons.math4.legacy.linear.FieldVector;
import org.apache.commons.math4.legacy.linear.MatrixUtils;

public class Phone {
    FieldVector<FFE> phoneVector;

    public Phone(FFE primaryAxis, FFE secondaryAxis, FFE tertiaryAxis) {
        phoneVector = MatrixUtils.createFieldVector(new FFE[]{FFE.of(primaryAxis), FFE.of(secondaryAxis), FFE.of(tertiaryAxis)});
        verifyState();
    }

    public boolean isConsonant() {
        FFE primaryAxis = phoneVector.getEntry(0);
        FFE secondaryAxis = phoneVector.getEntry(1);
        FFE tertiaryAxis = phoneVector.getEntry(2);
        return primaryAxis.signum() == 1 && secondaryAxis.signum() == 1 && tertiaryAxis.signum() == 1;
    }

    public boolean isVowel() {
        FFE primaryAxis = phoneVector.getEntry(0);
        FFE secondaryAxis = phoneVector.getEntry(1);
        FFE tertiaryAxis = phoneVector.getEntry(2);
        return primaryAxis.signum() == -1 && secondaryAxis.signum() == -1 && tertiaryAxis.signum() == -1;
    }

    public boolean hasIllegalState() {
        return !isConsonant() && !isVowel();
    }

    public void verifyState() {
        if (this.hasIllegalState()) {
            throw new IllegalStateException();
        }
    }

    @Override
    public String toString() {
        FFE primaryAxis = phoneVector.getEntry(0);
        FFE secondaryAxis = phoneVector.getEntry(1);
        FFE tertiaryAxis = phoneVector.getEntry(2);
        return "P(%s, %s, %s)".formatted(primaryAxis, secondaryAxis, tertiaryAxis);
    }
}
