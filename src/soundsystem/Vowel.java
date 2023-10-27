package soundsystem;

public class Vowel extends Phone {
    public Vowel(FFE primary, FFE secondary, FFE tertiary) {
        super(primary, secondary, tertiary);
    }

    @Override
    public boolean hasIllegalState() {
        return !isVowel();
    }

    @Override
    public String toString() {
        FFE primaryAxis = phoneVector.getEntry(0);
        FFE secondaryAxis = phoneVector.getEntry(1);
        FFE tertiaryAxis = phoneVector.getEntry(2);
        return "V(%s, %s, %s)".formatted(primaryAxis, secondaryAxis, tertiaryAxis);
    }
}
