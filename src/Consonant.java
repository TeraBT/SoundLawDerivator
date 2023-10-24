import org.apache.commons.numbers.fraction.Fraction;

public class Consonant extends Phone {
    public Consonant(Fraction primary, Fraction secondary, Fraction tertiary) {
        super(primary, secondary, tertiary);
    }

    @Override
    public boolean hasIllegalState() {
        return !isConsonant();
    }
}
