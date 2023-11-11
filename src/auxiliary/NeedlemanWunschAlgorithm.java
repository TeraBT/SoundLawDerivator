package auxiliary;

import mapping.IPASymbol;
import org.apache.commons.math4.legacy.core.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeedlemanWunschAlgorithm {
    private static final int gapPenalty = -5;

    private static int getSimilarityScore(char i, char j) {
        return i == j ? 1 : -1;
    }

    private static int getSimilarityScore(IPASymbol i, IPASymbol j) {
        return i.equals(j) ? 1 : -1;
    }

    private static int[][] computeAlignmentMatrix(String sequence1, String sequence2) {
        int n = sequence1.length();
        int m = sequence2.length();
        int[][] alignmentMatrix = initializeMatrix(n, m);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int matching = alignmentMatrix[i - 1][j - 1] + getSimilarityScore(
                        sequence1.charAt(i - 1), sequence2.charAt(j - 1));
                int deletion = alignmentMatrix[i - 1][j] + gapPenalty;
                int insertion = alignmentMatrix[i][j - 1] + 1;
                alignmentMatrix[i][j] = Math.max(Math.max(matching, deletion), insertion);
            }
        }

        return alignmentMatrix;
    }

    private static int[][] computeAlignmentMatrix(List<IPASymbol> sequence1, List<IPASymbol> sequence2) {
        int n = sequence1.size();
        int m = sequence2.size();
        int[][] alignmentMatrix = initializeMatrix(n, m);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int matching = alignmentMatrix[i - 1][j - 1] + getSimilarityScore(
                        sequence1.get(i - 1), sequence2.get(j - 1));
                int deletion = alignmentMatrix[i - 1][j] + gapPenalty;
                int insertion = alignmentMatrix[i][j - 1] + 1;
                alignmentMatrix[i][j] = Math.max(Math.max(matching, deletion), insertion);
            }
        }

        return alignmentMatrix;
    }

    private static int[][] initializeMatrix(int n, int m) {
        int[][] alignmentMatrix = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            alignmentMatrix[i][0] = gapPenalty * i;
        }
        for (int j = 1; j <= m; j++) {
            alignmentMatrix[0][j] = gapPenalty * j;
        }
        return alignmentMatrix;
    }

    private static Pair<String, String> computeOptimalAlignment(int[][] alignmentMatrix, String sequence1, String sequence2) {
        StringBuilder alignmentSequence1 = new StringBuilder();
        StringBuilder alignmentSequence2 = new StringBuilder();
        int i = sequence1.length();
        int j = sequence2.length();

        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 && alignmentMatrix[i][j] == alignmentMatrix[i - 1][j - 1]
                    + getSimilarityScore(sequence1.charAt(i - 1), sequence2.charAt(j - 1))) {
                alignmentSequence1.insert(0, sequence1.charAt(i-1));
                alignmentSequence2.insert(0, sequence2.charAt(j-1));
                i--;
                j--;
            } else if (i > 0 && alignmentMatrix[i][j] == alignmentMatrix[i-1][j] + gapPenalty) {
                alignmentSequence1.insert(0, sequence1.charAt(i-1));
                alignmentSequence2.insert(0, '-');
                i--;
            } else {
                alignmentSequence1.insert(0, '-');
                alignmentSequence2.insert(0, sequence2.charAt(j-1));
                j--;
            }
        }

        return Pair.create(alignmentSequence1.toString(), alignmentSequence2.toString());
    }

    private static Pair<List<IPASymbol>, List<IPASymbol>> computeOptimalAlignment(int[][] alignmentMatrix, List<IPASymbol> sequence1, List<IPASymbol> sequence2) {
        List<IPASymbol> alignmentSequence1 = new ArrayList<>();
        List<IPASymbol> alignmentSequence2 = new ArrayList<>();
        int i = sequence1.size();
        int j = sequence2.size();

        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 && alignmentMatrix[i][j] == alignmentMatrix[i - 1][j - 1]
                    + getSimilarityScore(sequence1.get(i - 1), sequence2.get(j - 1))) {
                alignmentSequence1.add(0, sequence1.get(i-1));
                alignmentSequence2.add(0, sequence2.get(j-1));
                i--;
                j--;
            } else if (i > 0 && alignmentMatrix[i][j] == alignmentMatrix[i-1][j] + gapPenalty) {
                alignmentSequence1.add(0, sequence1.get(i-1));
                alignmentSequence2.add(0, IPASymbol.EMPTY_SYMBOL);
                i--;
            } else {
                alignmentSequence1.add(0, IPASymbol.EMPTY_SYMBOL);
                alignmentSequence2.add(0, sequence2.get(j-1));
                j--;
            }
        }

        return Pair.create(alignmentSequence1, alignmentSequence2);
    }

    public static Pair<String, String> computeOptimalAlignment(String sequence1, String sequence2) {
        return computeOptimalAlignment(computeAlignmentMatrix(sequence1, sequence2), sequence1, sequence2);
    }

    public static Pair<List<IPASymbol>, List<IPASymbol>> computeOptimalAlignment(List<IPASymbol> sequence1, List<IPASymbol> sequence2) {
        return computeOptimalAlignment(computeAlignmentMatrix(sequence1, sequence2), sequence1, sequence2);
    }
}
