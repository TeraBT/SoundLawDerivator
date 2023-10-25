public class Consonant extends Phone {
    public Consonant(FFE primary, FFE secondary, FFE tertiary) {
        super(primary, secondary, tertiary);
    }

    @Override
    public boolean hasIllegalState() {
        return !isConsonant();
    }

    @Override
    public String toString() {
        FFE primaryAxis = phoneVector.getEntry(0);
        FFE secondaryAxis = phoneVector.getEntry(1);
        FFE tertiaryAxis = phoneVector.getEntry(2);
        return "C(%s, %s, %s)".formatted(primaryAxis, secondaryAxis, tertiaryAxis);
    }
}
