import org.apache.commons.math4.legacy.linear.FieldMatrix;
import org.apache.commons.math4.legacy.linear.MatrixUtils;

public class SoundLawMatrix {
    FieldMatrix<FFE> soundLawMatrix;

    public SoundLawMatrix(FFE[][] matrixEntries) {
        soundLawMatrix = MatrixUtils.createFieldMatrix(matrixEntries);
    }

    public static SoundLawMatrix of(FFE ffe0, FFE ffe1, FFE ffe2, FFE ffe3, FFE ffe4,
                                    FFE ffe5, FFE ffe6, FFE ffe7, FFE ffe8) {
        return new SoundLawMatrix(new FFE[][]
                {{FFE.of(ffe0), FFE.of(ffe1), FFE.of(ffe2)},
                        {FFE.of(ffe3), FFE.of(ffe4), FFE.of(ffe5)},
                        {FFE.of(ffe6), FFE.of(ffe7), FFE.of(ffe8)}});
    }
    
    public static SoundLawMatrix of(
            int num0, int den0, int num1, int den1, int num2, int den2,
            int num3, int den3, int num4, int den4, int num5, int den5,
            int num6, int den6, int num7, int den7, int num8, int den8) {
        return new SoundLawMatrix(new FFE[][]
                {{FFE.of(num0, den0), FFE.of(num1, den1), FFE.of(num2, den2)},
                        {FFE.of(num3, den3), FFE.of(num4, den4), FFE.of(num5, den5)},
                        {FFE.of(num6, den6), FFE.of(num7, den7), FFE.of(num8, den8)}});
    }

    public static SoundLawMatrix getIdentityMatrix() {
        return SoundLawMatrix.of(
                1, 1, 0, 1, 0, 1,
                0, 1, 1, 1, 0, 1,
                0, 1, 0, 1, 1, 1);
    }

    public static SoundLawMatrix getDiagonalMatrix(FFE ffe1, FFE ffe2, FFE ffe3) {
        SoundLawMatrix soundLawMatrix = getZeroMatrix();
        soundLawMatrix.setEntry(0, 0, ffe1);
        soundLawMatrix.setEntry(1, 1, ffe2);
        soundLawMatrix.setEntry(2, 2, ffe3);
        return soundLawMatrix;
    }

    public static SoundLawMatrix getDiagonalMatrix(
            int num0, int den0, int num1, int den1, int num2, int den2) {
        SoundLawMatrix soundLawMatrix = getZeroMatrix();
        soundLawMatrix.setEntry(0, 0, num0, den0);
        soundLawMatrix.setEntry(1, 1, num1, den1);
        soundLawMatrix.setEntry(2, 2, num2, num2);
        return soundLawMatrix;
    }

    public static SoundLawMatrix getUnitaryMatrix(FFE ffe) {
        return new SoundLawMatrix(new FFE[][] {{ffe, ffe, ffe}, {ffe, ffe, ffe}, {ffe, ffe, ffe}});
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
        SoundLawMatrix soundLawMatrix = getZeroMatrix();
        for (int i = 0; i < 3; i++) {
            soundLawMatrix.setEntry(i, i, toPhone.getEntryArray()[i].divide(fromPhone.getEntryArray()[i]));
        }
        return soundLawMatrix;
    }

    public Phone applySoundLaw(Phone phone) {
        return new Phone(soundLawMatrix.multiply(MatrixUtils.createColumnFieldMatrix(
                phone.getEntryArray())).getColumnVector(0));
    }

    private FFE getEntry(int i, int j) {
        return FFE.of(soundLawMatrix.getEntry(i, j));
    }

    private void setEntry(int i, int j, FFE ffe) {
        soundLawMatrix.setEntry(i, j, ffe);
    }

    private void setEntry(int i, int j, int num, int den) {
        soundLawMatrix.setEntry(i, j, FFE.of(num, den));
    }

    @Override
    public String toString() {
        StringBuilder matrixString = new StringBuilder("SL( ");
        for (int i = 0; i < soundLawMatrix.getRowDimension(); i++) {
            for (int j = 0; j < soundLawMatrix.getColumnDimension(); j++) {
                matrixString.append(soundLawMatrix.getEntry(i, j)).append(' ');
            }
            matrixString.replace(matrixString.length() - 1, matrixString.length(), ", ");
        }
        matrixString.replace(matrixString.length() - 1, matrixString.length(), " )");
        return matrixString.toString();
    }
}
