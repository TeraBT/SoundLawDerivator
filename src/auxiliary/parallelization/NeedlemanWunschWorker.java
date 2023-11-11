package auxiliary.parallelization;

public abstract class NeedlemanWunschWorker<T> extends Thread {

    @Override
    public abstract void run();
}
