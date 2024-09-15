package mapping;

import soundsystem.Phone;
import soundsystem.PlaceholderPhone;

import java.util.*;

public class IPA implements PhoneticAlphabet {
    public static final Phone EMPTY_PHONE = new PlaceholderPhone(IPASymbol.EMPTY_SYMBOL);
    public static final Phone UNKNOWN_PHONE = new PlaceholderPhone(IPASymbol.UNKNOWN_SYMBOL);
    public static final Phone UNDEFINED_PHONE = new PlaceholderPhone("UNDEFINED_PHONE");

    public static final Phone A = new Phone(-2, 3, 0, 1, 0, 1);
    public static final Phone E = new Phone(-7, 8, -2, 3, 0, 1);
    public static final Phone I = new Phone(-1, 1, -1, 1, 0, 1);
    public static final Phone O = new Phone(0, 1, -2, 3, -1, 1);
    public static final Phone U = new Phone(0, 1, -1, 1, -1, 1);

    public static final Phone VOICELESS_BILABIAL_PLOSIVE = new Phone(0, 10, 1, 10, 0, 1);
    public static final Phone VOICED_BILABIAL_PLOSIVE = new Phone(0, 10, 1, 10, 1, 1);
    public static final Phone VOICELESS_ALVEOLAR_PLOSIVE = new Phone(3, 10, 1, 10, 0, 1);
    public static final Phone VOICED_ALVEOLAR_PLOSIVE = new Phone(3, 10, 1, 10, 1, 1);
    public static final Phone VOICELESS_VELAR_PLOSIVE = new Phone(7, 10, 1, 10, 0, 1);
    public static final Phone VOICED_VELAR_PLOSIVE = new Phone(7, 10, 1, 10, 0, 1);

    // TODO: How to model labialization?
    public static final Phone VOICELESS_VELAR_PLOSIVE_LABIALIZED = new Phone(7, 10, 1, 10, 0, 1);

    public static final Phone BILABIAL_NASAL = new Phone(0, 10, 2, 10, 1, 1);
    public static final Phone ALVEOLAR_NASAL = new Phone(3, 10, 2, 10, 1, 1);
    public static final Phone VELAR_NASAL = new Phone(7, 10, 2, 10, 1, 1);

    public static final Phone ALVEOLAR_TRILL = new Phone(3, 10, 3, 10, 1, 1);

    public static final Phone VOICELESS_LABIODENTAL_FRICATIVE = new Phone(1, 10, 5, 10, 0, 1);
    public static final Phone VOICED_LABIODENTAL_FRICATIVE = new Phone(1, 10, 5, 10, 1, 1);
    public static final Phone VOICELESS_ALVEOLAR_FRICATIVE = new Phone(3, 10, 5, 10, 0, 1);
    public static final Phone VOICED_ALVEOLAR_FRICATIVE = new Phone(3, 10, 5, 10, 0, 1);
    public static final Phone VOICELESS_POSTALVEOLAR_FRICATIVE = new Phone(4, 10, 5, 10, 0, 1);
    public static final Phone VOICED_POSTALVEOLAR_FRICATIVE = new Phone(4, 10, 5, 10, 1, 1);
    public static final Phone VOICELESS_GLOTTAL_FRICATIVE = new Phone(1, 1, 5, 10, 0, 1);

    public static final Phone PALATAL_APPROXIMANT = new Phone(6, 10, 7, 10, 1, 1);
    // TODO: How to model labialization/velarization?
    public static final Phone VOICED_LABIAL_VELAR_APPROXIMANT = new Phone(0, 10, 7, 10, 1, 1);

    public static final Phone ALVEOLAR_LATERAL_APPROXIMANT = new Phone(3, 10, 8, 10, 1, 1);




    private final Map<IPASymbol, Phone> symbolToPhoneMap = new HashMap<>();
    private final Map<Phone, IPASymbol> phoneToSymbolMap = new HashMap<>();

    //TODO: consonants and other vowels; then subdivide in consonant classes cet.

    public IPA() {
        symbolToPhoneMap.put(IPASymbol.EMPTY_SYMBOL, EMPTY_PHONE);
        symbolToPhoneMap.put(IPASymbol.UNKNOWN_SYMBOL, UNKNOWN_PHONE);
        symbolToPhoneMap.put(IPASymbol.UNDEFINED_SYMBOL, UNDEFINED_PHONE);

        symbolToPhoneMap.put(IPASymbol.A, A);
        symbolToPhoneMap.put(IPASymbol.E, E);
        symbolToPhoneMap.put(IPASymbol.I, I);
        symbolToPhoneMap.put(IPASymbol.O, O);
        symbolToPhoneMap.put(IPASymbol.U, U);

        symbolToPhoneMap.put(IPASymbol.VOICELESS_BILABIAL_PLOSIVE, VOICELESS_BILABIAL_PLOSIVE);
        symbolToPhoneMap.put(IPASymbol.VOICED_BILABIAL_PLOSIVE, VOICED_BILABIAL_PLOSIVE);
        symbolToPhoneMap.put(IPASymbol.VOICELESS_ALVEOLAR_PLOSIVE, VOICELESS_ALVEOLAR_PLOSIVE);
        symbolToPhoneMap.put(IPASymbol.VOICED_ALVEOLAR_PLOSIVE, VOICED_ALVEOLAR_PLOSIVE);
        symbolToPhoneMap.put(IPASymbol.VOICELESS_VELAR_PLOSIVE, VOICELESS_VELAR_PLOSIVE);
        symbolToPhoneMap.put(IPASymbol.VOICED_VELAR_PLOSIVE, VOICED_VELAR_PLOSIVE);

        symbolToPhoneMap.put(IPASymbol.VOICELESS_VELAR_PLOSIVE_LABIALIZED, VOICELESS_VELAR_PLOSIVE_LABIALIZED);

        symbolToPhoneMap.put(IPASymbol.BILABIAL_NASAL, BILABIAL_NASAL);
        symbolToPhoneMap.put(IPASymbol.ALVEOLAR_NASAL, ALVEOLAR_NASAL);
        symbolToPhoneMap.put(IPASymbol.VELAR_NASAL, VELAR_NASAL);

        symbolToPhoneMap.put(IPASymbol.ALVEOLAR_TRILL, ALVEOLAR_TRILL);

        symbolToPhoneMap.put(IPASymbol.VOICELESS_LABIODENTAL_FRICATIVE, VOICELESS_LABIODENTAL_FRICATIVE);
        symbolToPhoneMap.put(IPASymbol.VOICED_LABIODENTAL_FRICATIVE, VOICED_LABIODENTAL_FRICATIVE);
        symbolToPhoneMap.put(IPASymbol.VOICELESS_ALVEOLAR_FRICATIVE, VOICELESS_ALVEOLAR_FRICATIVE);
        symbolToPhoneMap.put(IPASymbol.VOICED_ALVEOLAR_FRICATIVE, VOICED_ALVEOLAR_FRICATIVE);

        symbolToPhoneMap.put(IPASymbol.VOICELESS_POSTALVEOLAR_FRICATIVE, VOICELESS_POSTALVEOLAR_FRICATIVE);

        symbolToPhoneMap.put(IPASymbol.VOICED_POSTALVEOLAR_FRICATIVE, VOICED_POSTALVEOLAR_FRICATIVE);
        symbolToPhoneMap.put(IPASymbol.VOICELESS_GLOTTAL_FRICATIVE, VOICELESS_GLOTTAL_FRICATIVE);

        symbolToPhoneMap.put(IPASymbol.PALATAL_APPROXIMANT, PALATAL_APPROXIMANT);

        symbolToPhoneMap.put(IPASymbol.ALVEOLAR_LATERAL_APPROXIMANT, ALVEOLAR_LATERAL_APPROXIMANT);



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
