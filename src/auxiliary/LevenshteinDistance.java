package auxiliary;

import mapping.IPASymbol;

import java.util.List;

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
                if (compareSymbolsAt(sequence1, sequence2, i-1, j-1)) {
                     substitutionCost = 0;
                } else {
                    substitutionCost = 1;
                }
                calculateMatrixEntries(levenshteinMatrix, i, j, substitutionCost);
            }
        }

        return levenshteinMatrix[n][m];
    }

    public static int calculateDistance(List<IPASymbol> sequence1, List<IPASymbol> sequence2) {
        int n = sequence1.size();
        int m = sequence2.size();
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
                if (compareSymbolsAt(sequence1, sequence2, i-1, j-1)) {
                    substitutionCost = 0;
                } else {
                    substitutionCost = 1;
                }
                calculateMatrixEntries(levenshteinMatrix, i, j, substitutionCost);
            }
        }

        return levenshteinMatrix[n][m];
    }

    private static boolean compareSymbolsAt(String sequence1, String sequence2, int index1, int index2) {
        return sequence1.charAt(index1) == sequence2.charAt(index2);
    }

    private static boolean compareSymbolsAt(List<IPASymbol> sequence1, List<IPASymbol> sequence2, int index1, int index2) {
        return sequence1.get(index1) == sequence2.get(index2);
    }

    private static void calculateMatrixEntries(int[][] levenshteinMatrix, int i, int j, int substitutionCost) {
        int deletion = levenshteinMatrix[i-1][j] + 1;
        int insertion = levenshteinMatrix[i][j-1] + 1;
        int substitution = levenshteinMatrix[i-1][j-1] + substitutionCost;
        levenshteinMatrix[i][j] = Math.min(Math.min(deletion, insertion), substitution);
    }

    public static double calculateSimilarityScore(String sequence1, String sequence2) {
        int n = Math.max(sequence1.length(), sequence2.length());
        int d = calculateDistance(sequence1, sequence2);
        return (double) (n-d)/n;
    }

    public static double calculateSimilarityScore(List<IPASymbol> sequence1, List<IPASymbol> sequence2) {
        int n = Math.max(sequence1.size(), sequence2.size());
        int d = calculateDistance(sequence1, sequence2);
        return (double) (n-d)/n;
    }
}
