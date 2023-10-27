package auxiliary;

public class StringPreprocessor {
    public static String clean(String text) {
        return text.toLowerCase().replaceAll("[^a-zA-Z]", "");
    }
}
