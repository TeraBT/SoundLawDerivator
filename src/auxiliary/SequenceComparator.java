package auxiliary;

import mapping.IPASymbol;
import org.apache.commons.math4.legacy.core.Pair;
import soundsystem.Phone;

import java.util.ArrayList;
import java.util.List;

public class SequenceComparator {

    public static List<Pair<String, String>> findBestMatchesInRepresentativeSequence(List<String> tokenListOlder, List<String> tokenListYounger) {


        List<Pair<String, String>> bestMatchList = new ArrayList<>();
        for (String tokenOlder : tokenListOlder) {
            String bestCounterpart = compareOneToN(tokenOlder, tokenListYounger);
            bestMatchList.add(new Pair<>(tokenOlder, bestCounterpart));
        }
        return bestMatchList;
    }

    public static List<Pair<List<IPASymbol>, List<IPASymbol>>> findBestMatchesInSymbolSequence(List<List<IPASymbol>> tokenListOlder, List<List<IPASymbol>> tokenListYounger) {
        List<Pair<List<IPASymbol>, List<IPASymbol>>> bestMatchList = new ArrayList<>();
        for (List<IPASymbol> tokenOlder : tokenListOlder) {
            List<IPASymbol> bestCounterpart = compareOneToN(tokenOlder, tokenListYounger);
            bestMatchList.add(new Pair<>(tokenOlder, bestCounterpart));
        }
        return bestMatchList;
    }

    public static List<Pair<List<Phone>, List<Phone>>> findBestMatchesInPhoneSequence(List<List<Phone>> tokenListOlder, List<List<Phone>> tokenListYounger) {
        List<Pair<List<Phone>, List<Phone>>> bestMatchList = new ArrayList<>();
        for (List<Phone> tokenOlder : tokenListOlder) {
            List<Phone> bestCounterpart = compareOneToNForPhones(tokenOlder, tokenListYounger);
            bestMatchList.add(new Pair<>(tokenOlder, bestCounterpart));
        }
        return bestMatchList;
    }

    public static Pair<String, String> findBestMatchConcurrently(String tokenOlder, List<String> tokenListYounger) {
        String bestCounterpart = compareOneToN(tokenOlder, tokenListYounger);
        return new Pair<>(tokenOlder, bestCounterpart);
    }

    public static Pair<List<IPASymbol>, List<IPASymbol>> findBestMatchConcurrently(List<IPASymbol> tokenOlder, List<List<IPASymbol>> tokenListYounger) {
        List<IPASymbol> bestCounterpart = compareOneToN(tokenOlder, tokenListYounger);
        return new Pair<>(tokenOlder, bestCounterpart);
    }

    public static Pair<List<Phone>, List<Phone>> findBestMatchForPhonesConcurrently(List<Phone> tokenOlder, List<List<Phone>> tokenListYounger) {
        List<Phone> bestCounterpart = compareOneToNForPhones(tokenOlder, tokenListYounger);
        return new Pair<>(tokenOlder, bestCounterpart);
    }

    private static String compareOneToN(String tokenOlder, List<String> tokenListYounger) {
        String bestCounterpart = null;
        double matchDistance;
        double priorMatchDistance = 0;

        for (String currentTokenYounger : tokenListYounger) {

            matchDistance = LevenshteinDistance.calculateSimilarityScore(tokenOlder, currentTokenYounger);
            if (matchDistance > priorMatchDistance) {
                priorMatchDistance = matchDistance;
                bestCounterpart = currentTokenYounger;
            }
        }
        return bestCounterpart;
    }

    private static List<IPASymbol> compareOneToN(List<IPASymbol> tokenOlder, List<List<IPASymbol>> tokenListYounger) {
        List<IPASymbol> bestCounterpart = null;
        double matchDistance;
        double priorMatchDistance = 0;

        for (List<IPASymbol> currentTokenYounger : tokenListYounger) {

            matchDistance = LevenshteinDistance.calculateSimilarityScore(tokenOlder, currentTokenYounger);
            if (matchDistance > priorMatchDistance) {
                priorMatchDistance = matchDistance;
                bestCounterpart = currentTokenYounger;
            }
        }
        return bestCounterpart;
    }

    private static List<Phone> compareOneToNForPhones(List<Phone> tokenOlder, List<List<Phone>> tokenListYounger) {
        List<Phone> bestCounterpart = null;
        double matchDistance;
        double priorMatchDistance = 0;

        for (List<Phone> currentTokenYounger : tokenListYounger) {

            matchDistance = LevenshteinDistance.calculateSimilarityScoreOfPhones(tokenOlder, currentTokenYounger);
            if (matchDistance > priorMatchDistance) {
                priorMatchDistance = matchDistance;
                bestCounterpart = currentTokenYounger;
            }
        }
        return bestCounterpart;
    }
}
