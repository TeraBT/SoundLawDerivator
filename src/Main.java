import auxiliary.StringPreprocessor;
import auxiliary.XMLParser;
import auxiliary.parallelization.PhoneWorkerOrganizer;
import mapping.*;
import naive.NaiveDerivationAlgorithm;
import org.apache.commons.math4.legacy.core.Pair;
import soundsystem.Phone;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) throws IOException, InterruptedException {
        IPA ipa = new IPA();
        LatinOrthography latinOrthography = new LatinOrthography();
        ItalianOrthography italianOrthography = new ItalianOrthography();
        SigmaMapper sigmaMapperLatin = new SigmaMapper(latinOrthography, ipa);
        SigmaMapper sigmaMapperItalian = new SigmaMapper(italianOrthography, ipa);

        List<String> latinTokens = StringPreprocessor.tokenizeDistinct(XMLParser.parseOnlyText("corpora/Julius-Caesar_De-bello-Gallico.xml"));
        List<String> italianTokens = StringPreprocessor.tokenizeDistinct(XMLParser.parseOnlyText("corpora/de_bello_gallico_italiano.html"));
        List<List<Phone>> latinPhoneTokens = StringPreprocessor.tokenizeDistinctToPhoneSequence(XMLParser.parseOnlyText("corpora/Julius-Caesar_De-bello-Gallico.xml"), sigmaMapperLatin, 16);
        List<List<Phone>> italianPhoneTokens = StringPreprocessor.tokenizeDistinctToPhoneSequence(XMLParser.parseOnlyText("corpora/de_bello_gallico_italiano.html"), sigmaMapperItalian, 16);

        PhoneWorkerOrganizer woPhones = new PhoneWorkerOrganizer(latinPhoneTokens, italianPhoneTokens, 16, 16);


        List<Pair<List<Phone>, List<Phone>>> bestMatchesConcurrentForPhones = woPhones.executeLevenshteinWorkers();
        System.out.println(bestMatchesConcurrentForPhones.size());
        bestMatchesConcurrentForPhones = bestMatchesConcurrentForPhones.stream().sorted(Comparator.comparing(s -> s.getKey().size())).toList();
        int i = 0;
        for (var bestMatch : bestMatchesConcurrentForPhones) {
//            if (i == 10) {
//                break;
//            }
            System.out.println(bestMatch);
            ++i;
        }

        System.out.println("MATCHES DONE #################################################");

        List<Pair<List<Phone>, List<Phone>>> optimalAlignmentsConcurrentForPhones = woPhones.executeNeedlemanWunschWorkers();
        i = 0;
        optimalAlignmentsConcurrentForPhones = optimalAlignmentsConcurrentForPhones.stream().sorted(Comparator.comparing(s -> s.getKey().size())).toList();
        for (Pair<List<Phone>, List<Phone>> optimalAlignment : optimalAlignmentsConcurrentForPhones) {
//            if (i == 10) {
//                break;
//            }
            System.out.println(optimalAlignment);
            ++i;
        }

        System.out.println("ALIGNMENTS DONE ########################################");

        List<List<List<Phone>>> soundLawCandidatesAsPhones = NaiveDerivationAlgorithm.deriveSoundLawCandidatesAsPhones(optimalAlignmentsConcurrentForPhones);
        List<Map.Entry<List<Phone>, Long>> soundLawsAsPhones = NaiveDerivationAlgorithm.deriveSoundLawsAsPhones(soundLawCandidatesAsPhones);

        for (i = 0; i < soundLawsAsPhones.size(); i++) {
            System.out.println(soundLawsAsPhones.get(i));
        }

        soundLawsAsPhones.forEach(System.out::println);

        System.out.println("SOUND LAWS DONE ########################################\nSHOWING TOP 20 NOW");

        for (i = 0; i < soundLawsAsPhones.size() && i < 20; i++) {
            Map.Entry<List<Phone>, Long> soundLaw = soundLawsAsPhones.get(i);
                System.out.println(soundLaw);
        }
    }
}