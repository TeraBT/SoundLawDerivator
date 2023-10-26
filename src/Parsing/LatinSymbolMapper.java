package Parsing;

import SoundSystem.Phone;
import SoundSystem.StandardPhones;

import java.util.HashMap;
import java.util.Map;

public class LatinSymbolMapper implements SymbolMapper {
    Map<String, Phone> symbolToPhoneMap = new HashMap<>();
    Map<Phone, String> phoneToSymbolMap = new HashMap<>();

    public LatinSymbolMapper() {
        symbolToPhoneMap.put("a", StandardPhones.A);
        symbolToPhoneMap.put("e", StandardPhones.E);
        symbolToPhoneMap.put("i", StandardPhones.I);
        symbolToPhoneMap.put("o", StandardPhones.O);
        symbolToPhoneMap.put("u", StandardPhones.U);

        for (Map.Entry<String, Phone> stringPhoneEntry : symbolToPhoneMap.entrySet()) {
            phoneToSymbolMap.put(stringPhoneEntry.getValue(), stringPhoneEntry.getKey());
        }
    }

    @Override
    public Phone mapToPhone(String symbol) {
        return symbolToPhoneMap.get(symbol);
    }

    @Override
    public String mapToSymbol(Phone phone) {
        return phoneToSymbolMap.get(phone);
    }
}
