package auxiliary;

import mapping.IPASymbol;
import mapping.SigmaMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringPreprocessor {
    public static String clean(String text) {
        return text.toLowerCase().replaceAll("[[^\\p{L}\\p{M}]+]", "");
    }

    public static List<String> tokenize(String text) {
        String[] tokens = text.toLowerCase().split("[^\\p{L}\\p{M}]+");
        String[] nonZeroTokens = Arrays.stream(tokens).filter(t -> !t.isEmpty()).toArray(String[]::new);
        return Arrays.asList(nonZeroTokens);
    }

    public static List<String> tokenizeDistinct(String text) {
        return tokenize(text).stream().distinct().toList();
    }

    public static List<List<IPASymbol>> tokenizeDistinctToIPASymbolSequence(String text, SigmaMapper sigmaMapper) { //TODO: parallelize
        List<List<IPASymbol>> symbolSequenceList = new ArrayList<>();
        System.out.println("#############################");
        int i = 0;
        for (String token : tokenizeDistinct(text)) {
            symbolSequenceList.add(sigmaMapper.mapToFlatSymbolSequence(text));
            System.out.println("Iteration " + i++ + ": Token mapped to symbol");
        }
        return symbolSequenceList;
    }
}
