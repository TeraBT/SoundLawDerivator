package auxiliary.parallelization;

import auxiliary.NeedlemanWunschAlgorithm;
import auxiliary.SequenceComparator;
import org.apache.commons.math4.legacy.core.Pair;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class RepresentativeWorkerOrganizer extends WorkerOrganizer<String> {

    public RepresentativeWorkerOrganizer(List<String> tokenListOlder, List<String> tokenListYounger, int levenshteinWorkerNumber, int needlemanWunschWorkerNumber) {
        super.tokenQueueOlder = new LinkedBlockingQueue<>(tokenListOlder);
        super.tokenListYounger = Collections.synchronizedList(tokenListYounger);
        super.levenshteinWorkerNumber = levenshteinWorkerNumber;
        super.needlemanWunschWorkerNumber = needlemanWunschWorkerNumber;
    }

    @Override
    protected Pair<String, String> levenshteinMatchWrapperMethod(String tokenOlder, List<String> tokenListYounger) {
        return SequenceComparator.findBestMatchConcurrently(tokenOlder, tokenListYounger);
    }

    @Override
    protected Pair<String, String> needlemanWunschAlignmentWrapperMethod(String sequence1, String sequence2) {
        return NeedlemanWunschAlgorithm.computeOptimalAlignment(sequence1, sequence2);
    }
}
