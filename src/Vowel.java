import org.apache.commons.numbers.fraction.Fraction;

public class Vowel extends Phone {
    public Vowel(Fraction primary, Fraction secondary, Fraction tertiary) {
        super(primary, secondary, tertiary);
    }

    @Override
    public boolean hasIllegalState() {
        return !isVowel();
    }
}
