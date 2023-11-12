package auxiliary;

import mapping.IPASymbol;
import mapping.SigmaMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class StringPreprocessor {

    public static String clean(String text) {
        return text.toLowerCase().replaceAll("[[^\\p{L}\\p{M}]+]", "");
    }

    public static List<String> tokenize(String text) {
        String[] tokens = text.toLowerCase().split("[^\\p{L}\\p{M}]+");
        String[] nonZeroTokens = Arrays.stream(tokens).filter(t -> !t.isEmpty()).toArray(String[]::new);
        return Arrays.asList(nonZeroTokens);
    }

    public static List<String> tokenizeDistinct(String text) {
        return tokenize(text).stream().distinct().toList();
    }

    public static List<List<IPASymbol>> tokenizeDistinctToIPASymbolSequence(String text, SigmaMapper sigmaMapper, int stringWorkerNumber) throws InterruptedException {
        BlockingQueue<String> tokenQueue = new LinkedBlockingQueue<>(tokenizeDistinct(text));
        List<List<IPASymbol>> symbolSequenceList = Collections.synchronizedList(new ArrayList<>());
        List<Thread> stringWorkerList = new ArrayList<>();

        for (int i = 0; i < stringWorkerNumber; i++) {
            StringWorker stringWorker = new StringWorker(tokenQueue, symbolSequenceList, sigmaMapper);
            stringWorker.start();
            stringWorkerList.add(stringWorker);
        }

        for (Thread thread : stringWorkerList) {
            thread.join();
        }

        return List.copyOf(symbolSequenceList);

//        System.out.println("#############################");
//        int i = 0;
//        for (String token : tokenizeDistinct(text)) {
//            symbolSequenceList.add(sigmaMapper.mapToFlatSymbolSequence(text));
//            System.out.println("Iteration " + i++ + ": Token mapped to symbol");
//        }
//        return symbolSequenceList;
    }

    private static class StringWorker extends Thread {

        private final BlockingQueue<String> tokenQueue;
        private final List<List<IPASymbol>> synchronizedSymbolSequenceList;
        private final SigmaMapper sigmaMapper;

        private StringWorker(BlockingQueue<String> tokenQueue, List<List<IPASymbol>> synchronizedSymbolSequenceList, SigmaMapper sigmaMapper) {
            this.tokenQueue = tokenQueue;
            this.synchronizedSymbolSequenceList = synchronizedSymbolSequenceList;
            this.sigmaMapper = sigmaMapper;
        }

        @Override
        public void run() {
            while (true) {
                String token = tokenQueue.poll();
                if (token == null) {
                    break;
                } else {
                    List<IPASymbol> symbolSequence = sigmaMapper.mapToFlatSymbolSequence(token);
                    synchronizedSymbolSequenceList.add(symbolSequence);
                }
            }
            Thread.currentThread().interrupt();
        }
    }
}
