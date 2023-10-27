package soundsystem;

import mapping.IPASymbol;

public class PlaceholderPhone extends Phone {
    private final String typeDescription;

    public PlaceholderPhone(IPASymbol symbol) {
        super(0, 0, 0, 0, 0, 0);
        switch (symbol) {
            case EMPTY_SYMBOL -> typeDescription = "EMPTY_PHONE";
            case UNKNOWN_SYMBOL -> typeDescription = "UNKNOWN_PHONE";
            default -> typeDescription = "UNDEFINED_PLACEHOLDER";
        }
    }

    public PlaceholderPhone(String typeDescription) {
        super(0, 0, 0, 0, 0, 0);
        this.typeDescription = typeDescription;
    }

    @Override
    public boolean hasIllegalState() {
        return false;
    }

    @Override
    public String toString() {
        return typeDescription;
    }
}
