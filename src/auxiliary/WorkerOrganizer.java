package auxiliary;

import org.apache.commons.math4.legacy.core.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkerOrganizer {
    private final LinkedBlockingQueue<String> tokenQueueOlder;
    private final List<String> tokenListYounger;
    private final int levenshteinWorkerNumber;
    private final int needlemanWunschWorkerNumber;
    private final List<Pair<String, String>> bestMatchList = Collections.synchronizedList(new ArrayList<>());
    private final LinkedBlockingQueue<Pair<String, String>> bestMatchQueue = new LinkedBlockingQueue<>();
    private final List<Pair<String, String>> optimalAlignmentList = Collections.synchronizedList(new ArrayList<>());
    private boolean executionOrderIsCorrect = false;

    public WorkerOrganizer(List<String> tokenListOlder, List<String> tokenListYounger, int levenshteinWorkerNumber, int needlemanWunschWorkerNumber) {
        this.tokenQueueOlder = new LinkedBlockingQueue<>(tokenListOlder);
        this.tokenListYounger = Collections.synchronizedList(tokenListYounger);
        this.levenshteinWorkerNumber = levenshteinWorkerNumber;
        this.needlemanWunschWorkerNumber = needlemanWunschWorkerNumber;

    }

    public List<Pair<String, String>> executeLevenshteinWorkers() throws InterruptedException {
        List<Thread> levenshteinWorkerList = new ArrayList<>();
        for (int i = 0; i < levenshteinWorkerNumber; i++) {
            LevenshteinWorker levenshteinWorker = new LevenshteinWorker();
            levenshteinWorker.start();
            levenshteinWorkerList.add(levenshteinWorker);
        }
        for (Thread thread : levenshteinWorkerList) {
            thread.join();
        }
        executionOrderIsCorrect = true;
        return List.copyOf(bestMatchList);
    }

    public List<Pair<String, String>> executeNeedlemanWunschWorkers() throws InterruptedException {
        List<Thread> needlemanWunschWorkerList = new ArrayList<>();
        for (int i = 0; i < needlemanWunschWorkerNumber; i++) {
            NeedlemanWunschWorker needlemanWunschWorker = new NeedlemanWunschWorker();
            needlemanWunschWorker.start();
            needlemanWunschWorkerList.add(needlemanWunschWorker);
        }
        for (Thread thread : needlemanWunschWorkerList) {
            thread.join();
        }
        executionOrderIsCorrect = false;
        return List.copyOf(optimalAlignmentList);
    }

    private class LevenshteinWorker extends Thread {
        @Override
        public void run() {
            while (true) {
                String tokenOlder = tokenQueueOlder.poll();
                if (tokenOlder == null) {
                    break;
                } else {
                    List<String> localTokenListYounger = List.copyOf(tokenListYounger);
                    Pair<String, String> bestMatch = SequenceComparator.findBestMatchConcurrently(tokenOlder, localTokenListYounger);
                    bestMatchList.add(bestMatch);
                    bestMatchQueue.add(bestMatch);
                }
                Thread.currentThread().interrupt();
            }
        }
    }

    private class NeedlemanWunschWorker extends Thread {
        @Override
        public void run() {
            while (true) {
                Pair<String, String> bestMatch = bestMatchQueue.poll();
                if (bestMatch == null) {
                    break;
                } else {
                    Pair<String, String> optimalAlignment = NeedlemanWunschAlgorithm.computeOptimalAlignment(bestMatch.getKey(), bestMatch.getValue());
                    optimalAlignmentList.add(optimalAlignment);
                }
            }
            Thread.currentThread().interrupt();
        }
    }
}

