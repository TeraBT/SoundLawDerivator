import auxiliary.StringPreprocessor;
import auxiliary.XMLParser;
import mapping.IPA;
import mapping.LatinOrthography;
import mapping.SigmaMapper;
import org.apache.commons.math4.legacy.optim.nonlinear.scalar.Sigma;

import java.io.IOException;


public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) throws IOException {
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
        String text = XMLParser.parse("corpora/Julius-Caesar_De-bello-Gallico.xml").substring(0, 1000);
//        List<Phone> phoneSequence = new SymbolParser(new LatinSymbolMapper()).parse("Gallia");
//        System.out.println(phoneSequence);
//        System.out.println(new StringBuilder("TEST").replace(1, 2, "L"));
//        String text = "Gaquaqllia";
//        String text2 = "qu";
        String sequence = StringPreprocessor.clean(text);
        LatinOrthography latinOrthography = new LatinOrthography();
        IPA ipa = new IPA();
        SigmaMapper sigmaMapper = new SigmaMapper(latinOrthography, ipa);
        System.out.println(sigmaMapper.mapToSymbolSequence(sequence));
        System.out.println(sigmaMapper.mapDirectlyToPhoneSequence(sequence));
    }
}