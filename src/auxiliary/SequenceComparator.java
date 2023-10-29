package auxiliary;

import org.apache.commons.math4.legacy.core.Pair;

import java.util.ArrayList;
import java.util.List;

public class SequenceComparator {

    public static List<Pair<String, String>> findMostSimilarMatches(List<String> olderTokens, List<String> youngerTokens) {
        List<Pair<String, String>> matchList = new ArrayList<>();
        for (String olderToken : olderTokens) {
            String bestMatch = null;
            double matchDistance = 0;
            double priorMatchDistance = 0;
            for (String youngerToken : youngerTokens) {
                matchDistance = LevenshteinDistance.calculateSimilarityScore(olderToken, youngerToken);
                if (matchDistance > priorMatchDistance) {
                    priorMatchDistance = matchDistance;
                    bestMatch = youngerToken;
                }
            }
            matchList.add(new Pair<>(olderToken, bestMatch));
        }
        return matchList;
    }

    //TODO: same method but for IPASymbols!
}
