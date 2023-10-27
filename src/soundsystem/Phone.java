package soundsystem;

import org.apache.commons.math4.legacy.linear.FieldVector;
import org.apache.commons.math4.legacy.linear.MatrixUtils;

public class Phone {
    protected FieldVector<FFE> phoneVector;

    public Phone(FFE primaryAxis, FFE secondaryAxis, FFE tertiaryAxis) {
        phoneVector = MatrixUtils.createFieldVector(new FFE[] {primaryAxis, (secondaryAxis), (tertiaryAxis)});
        verifyState();
    }

    public Phone(int num0, int den0, int num1, int den1, int num2, int den2) {
        phoneVector = MatrixUtils.createFieldVector(new FFE[] {FFE.of(num0, den0), FFE.of(num1, den1), FFE.of(num2, den2)});
        verifyState();
    }

    public Phone(FieldVector<FFE> phoneVector) {
        this.phoneVector = MatrixUtils.createFieldVector(new FFE[]{phoneVector.getEntry(0),
                phoneVector.getEntry(1), phoneVector.getEntry(2)});
        verifyState();
    }

    public Phone(Phone phone) {
        phoneVector = MatrixUtils.createFieldVector(phone.getEntryArray());
        verifyState();
    }

    public boolean isConsonant() {
        FFE primaryAxis = phoneVector.getEntry(0);
        FFE secondaryAxis = phoneVector.getEntry(1);
        FFE tertiaryAxis = phoneVector.getEntry(2);
        return primaryAxis.signum() != -1 && secondaryAxis.signum() != -1 && tertiaryAxis.signum() != 1;
    }

    public boolean isVowel() {
        FFE primaryAxis = phoneVector.getEntry(0);
        FFE secondaryAxis = phoneVector.getEntry(1);
        FFE tertiaryAxis = phoneVector.getEntry(2);
        return primaryAxis.signum() != 1 && secondaryAxis.signum() != 1 && tertiaryAxis.signum() != 1;
    }

    public boolean hasIllegalState() {
        return !isConsonant() && !isVowel();
    }

    public void verifyState() {
        if (this.hasIllegalState()) {
            throw new IllegalStateException();
        }
    }

    public FFE[] getEntryArray() {
        return phoneVector.toArray();
    }

    @Override
    public String toString() {
        FFE primaryAxis = phoneVector.getEntry(0);
        FFE secondaryAxis = phoneVector.getEntry(1);
        FFE tertiaryAxis = phoneVector.getEntry(2);
        return "P(%s, %s, %s)".formatted(primaryAxis, secondaryAxis, tertiaryAxis);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof Phone) {
            FFE primaryAxis = phoneVector.getEntry(0);
            FFE secondaryAxis = phoneVector.getEntry(1);
            FFE tertiaryAxis = phoneVector.getEntry(2);

            FFE primaryAxisOther = ((Phone) other).phoneVector.getEntry(0);
            FFE secondaryAxisOther = ((Phone) other).phoneVector.getEntry(1);
            FFE tertiaryAxisOther = ((Phone) other).phoneVector.getEntry(2);

            return primaryAxis.equals(primaryAxisOther) && secondaryAxis.equals(secondaryAxisOther)
                    && tertiaryAxis.equals(tertiaryAxisOther);
        }

        return false;
    }
}
