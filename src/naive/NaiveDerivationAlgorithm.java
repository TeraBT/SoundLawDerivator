package naive;

import mapping.IPASymbol;
import org.apache.commons.math4.legacy.core.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class NaiveDerivationAlgorithm {

    public static List<List<List<IPASymbol>>> deriveSoundLawCandidatesAsIPASymbols(List<Pair<List<IPASymbol>, List<IPASymbol>>> optimalAlignmentList) {

        List<List<List<IPASymbol>>> soundLawList = new ArrayList<>();
        for (Pair<List<IPASymbol>, List<IPASymbol>> match : optimalAlignmentList) {
            List<IPASymbol> first = match.getFirst();
            List<IPASymbol> second = match.getSecond();
            List<List<IPASymbol>> soundLawListOfMatch = new ArrayList<>();

            for (int i = 0; i < first.size(); i++) {
                List<IPASymbol> soundLaw = new ArrayList<>();
                if (first.get(i) != second.get(i)) {
                    soundLaw.add(first.get(i));
                    soundLaw.add(second.get(i));

                    if (first.size() == 1) {
                        soundLaw.add(IPASymbol.EMPTY_SYMBOL);
                        soundLaw.add(IPASymbol.EMPTY_SYMBOL);
                    }

                    if (i == 0) {
                        soundLaw.add(IPASymbol.EMPTY_SYMBOL);
                        soundLaw.add(first.get(i + 1));
                    }

                    if (i == first.size() - 1) {
                        soundLaw.add(first.get(i - 1));
                        soundLaw.add(IPASymbol.EMPTY_SYMBOL);
                    }
                    soundLawListOfMatch.add(soundLaw);
                }
            }
            soundLawList.add(soundLawListOfMatch);
        }

        return soundLawList;
    }

    public static List<List<List<Character>>> deriveSoundLawCandidatesAsCharacters(List<Pair<String, String>> optimalAlignmentList) {

        List<List<List<Character>>> soundLawList = new ArrayList<>();
        for (Pair<String, String> match : optimalAlignmentList) {
            String first = match.getFirst();
            String second = match.getSecond();
            List<List<Character>> soundLawListOfMatch = new ArrayList<>();

            for (int i = 0; i < first.length(); i++) {
                List<Character> soundLaw = new ArrayList<>();
                if (first.charAt(i) != second.charAt(i)) {
                    soundLaw.add(first.charAt(i));
                    soundLaw.add(second.charAt(i));

                    if (first.length() == 1) {
                        soundLaw.add('#');
                        soundLaw.add('#');
                    }

                    if (i == 0) {
                        soundLaw.add('#');
                        soundLaw.add(first.charAt(i + 1));
                    }

                    if (i == first.length() - 1) {
                        soundLaw.add(first.charAt(i - 1));
                        soundLaw.add('#');
                    }

                    if (i != 0 && i != first.length() - 1) {
                        soundLaw.add(first.charAt(i - 1));
                        soundLaw.add(first.charAt(i + 1));
                    }

                    soundLawListOfMatch.add(soundLaw);
                }
            }
            soundLawList.add(soundLawListOfMatch);
        }

        return soundLawList;
    }

    public static List<Map.Entry<List<IPASymbol>, Long>> deriveSoundLawsAsIPASymbols(List<List<List<IPASymbol>>> soundLawListList) {
        List<List<IPASymbol>> soundLawList = soundLawListList.stream().flatMap(List::stream).collect(Collectors.toCollection(ArrayList::new));
        List<List<IPASymbol>> distinctSoundLawList = soundLawList.stream().distinct().collect(Collectors.toCollection(ArrayList::new));

        Map<List<IPASymbol>, Long> soundLawFrequencyMap = new HashMap<>();
        for (List<IPASymbol> soundLaw : distinctSoundLawList) {
            long count = soundLawList.stream().filter(sl -> sl.equals(soundLaw)).count();
            soundLawFrequencyMap.put(soundLaw, count);
        }

        return soundLawFrequencyMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).toList();
    }

    public static List<Map.Entry<List<Character>, Long>> deriveSoundLawsAsCharacters(List<List<List<Character>>> soundLawListList) {
        List<List<Character>> soundLawList = soundLawListList.stream().flatMap(List::stream).collect(Collectors.toCollection(ArrayList::new));
        List<List<Character>> distinctSoundLawList = soundLawList.stream().distinct().collect(Collectors.toCollection(ArrayList::new));

        Map<List<Character>, Long> soundLawFrequencyMap = new HashMap<>();
        for (List<Character> soundLaw : distinctSoundLawList) {
            long count = soundLawList.stream().filter(sl -> sl.equals(soundLaw)).count();
            soundLawFrequencyMap.put(soundLaw, count);
        }

        return soundLawFrequencyMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).toList();
    }

//    private boolean checkSoundLawEquality(List<Character> soundLaw1, List<Character> soundLaw2) {
//        return soundLaw1.get(0) == soundLaw2.get(0) && soundLaw1.get(1) == soundLaw2.get(1)
//                && soundLaw1.get(2) == soundLaw2.get(2) && soundLaw1.get(3) == soundLaw2.get(3);
//    }
}
