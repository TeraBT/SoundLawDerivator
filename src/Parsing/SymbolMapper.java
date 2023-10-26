package Parsing;

import SoundSystem.Phone;

public interface SymbolMapper {
    Phone mapToPhone(String symbol);
    String mapToSymbol(Phone phone);
}
