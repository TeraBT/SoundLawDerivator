package Parsing;

import SoundSystem.Phone;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class PhoneParser {
    LatinSymbolMapper latinSymbolMapper;

    public PhoneParser(LatinSymbolMapper latinSymbolMapper) {
        this.latinSymbolMapper = latinSymbolMapper;
    }

    public List<Phone> parse(String text) {
        String smallCapsText = text.toLowerCase();
        String onlySymbolsText = smallCapsText.replaceAll("[^a-zA-Z]", "");
        List<Phone> phoneSequence= new ArrayList<>();
        for (int i = 0; i < onlySymbolsText.length(); i++) {
            phoneSequence.add(null);
        }
        Set<String> symbolSet = latinSymbolMapper.symbolToPhoneMap.keySet();
        List<String> biggestToSmallestSymbolList = symbolSet.stream().sorted(Comparator.reverseOrder()).toList();
        for (String symbol : biggestToSmallestSymbolList) {
            int currentSequencePosition = 0;
            while (true) {
                currentSequencePosition = onlySymbolsText.indexOf(symbol, currentSequencePosition);
                if (currentSequencePosition != -1) {
                    Phone phone = new Phone(latinSymbolMapper.mapToPhone(symbol));
                    phoneSequence.add(currentSequencePosition++, phone);
                } else {
                    break;
                }
            }
        }
        return phoneSequence;
    }
}
