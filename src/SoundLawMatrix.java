import org.apache.commons.math4.legacy.linear.FieldMatrix;
import org.apache.commons.math4.legacy.linear.MatrixUtils;

import java.util.Arrays;

public class SoundLawMatrix {
    FieldMatrix<FFE> soundLawMatrix;

    public SoundLawMatrix(FFE[][] matrixEntries) {
        soundLawMatrix = MatrixUtils.createFieldMatrix(matrixEntries);
    }

    public static SoundLawMatrix of(
            int num1, int den1, int num2, int den2, int num3, int den3,
            int num4, int den4, int num5, int den5, int num6, int den6,
            int num7, int den7, int num8, int den8, int num9, int den9) {
        return new SoundLawMatrix(new FFE[][]
                {{FFE.of(num1, den1), FFE.of(num2, den2), FFE.of(num3, den3)},
                {FFE.of(num4, den4), FFE.of(num5, den5), FFE.of(num6, den6)},
                {FFE.of(num7, den7), FFE.of(num8, den8), FFE.of(num9, den9)}});
    }

    public static SoundLawMatrix getIdentityMatrix() {
        return SoundLawMatrix.of(
                1, 1, 0, 1, 0, 1,
                0, 1, 1, 1, 0, 1,
                0, 1, 0, 1, 1, 1);
    }

    public static SoundLawMatrix getUnitaryMatrix(int num, int den) {
        return new SoundLawMatrix(new FFE[][]
                {{FFE.of(num, den), FFE.of(num, den), FFE.of(num, den)},
                {FFE.of(num, den), FFE.of(num, den), FFE.of(num, den)},
                {FFE.of(num, den), FFE.of(num, den), FFE.of(num, den)}});
    }

    public static SoundLawMatrix getOneMatrix() {
        return getUnitaryMatrix(1, 1);
    }
    public static SoundLawMatrix getZeroMatrix() {
        return getUnitaryMatrix(0, 1);
    }

    public static SoundLawMatrix generateSoundLawMatrixOf(Phone fromPhone, Phone toPhone) {
        return null; //TODO
    }

    public static Phone applySoundLaw(Phone phone) {
        return null; //TODO
    }

    private FFE getEntry(int i, int j) {
        return soundLawMatrix.getEntry(i, j);
    }

    private void setEntry(int i, int j, FFE ffe) {
        soundLawMatrix.setEntry(i, j, ffe);
    }

    @Override
    public String toString() {
        StringBuilder matrixString = new StringBuilder("SL( ");
        for (int i = 0; i < soundLawMatrix.getRowDimension(); ++i) {
            for (int j = 0; j < soundLawMatrix.getColumnDimension(); ++j) {
                matrixString.append(soundLawMatrix.getEntry(i, j)).append(' ');
            }
            matrixString.replace(matrixString.length() - 1, matrixString.length(), ", ");
        }
        matrixString.replace(matrixString.length() - 1, matrixString.length(), " )");
        return matrixString.toString();
    }
}
