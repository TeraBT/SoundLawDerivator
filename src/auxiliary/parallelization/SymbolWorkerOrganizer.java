package auxiliary.parallelization;

import auxiliary.NeedlemanWunschAlgorithm;
import auxiliary.SequenceComparator;
import mapping.IPASymbol;
import org.apache.commons.math4.legacy.core.Pair;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class SymbolWorkerOrganizer extends WorkerOrganizer<List<IPASymbol>> {

    public SymbolWorkerOrganizer(List<List<IPASymbol>> tokenListOlder, List<List<IPASymbol>> tokenListYounger, int levenshteinWorkerNumber, int needlemanWunschWorkerNumber) {
        super.tokenQueueOlder = new LinkedBlockingQueue<>(tokenListOlder);
        super.tokenListYounger = Collections.synchronizedList(tokenListYounger);
        super.levenshteinWorkerNumber = levenshteinWorkerNumber;
        super.needlemanWunschWorkerNumber = needlemanWunschWorkerNumber;

    }

    @Override
    protected Pair<List<IPASymbol>, List<IPASymbol>> levenshteinMatchWrapperMethod(List<IPASymbol> tokenOlder, List<List<IPASymbol>> tokenListYounger) {
        return SequenceComparator.findBestMatchConcurrently(tokenOlder, tokenListYounger);
    }

    @Override
    protected Pair<List<IPASymbol>, List<IPASymbol>> needlemanWunschAlignmentWrapperMethod(List<IPASymbol> sequence1, List<IPASymbol> sequence2) {
        return NeedlemanWunschAlgorithm.computeOptimalAlignment(sequence1, sequence2);
    }
}
