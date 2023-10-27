package mapping;

import soundsystem.Phone;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class SigmaMapper {
    private final Orthography orthography;
    private final PhoneticAlphabet phoneticAlphabet;

    public SigmaMapper(Orthography orthography, PhoneticAlphabet phoneticAlphabet) {
        this.orthography = orthography;
        this.phoneticAlphabet = phoneticAlphabet;
    }

    public List<IPASymbol> mapToSymbolSequence(String sequence) {
        StringBuilder editingSequence = new StringBuilder(sequence);
        Set<String> representativeSet = orthography.getRepresentativeSet();
        List<String> biggestToSmallesRepresentativeList = representativeSet.stream().
                sorted(Comparator.comparing(String::length, Comparator.reverseOrder())).toList();
        List<IPASymbol> symbolSequence = new ArrayList<>();
        for (int i = 0; i < sequence.length(); i++) {
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
                if (correspondingPhone != null) {
                    phoneSequence.add(phoneticAlphabet.mapToPhone(symbol));
                } else {
                    phoneSequence.add(IPA.UNDEFINED_PHONE);
                }
            }
        }
        return phoneSequence;
    }

    public List<Phone> mapDirectlyToPhoneSequence(String sequence) {
        return mapToPhoneSequence(mapToSymbolSequence(sequence));
    }

    //TODO: reverse functions
}
