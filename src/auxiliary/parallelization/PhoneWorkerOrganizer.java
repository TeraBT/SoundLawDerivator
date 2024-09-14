package auxiliary.parallelization;

import auxiliary.NeedlemanWunschAlgorithm;
import auxiliary.SequenceComparator;
import mapping.IPASymbol;
import org.apache.commons.math4.legacy.core.Pair;
import soundsystem.Phone;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class PhoneWorkerOrganizer extends WorkerOrganizer<List<Phone>> {

    public PhoneWorkerOrganizer(List<List<Phone>> tokenListOlder, List<List<Phone>> tokenListYounger, int levenshteinWorkerNumber, int needlemanWunschWorkerNumber) {
        super.tokenQueueOlder = new LinkedBlockingQueue<>(tokenListOlder);
        super.tokenListYounger = Collections.synchronizedList(tokenListYounger);
        super.levenshteinWorkerNumber = levenshteinWorkerNumber;
        super.needlemanWunschWorkerNumber = needlemanWunschWorkerNumber;

    }

    @Override
    protected Pair<List<Phone>, List<Phone>> levenshteinMatchWrapperMethod(List<Phone> tokenOlder, List<List<Phone>> tokenListYounger) {
        return SequenceComparator.findBestMatchForPhonesConcurrently(tokenOlder, tokenListYounger);
    }

    @Override
    protected Pair<List<Phone>, List<Phone>> needlemanWunschAlignmentWrapperMethod(List<Phone> sequence1, List<Phone> sequence2) {
        return NeedlemanWunschAlgorithm.computeOptimalAlignmentOfPhones(sequence1, sequence2);
    }
}
