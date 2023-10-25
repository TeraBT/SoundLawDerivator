public class Phone {
    FFE primary;
    FFE secondary;
    FFE tertiary;

    public Phone(FFE primary, FFE secondary, FFE tertiary) {
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

    public FFE getPrimary() {
        return primary;
    }

    public void setPrimary(FFE primary) {
        this.primary = primary;
        verifyState();
    }

    public FFE getSecondary() {
        return secondary;
    }

    public void setSecondary(FFE secondary) {
        this.secondary = secondary;
        verifyState();
    }

    public FFE getTertiary() {
        return tertiary;
    }

    public void setTertiary(FFE tertiary) {
        this.tertiary = tertiary;
        verifyState();
    }
}
