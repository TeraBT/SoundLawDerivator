import org.apache.commons.numbers.fraction.Fraction;

public class Phone {
    Fraction primary;
    Fraction secondary;
    Fraction tertiary;

    public Phone(Fraction primary, Fraction secondary, Fraction tertiary) {
        this.primary = primary;
        this.secondary = secondary;
        this.tertiary = tertiary;
        verifyState();
    }

    public boolean isConsonant() {
        return primary.signum() == 1 && secondary.signum() == 1 && tertiary.signum() == 1;
    }
    public boolean isVowel() {
        return primary.signum() == -1 && secondary.signum() == -1 && tertiary.signum() == -1;
    }

    public boolean hasIllegalState() {
        return !isConsonant() && !isVowel();
    }

    public void verifyState() {
        if (this.hasIllegalState()) {
            throw new IllegalStateException();
        }
    }

    public Fraction getPrimary() {
        return primary;
    }

    public void setPrimary(Fraction primary) {
        this.primary = primary;
        verifyState();
    }

    public Fraction getSecondary() {
        return secondary;
    }

    public void setSecondary(Fraction secondary) {
        this.secondary = secondary;
        verifyState();
    }

    public Fraction getTertiary() {
        return tertiary;
    }

    public void setTertiary(Fraction tertiary) {
        this.tertiary = tertiary;
        verifyState();
    }
}
