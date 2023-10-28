package mapping;

import soundsystem.Phone;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class SigmaMapper {
    private final Orthography orthography;
    private final PhoneticAlphabet phoneticAlphabet;

    public SigmaMapper(Orthography orthography, PhoneticAlphabet phoneticAlphabet) {
        this.orthography = orthography;
        this.phoneticAlphabet = phoneticAlphabet;
    }

    public List<IPASymbol> mapToSymbolSequence(String representativeSequence) {
        StringBuilder editingSequence = new StringBuilder(representativeSequence);
        Set<String> representativeSet = orthography.getRepresentativeSet();
        List<String> biggestToSmallesRepresentativeList = representativeSet.stream().
                sorted(Comparator.comparing(String::length, Comparator.reverseOrder())).toList();
        List<IPASymbol> symbolSequence = new ArrayList<>();
        for (int i = 0; i < representativeSequence.length(); i++) {
            symbolSequence.add(IPASymbol.UNKNOWN_SYMBOL);
        }

        for (String representative : biggestToSmallesRepresentativeList) {
            int currentSequencePosition = 0;
            while (true) {
                currentSequencePosition = editingSequence.indexOf(representative, currentSequencePosition);
                if (currentSequencePosition != -1) {
                    symbolSequence.set(currentSequencePosition, orthography.mapToSymbol(representative));
                    editingSequence.replace(currentSequencePosition, currentSequencePosition + 1, "#");
                    for (int i = currentSequencePosition + 1; i < currentSequencePosition + representative.length(); i++) {
                        symbolSequence.set(i, IPASymbol.EMPTY_SYMBOL);
                        editingSequence.replace(i, i + 1, "#");
                    }
                    currentSequencePosition = currentSequencePosition + representative.length();
                } else {
                    break;
                }
            }
        }
        return symbolSequence;
    }

    public List<Phone> mapToPhoneSequence(List<IPASymbol> symbolSequence) {
        List<Phone> phoneSequence = new ArrayList<>();
        for (IPASymbol symbol : symbolSequence) {
            if (symbol != IPASymbol.EMPTY_SYMBOL) {
                Phone correspondingPhone = phoneticAlphabet.mapToPhone(symbol);
                phoneSequence.add(Objects.requireNonNullElse(correspondingPhone, IPA.UNDEFINED_PHONE));
            }
        }
        return phoneSequence;
    }

    public List<Phone> mapDirectlyToPhoneSequence(String sequence) {
        return mapToPhoneSequence(mapToSymbolSequence(sequence));
    }

    public List<IPASymbol> mapBackToSymbolSequence(List<Phone> phoneSequence) {
        List<IPASymbol> symbolSequence = new ArrayList<>();
        for (Phone phone : phoneSequence) {
            IPASymbol correspondingSymbol = phoneticAlphabet.mapToSymbol(phone);
            symbolSequence.add(Objects.requireNonNullElse(correspondingSymbol, IPASymbol.UNDEFINED_SYMBOL));
        }
        return symbolSequence;
    }

    //TODO representative to symbol is not injective and s to r is right-unique !!!change behavior

    //TODO ALSO: Latin rep. "iu" needs to be mapped to "j, u", not "i, u" cet. Maybe tuple of symbols or automatic
    //TODO: remapping of i if it is followed by u
    //TODO:
    public String mapBackToRepresentativeSequence(List<IPASymbol> symbolSequence) {
        StringBuilder representativeSequence = new StringBuilder();
        for (IPASymbol symbol : symbolSequence) {
            String correspondingRepresentative = orthography.mapToRepresentative(symbol);
            if (symbol.equals(IPASymbol.UNKNOWN_SYMBOL)) {
                representativeSequence.append('#');
            } else if (correspondingRepresentative == null) {
                representativeSequence.append('*');
            } else {
                representativeSequence.append(correspondingRepresentative);
            }
        }
        return representativeSequence.toString();
    }

    public String mapBackDirectlyToRepresentativeSequence(List<Phone> phoneSequence) {
        return mapBackToRepresentativeSequence(mapBackToSymbolSequence(phoneSequence));
    }
}
