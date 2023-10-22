public class VowelQualityVector {
    private double backness;
    private double openness;
    private double roundedness;
    private double length;
    private double nasalization;

    /**
     *
     * @param backness 0: frontal; 1;
     * @param openness
     * @param roundedness
     * @param length
     * @param nasalization
     */
    public VowelQualityVector(double backness, double openness, double roundedness, double length, double nasalization) {
        if (roundedness < 0 || roundedness > 1 || nasalization < 0 || nasalization > 0) {
            throw new IllegalArgumentException();
        }
        this.backness = backness;
        this.openness = openness;
        this.roundedness = roundedness;
        this.length = length;
        this.nasalization = nasalization;
    }

    public double get(int index) {
        switch (index) {
            case 0 -> {
                return getBackness();
            }
            case 1 -> {
                return getOpenness();
            }
            case 2 -> {
                return getRoundedness();
            }
            case 3 -> {
                return getLength();
            }
            case 4 -> {
                return getNasalization();
            }
            default -> throw new IndexOutOfBoundsException();
        }
    }

    public void set(int index, double value) {
        switch (index) {
            case 0 -> {
                setBackness(value);
            }
            case 1 -> {
                setOpenness(value);
            }
            case 2 -> {
                setRoundedness(value);
            }
            case 3 -> {
                setLength(value);
            }
            case 4 -> {
                setNasalization(value);
            }
            default -> throw new IndexOutOfBoundsException();
        }
    }

    public double getBackness() {
        return backness;
    }

    public void setBackness(double backness) {
        this.backness = backness;
    }

    public double getOpenness() {
        return openness;
    }

    public void setOpenness(double openness) {
        this.openness = openness;
    }

    public double getRoundedness() {
        return roundedness;
    }

    public void setRoundedness(double roundedness) {
        if (roundedness < 0 || roundedness > 1) {
            throw new IllegalArgumentException();
        }
        this.roundedness = roundedness;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getNasalization() {
        return nasalization;
    }

    public void setNasalization(double nasalization) {
        if (nasalization < 0 || nasalization > 0) {
            throw new IllegalArgumentException();
        }
        this.nasalization = nasalization;
    }
}
