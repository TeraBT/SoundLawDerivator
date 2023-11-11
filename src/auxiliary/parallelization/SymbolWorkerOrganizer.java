package auxiliary.parallelization;

import auxiliary.NeedlemanWunschAlgorithm;
import auxiliary.SequenceComparator;
import mapping.IPASymbol;
import org.apache.commons.math4.legacy.core.Pair;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class SymbolWorkerOrganizer extends WorkerOrganizer<List<IPASymbol>> {
//    private final LinkedBlockingQueue<List<IPASymbol>> tokenQueueOlder;
//    private final List<List<IPASymbol>> tokenListYounger;
//    private final int levenshteinWorkerNumber;
//    private final int needlemanWunschWorkerNumber;
//    private final List<Pair<List<IPASymbol>, List<IPASymbol>>> bestMatchList = Collections.synchronizedList(new ArrayList<>());
//    private final LinkedBlockingQueue<Pair<List<IPASymbol>, List<IPASymbol>>> bestMatchQueue = new LinkedBlockingQueue<>();
//    private final List<Pair<List<IPASymbol>, List<IPASymbol>>> optimalAlignmentList = Collections.synchronizedList(new ArrayList<>());
//    private boolean executionOrderIsCorrect = false;

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


//    public List<Pair<List<IPASymbol>, List<IPASymbol>>> executeLevenshteinWorkers() throws InterruptedException {
//        List<Thread> levenshteinWorkerList = new ArrayList<>();
//        for (int i = 0; i < levenshteinWorkerNumber; i++) {
//            WorkerOrganizerForSymbolSequence.LevenshteinWorker levenshteinWorker = new WorkerOrganizerForSymbolSequence.LevenshteinWorker();
//            levenshteinWorker.start();
//            levenshteinWorkerList.add(levenshteinWorker);
//        }
//        for (Thread thread : levenshteinWorkerList) {
//            thread.join();
//        }
//        executionOrderIsCorrect = true;
//        return List.copyOf(bestMatchList);
//    }

//    public List<Pair<List<IPASymbol>, List<IPASymbol>>> executeNeedlemanWunschWorkers() throws InterruptedException {
//        List<Thread> needlemanWunschWorkerList = new ArrayList<>();
//        for (int i = 0; i < needlemanWunschWorkerNumber; i++) {
//            WorkerOrganizerForSymbolSequence.NeedlemanWunschWorker needlemanWunschWorker = new WorkerOrganizerForSymbolSequence.NeedlemanWunschWorker();
//            needlemanWunschWorker.start();
//            needlemanWunschWorkerList.add(needlemanWunschWorker);
//        }
//        for (Thread thread : needlemanWunschWorkerList) {
//            thread.join();
//        }
//        executionOrderIsCorrect = false;
//        return List.copyOf(optimalAlignmentList);
//    }

//    private class LevenshteinWorker extends Thread {
//        @Override
//        public void run() {
//            while (true) {
//                List<IPASymbol> tokenOlder = tokenQueueOlder.poll();
//                if (tokenOlder == null) {
//                    break;
//                } else {
//                    List<List<IPASymbol>> localTokenListYounger = List.copyOf(tokenListYounger);
//                    Pair<List<IPASymbol>, List<IPASymbol>> bestMatch = SequenceComparator.findBestMatchConcurrently(tokenOlder, localTokenListYounger);
//                    bestMatchList.add(bestMatch);
//                    bestMatchQueue.add(bestMatch);
//                }
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//
//    private class NeedlemanWunschWorker extends Thread {
//        @Override
//        public void run() {
//            while (true) {
//                Pair<List<IPASymbol>, List<IPASymbol>> bestMatch = bestMatchQueue.poll();
//                if (bestMatch == null) {
//                    break;
//                } else {
//                    Pair<List<IPASymbol>, List<IPASymbol>> optimalAlignment = NeedlemanWunschAlgorithm.computeOptimalAlignment(bestMatch.getKey(), bestMatch.getValue());
//                    optimalAlignmentList.add(optimalAlignment);
//                }
//            }
//            Thread.currentThread().interrupt();
//        }
//    }
}
