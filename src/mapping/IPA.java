package mapping;

import soundsystem.Phone;
import soundsystem.PlaceholderPhone;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IPA implements PhoneticAlphabet {
//    public static final Phone EMPTY_PHONE = new PlaceholderPhone(IPASymbol.EMPTY_SYMBOL);
    public static final Phone UNKNOWN_PHONE = new PlaceholderPhone(IPASymbol.UNKNOWN_SYMBOL);
    public static final Phone UNDEFINED_PHONE = new PlaceholderPhone("UNDEFINED_PHONE");
    public static final Phone A = new Phone(-2, 3, 0, 1, 0, 1);
    public static final Phone E = new Phone(-7, 8, -2, 3, 0, 1);
    public static final Phone I = new Phone(-1, 1, -1, 1, 0, 1);
    public static final Phone O = new Phone(0, 1, -2, 3, -1, 1);
    public static final Phone U = new Phone(0, 1, -1, 1, -1, 1);

    private final Map<IPASymbol, Phone> symbolToPhoneMap = new HashMap<>();
    private final Map<Phone, IPASymbol> phoneToSymbolMap = new HashMap<>();

    //TODO: consonants and other vowels; then subdivide in consonant classes cet.

    public IPA() {
//        symbolToPhoneMap.put(IPASymbol.EMPTY_SYMBOL, EMPTY_PHONE);
        symbolToPhoneMap.put(IPASymbol.UNKNOWN_SYMBOL, UNKNOWN_PHONE);
        symbolToPhoneMap.put(IPASymbol.A, A);
        symbolToPhoneMap.put(IPASymbol.E, E);
        symbolToPhoneMap.put(IPASymbol.I, I);
        symbolToPhoneMap.put(IPASymbol.O, O);
        symbolToPhoneMap.put(IPASymbol.U, U);


        for (Map.Entry<IPASymbol, Phone> stringPhoneEntry : symbolToPhoneMap.entrySet()) {
            phoneToSymbolMap.put(stringPhoneEntry.getValue(), stringPhoneEntry.getKey());
        }
    }

    @Override
    public Phone mapToPhone(IPASymbol symbol) {
        return symbolToPhoneMap.get(symbol);
    }

    @Override
    public IPASymbol mapToSymbol(Phone phone) {
        return phoneToSymbolMap.get(phone);
    }

    @Override
    public Set<IPASymbol> getSymbolSet() {
        return Collections.unmodifiableSet(symbolToPhoneMap.keySet());
    }

    @Override
    public Set<Phone> getPhoneSet() {
        return Collections.unmodifiableSet(phoneToSymbolMap.keySet());
    }
}
