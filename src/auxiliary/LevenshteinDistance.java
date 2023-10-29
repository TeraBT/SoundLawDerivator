package auxiliary;

public class LevenshteinDistance {
    public static int calculateDistance(String sequence1, String sequence2) {
        int n = sequence1.length();
        int m = sequence2.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        int[][] levenshteinMatrix = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            levenshteinMatrix[i][0] = i;
        }
        for (int j = 1; j <= m; j++) {
            levenshteinMatrix[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int substitutionCost;
                if (sequence1.charAt(i-1) == sequence2.charAt(j-1)) {
                     substitutionCost = 0;
                } else {
                    substitutionCost = 1;
                }
                int deletion = levenshteinMatrix[i-1][j] + 1;
                int insertion = levenshteinMatrix[i][j-1] + 1;
                int substitution = levenshteinMatrix[i-1][j-1] + substitutionCost;
                levenshteinMatrix[i][j] = Math.min(Math.min(deletion, insertion), substitution);
            }
        }

        return levenshteinMatrix[n][m];
    }

    public static double calculateSimilarityScore(String sequence1, String sequence2) {
        int n = Math.max(sequence1.length(), sequence2.length());
        int d = calculateDistance(sequence1, sequence2);
        return (double) (n-d)/n;
    }
}
