package auxiliary;

import java.util.Arrays;

public class StringPreprocessor {
    public static String clean(String text) {
        return text.toLowerCase().replaceAll("[[^\\p{L}\\p{M}]+]", "");
    }

    public static String[] tokenize(String text) {
        String[] tokens = text.toLowerCase().split("[^\\p{L}\\p{M}]+");
        String[] nonZeroTokens = Arrays.stream(tokens).filter(t -> !t.isEmpty()).toArray(String[]::new);
        return nonZeroTokens;
    }
}
