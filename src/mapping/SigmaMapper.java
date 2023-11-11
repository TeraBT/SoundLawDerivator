package mapping;

import soundsystem.Phone;

import java.util.*;

public class SigmaMapper {
    private final Orthography orthography;
    private final PhoneticAlphabet phoneticAlphabet;

    public SigmaMapper(Orthography orthography, PhoneticAlphabet phoneticAlphabet) {
        this.orthography = orthography;
        this.phoneticAlphabet = phoneticAlphabet;
    }

    public List<List<IPASymbol>> mapToSymbolSequenceList(String representativeSequence) {
        StringBuilder editingSequence = new StringBuilder(representativeSequence);
        Set<String> representativeSet = orthography.getRepresentativeSet();
        List<String> biggestToSmallesRepresentativeList = representativeSet.stream().
                sorted(Comparator.comparing(String::length, Comparator.reverseOrder())).toList();
        List<List<IPASymbol>> symbolSequenceList = new ArrayList<>();
        for (int i = 0; i < representativeSequence.length(); i++) {
            symbolSequenceList.add(List.of(IPASymbol.UNKNOWN_SYMBOL));
        }

        for (String representative : biggestToSmallesRepresentativeList) {
            int currentSequencePosition = 0;
            while (true) {
                currentSequencePosition = editingSequence.indexOf(representative, currentSequencePosition);
                if (currentSequencePosition != -1) {
                    symbolSequenceList.set(currentSequencePosition, orthography.mapToSymbolList(representative));
                    editingSequence.replace(currentSequencePosition, currentSequencePosition + 1, "#");
                    for (int i = currentSequencePosition + 1; i < currentSequencePosition + representative.length(); i++) {
                        symbolSequenceList.set(i, List.of(IPASymbol.EMPTY_SYMBOL));
                        editingSequence.replace(i, i + 1, "#");
                    }
                    currentSequencePosition = currentSequencePosition + representative.length();
                } else {
                    break;
                }
            }
        }
        return symbolSequenceList;
    }

    public List<IPASymbol> mapToFlatSymbolSequence(String representativeSequence) {
        return mapToSymbolSequenceList(representativeSequence).stream().flatMap(Collection::stream).toList();
    }

    public List<Phone> mapToPhoneSequence(List<List<IPASymbol>> symbolListSequence) {
        List<Phone> phoneSequence = new ArrayList<>();
        for (List<IPASymbol> symbolList : symbolListSequence) {
            for (IPASymbol symbol : symbolList) {
                if (symbol != IPASymbol.EMPTY_SYMBOL) {
                    Phone correspondingPhone = phoneticAlphabet.mapToPhone(symbol);
                    phoneSequence.add(Objects.requireNonNullElse(correspondingPhone, IPA.UNDEFINED_PHONE));
                }
            }
        }
        return phoneSequence;
    }

    public List<Phone> mapDirectlyToPhoneSequence(String sequence) {
        return mapToPhoneSequence(mapToSymbolSequenceList(sequence));
    }

    public List<IPASymbol> mapBackToSymbolListSequence(List<Phone> phoneSequence) { //TODO: fit for new list of lists imp.
//        List<IPASymbol> symbolSequence = new ArrayList<>();
//        for (Phone phone : phoneSequence) {
//            IPASymbol correspondingSymbol = phoneticAlphabet.mapToSymbol(phone);
//            symbolSequence.add(Objects.requireNonNullElse(correspondingSymbol, IPASymbol.UNDEFINED_SYMBOL));
//        }
//        return symbolSequence;
        return null;
    }

    //TODO representative to symbol is not injective and s to r is right-unique !!!change behavior

    //TODO ALSO: Latin rep. "iu" needs to be mapped to "j, u", not "i, u" cet. Maybe tuple of symbols or automatic
    //TODO: remapping of i if it is followed by u
    //TODO:
//    public String mapBackToRepresentativeSequence(List<IPASymbol> symbolSequence) { //TODO: change for new list of lists imp.
//        StringBuilder representativeSequence = new StringBuilder();
//        for (IPASymbol symbol : symbolSequence) {
//            String correspondingRepresentative = orthography.mapToRepresentative(symbol);
//            if (symbol.equals(IPASymbol.UNKNOWN_SYMBOL)) {
//                representativeSequence.append('#');
//            } else if (correspondingRepresentative == null) {
//                representativeSequence.append('*');
//            } else {
//                representativeSequence.append(correspondingRepresentative);
//            }
//        }
//        return representativeSequence.toString();
//    }
//
//    public String mapBackDirectlyToRepresentativeSequence(List<Phone> phoneSequence) {
//        return mapBackToRepresentativeSequence(mapBackToSymbolSequence(phoneSequence));
//    }
}
