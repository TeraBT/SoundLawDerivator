import auxiliary.*;
import auxiliary.parallelization.RepresentativeWorkerOrganizer;
import auxiliary.parallelization.SymbolWorkerOrganizer;
import mapping.IPA;
import mapping.IPASymbol;
import mapping.LatinOrthography;
import mapping.SigmaMapper;
import org.apache.commons.math4.legacy.core.Pair;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;


public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) throws IOException, InterruptedException {
//        SoundSystem.Phone phone = new SoundSystem.Phone(SoundSystem.FFE.of(1, 1), SoundSystem.FFE.of(1, 1), SoundSystem.FFE.of(1, 1));
//        SoundSystem.Consonant consonant = new SoundSystem.Consonant(SoundSystem.FFE.of(1, 1), SoundSystem.FFE.of(1, 1), SoundSystem.FFE.of(1, 1));
//        SoundSystem.Vowel vowel = new SoundSystem.Vowel(SoundSystem.FFE.of(-1, 1), SoundSystem.FFE.of(-1, 1), SoundSystem.FFE.of(-1, 1));
//        System.out.println(phone);
//        System.out.println(consonant);
//        System.out.println(vowel);
//
//        System.out.println(SoundSystem.SoundLawMatrix.getIdentityMatrix());

//        SoundSystem.Phone fromPhone = new SoundSystem.Phone(1, 1, 1, 1, 1, 1);
//        SoundSystem.Phone toPhone = new SoundSystem.Phone(SoundSystem.FFE.of(1, 1), SoundSystem.FFE.of(1, 3), SoundSystem.FFE.of(9, 5));
//        System.out.println("toPhone: " + toPhone);
//        SoundSystem.SoundLawMatrix sl1 = SoundSystem.SoundLawMatrix.generateSoundLawMatrixOf(fromPhone, toPhone);
//        SoundSystem.Phone transformedFromPhone = sl1.applySoundLaw(fromPhone);
//        System.out.println("transformedFromPhone: " + transformedFromPhone);
//
//        if (transformedFromPhone.equals(toPhone)) {
//        System.out.println(ANSI_GREEN
//                + "EQUAL"
//                + ANSI_RESET);
//
//        }

//        String test = "InTabernaQuando";
//        System.out.println(test.indexOf("Taberna", 3));
        String text = XMLParser.parseOnlyText("corpora/Julius-Caesar_De-bello-Gallico.xml").substring(0, 101);
//        List<Phone> phoneSequence = new SymbolParser(new LatinSymbolMapper()).parse("Gallia");
//        System.out.println(phoneSequence);
//        System.out.println(new StringBuilder("TEST").replace(1, 2, "L"));
//        String text = "Gaquaqllia";
//        String text2 = "qu";
//        String representativeSequence = StringPreprocessor.clean("terques");
//        System.out.println(representativeSequence);
        LatinOrthography latinOrthography = new LatinOrthography();
        IPA ipa = new IPA();
        SigmaMapper sigmaMapper = new SigmaMapper(latinOrthography, ipa);
////        System.out.println(sigmaMapper.mapToSymbolSequence(representativeSequence));
//        List<Phone> phoneSequence = sigmaMapper.mapDirectlyToPhoneSequence(representativeSequence);
//        System.out.println(phoneSequence);
//        String mappedBack = sigmaMapper.mapBackDirectlyToRepresentativeSequence(phoneSequence);
//        System.out.println(mappedBack);
//        if (mappedBack.equals(representativeSequence)) {
//            System.out.println(ANSI_GREEN
//                    + "EQUAL"
//                    + ANSI_RESET);
//        } else {
//            System.out.println(ANSI_RED
//                    + "UNEQUAL"
//                    + ANSI_RESET);
//        }
//        String[] arr= StringPreprocessor.tokenize("    gärhô    \r \r\n \n\r loss \n dsf   ");
//        System.out.println(Arrays.toString(StringPreprocessor.tokenize(text)));
//        int[][] array = new int[3][3];
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[0].length; j++) {
//                System.out.println(array[i][j]);
//            }
//        }

//        System.out.println(LevenshteinDistance.calculateSimilarityScore("asdfgjl", "dsa"));
//        String s1 = "aeiou";//"AGACTAGTTAC";
//        String s2 =  "aiu";//"CGAGACGT";
//        var seqs = NeedlemanWunschAlgorithm.computeOptimalSequences(sigmaMapper.mapToSymbolSequence(s1), sigmaMapper.mapToSymbolSequence(s2));
////        System.out.println(s1 + '\n' + seqs[0]);
////        System.out.println("-------------");
////        System.out.println(s2 + '\n' + seqs[1]);
//        System.out.println(seqs.get(0) + "\n" + seqs.get(1));
//        System.out.println(TextReader.read("corpora/test.txt"));
        List<String> latinTokens = StringPreprocessor.tokenizeDistinct(XMLParser.parseOnlyText("corpora/Julius-Caesar_De-bello-Gallico.xml"));
        List<String> italianoTokens = StringPreprocessor.tokenizeDistinct(XMLParser.parseOnlyText("corpora/de_bello_gallico_italiano.html"));
        List<List<IPASymbol>> latinSymbolTokens = StringPreprocessor.tokenizeDistinctToIPASymbolSequence(XMLParser.parseOnlyText("corpora/Julius-Caesar_De-bello-Gallico.xml"), sigmaMapper, 16);
        List<List<IPASymbol>> italianoSymbolTokens = StringPreprocessor.tokenizeDistinctToIPASymbolSequence(XMLParser.parseOnlyText("corpora/de_bello_gallico_italiano.html"), sigmaMapper, 16);
//        System.out.println(latinTokens.length + "\n" + italianoTokens.length);

//        List<Pair<String, String>> mostSimilarMatches = SequenceComparator.findMostSimilarMatches(latinTokens.subList(0, 10000), italianoTokens.subList(0, 10000));
//        for (int i = 0; i < mostSimilarMatches.size(); i++) {
//            System.out.print(mostSimilarMatches.get(i) + "\n");
//        }
//        ArrayList<Pair<String, String>> aligns = new ArrayList<>();
//        for (String latinToken : latinTokens) {
//            for (String italianoToken : italianoTokens) {
//                aligns.add(NeedlemanWunschAlgorithm.computeOptimalSequences(latinToken, italianoToken));
//            }
//        }
//        System.out.println(aligns.toString());

        //levenshtein
//        List<String> latinSmall = latinTokens.subList(0, 100);
//        List<String> italianoSmall = italianoTokens.subList(0, 100);
        RepresentativeWorkerOrganizer woRepresentatives = new RepresentativeWorkerOrganizer(latinTokens, italianoTokens, 16, 16);
        SymbolWorkerOrganizer woSymbols = new SymbolWorkerOrganizer(latinSymbolTokens, italianoSymbolTokens, 16, 16);
//        List<Pair<String, String>> bestMatchesConcurrent = woRepresentatives.executeLevenshteinWorkers();
//        System.out.println(bestMatchesConcurrent.size());
//        bestMatchesConcurrent = bestMatchesConcurrent.stream().sorted(Comparator.comparing(Pair::getKey)).toList();
//        for (Pair<String, String> bestMatch : bestMatchesConcurrent) {
//            System.out.println(bestMatch);
//        }

        List<Pair<List<IPASymbol>, List<IPASymbol>>> bestMatchesConcurrentSymbol = woSymbols.executeLevenshteinWorkers();
        System.out.println(bestMatchesConcurrentSymbol.size());
        bestMatchesConcurrentSymbol = bestMatchesConcurrentSymbol.stream().sorted(Comparator.comparing(s -> s.getKey().get(0))).toList();
        int i = 0;
        for (var bestMatch : bestMatchesConcurrentSymbol) {
            if (i == 10) {
                break;
            }
            System.out.println(bestMatch);
            ++i;
        }

        System.out.println("MATCHES DONE #################################################");
//        List<Pair<String, String>> bestMatchesSingular = SequenceComparator.findBestMatches(latinTokens, italianoTokens);
//        bestMatchesSingular = bestMatchesSingular.stream().sorted(Comparator.comparing(Pair::getKey)).toList();
//        for (Pair<String, String> mostSimilarMatch : bestMatchesSingular) {
//            System.out.println(mostSimilarMatch);
//        }
//        if (bestMatchesSingular.equals(bestMatchesConcurrent)) {
//            System.out.println(ANSI_GREEN
//                    + "EQUAL"
//                    + ANSI_RESET);
//        } else {
//            System.out.println(ANSI_RED
//                    + "UNEQUAL"
//                    + ANSI_RESET);
//        }
//        System.out.println("NEEDLEMAN-WUNSCH###############################");
//        //needleman-wunsch
//        List<Pair<String, String>> optimalAlignmentsConcurrent = woRepresentatives.executeNeedlemanWunschWorkers();
//        List<Pair<String, String>> optimalAlignmentsSingular = new ArrayList<>();
//        for (Pair<String, String> bestMatch : bestMatchesSingular) {
//            Pair<String, String> optimalAlignment = NeedlemanWunschAlgorithm.computeOptimalAlignment(bestMatch.getKey(), bestMatch.getValue());
//            optimalAlignmentsSingular.add(optimalAlignment);
//        }
//
//        optimalAlignmentsConcurrent = optimalAlignmentsConcurrent.stream().sorted(Comparator.comparing(Pair::getKey)).toList();
//        for (Pair<String, String> optimalAlignment : optimalAlignmentsConcurrent) {
//            System.out.println(optimalAlignment);
//        }
//        System.out.println(optimalAlignmentsConcurrent.size());
//        optimalAlignmentsSingular = optimalAlignmentsSingular.stream().sorted(Comparator.comparing(Pair::getKey)).toList();
//        System.out.println("ALIGNMENTS########################################");
//        for (Pair<String, String> optimalAlignment : optimalAlignmentsSingular) {
//            System.out.println(optimalAlignment);
//        }

        List<Pair<List<IPASymbol>, List<IPASymbol>>> optimalAlignmentsConcurrentSymbol = woSymbols.executeNeedlemanWunschWorkers();
        i = 0;
        optimalAlignmentsConcurrentSymbol = optimalAlignmentsConcurrentSymbol.stream().sorted(Comparator.comparing(s -> s.getKey().get(0))).toList();
        for (Pair<List<IPASymbol>, List<IPASymbol>> optimalAlignment : optimalAlignmentsConcurrentSymbol) {
            if (i == 10) {
                break;
            }
            System.out.println(optimalAlignment);
            ++i;
        }

        System.out.println("ALIGNMENTS DONE ########################################");



//        if (bestMatchesConcurrentSymbol.equals(optimalAlignmentsConcurrentSymbol)) {
//            System.out.println(ANSI_GREEN
//                    + "EQUAL"
//                    + ANSI_RESET);
//        } else {
//            System.out.println(ANSI_RED
//                    + "UNEQUAL"
//                    + ANSI_RESET);
//        }



    }
}