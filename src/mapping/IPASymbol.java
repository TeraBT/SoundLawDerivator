package mapping;

public enum IPASymbol {
    // special symbols:
    EMPTY_SYMBOL, UNKNOWN_SYMBOL, UNDEFINED_SYMBOL,

    // standard vowels:
    A, E, I, O, U,

    // -------consonants---------:

    // plosives:
    VOICELESS_BILABIAL_PLOSIVE,
    VOICED_BILABIAL_PLOSIVE,
    VOICELESS_ALVEOLAR_PLOSIVE,
    VOICED_ALVEOLAR_PLOSIVE,
    VOICELESS_VELAR_PLOSIVE,
    VOICED_VELAR_PLOSIVE,

    VOICELESS_VELAR_PLOSIVE_LABIALIZED,

    // nasals:
    BILABIAL_NASAL,
    ALVEOLAR_NASAL,
    VELAR_NASAL,

    // trills:
    ALVEOLAR_TRILL,

    // fricatives:
//    VOICELESS_BILABIAL_FRICATIVE,
//    VOICED_BILABIAL_FRICATIVE,
    VOICELESS_LABIODENTAL_FRICATIVE,
    VOICED_LABIODENTAL_FRICATIVE,
    VOICELESS_ALVEOLAR_FRICATIVE,
    VOICED_ALVEOLAR_FRICATIVE,
    VOICELESS_POSTALVEOLAR_FRICATIVE,
    VOICED_POSTALVEOLAR_FRICATIVE,
    VOICELESS_GLOTTAL_FRICATIVE,

    // approximants:
    PALATAL_APPROXIMANT,

    // lateral approximants:
    ALVEOLAR_LATERAL_APPROXIMANT

}
