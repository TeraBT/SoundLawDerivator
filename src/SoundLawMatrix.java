import org.apache.commons.math4.legacy.linear.FieldMatrix;
import org.apache.commons.math4.legacy.linear.MatrixUtils;

public class SoundLawMatrix {
    FieldMatrix<FFE> soundLawMatrix;

    public SoundLawMatrix(FFE[][] matrixEntries) {
        soundLawMatrix = MatrixUtils.createFieldMatrix(matrixEntries);
    }

    public static SoundLawMatrix getUnitaryMatrix(int num, int den) {
        return new SoundLawMatrix(new FFE[][] {{FFE.of(num, den), FFE.of(num, den), FFE.of(num, den)},
                {FFE.of(num, den), FFE.of(num, den), FFE.of(num, den)},
                {FFE.of(num, den), FFE.of(num, den), FFE.of(num, den)}});
    }

    public static SoundLawMatrix getIdentityMatrix() {
        return getUnitaryMatrix(1, 1);
    }
    public static SoundLawMatrix getZeroMatrix() {
        return getUnitaryMatrix(0, 1);
    }

    public static SoundLawMatrix generateTransformerOf(Phone fromPhone, Phone toPhone) {
        return null; //TODO
    }

    public static Phone transformPhone(Phone phone) {
        return null; //TODO
    }

    public FFE getEntry(int i, int j) {
        return soundLawMatrix.getEntry(i, j);
    }

    public void setEntry(int i, int j, FFE ffe) {
        soundLawMatrix.setEntry(i, j, ffe);
    }
}
