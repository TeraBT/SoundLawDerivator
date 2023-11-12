package auxiliary.parallelization;

import org.apache.commons.math4.legacy.core.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class WorkerOrganizer<T> {
    protected LinkedBlockingQueue<T> tokenQueueOlder;
    protected List<T> tokenListYounger;
    protected int levenshteinWorkerNumber;
    protected int needlemanWunschWorkerNumber;
    protected final List<Pair<T, T>> bestMatchList = Collections.synchronizedList(new ArrayList<>());
    protected final LinkedBlockingQueue<Pair<T, T>> bestMatchQueue = new LinkedBlockingQueue<>();
    protected final List<Pair<T, T>> optimalAlignmentList = Collections.synchronizedList(new ArrayList<>());
    protected boolean executionOrderIsCorrect = false;

    public List<Pair<T, T>> executeLevenshteinWorkers() throws InterruptedException {
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

    public List<Pair<T, T>> executeNeedlemanWunschWorkers() throws InterruptedException {
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
                T tokenOlder = tokenQueueOlder.poll();
                if (tokenOlder == null) {
                    break;
                } else {
                    List<T> localTokenListYounger = List.copyOf(tokenListYounger);
                    Pair<T, T> bestMatch = levenshteinMatchWrapperMethod(tokenOlder, localTokenListYounger);
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
                Pair<T, T> bestMatch = bestMatchQueue.poll();
                if (bestMatch == null) {
                    break;
                } else {
                    Pair<T, T> optimalAlignment = needlemanWunschAlignmentWrapperMethod(bestMatch.getKey(), bestMatch.getValue());
                    optimalAlignmentList.add(optimalAlignment);
                }
            }
            Thread.currentThread().interrupt();
        }
    }

    protected abstract Pair<T, T> levenshteinMatchWrapperMethod(T tokenOlder, List<T> tokenListYounger);

    protected abstract Pair<T, T> needlemanWunschAlignmentWrapperMethod(T sequence1, T sequence2);


//    SequenceComparator.findBestMatchConcurrently(tokenOlder, localTokenListYounger)
//    NeedlemanWunschAlgorithm.computeOptimalAlignment(bestMatch.getKey(), bestMatch.getValue())
}
