package auxiliary;

public class LevenshteinDistance {

    public static int calculateLevensheteinDistance(String sequence1, String sequence2) {
        int[][] levenshteinMatrix = new int[sequence1.length()][sequence2.length()];

        for (int i = 0; i < sequence1.length(); i++) {
            levenshteinMatrix[i][0] = i;
        }
        for (int j = 0; j < sequence1.length(); j++) {
            levenshteinMatrix[0][j] = j;
        }


        for (int i = 0; i < sequence1.length(); i++) {
            for (int j = 0; j < sequence2.length(); j++) {
                int substitutionCost;
                if (sequence1.charAt(i) == sequence2.charAt(j)) {
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

        return levenshteinMatrix[sequence1.length()][sequence2.length()];
    }
}
