package auxiliary.parallelization;

public abstract class LevenshteinWorker<T> extends Thread {

    @Override
    public abstract void run();
}
