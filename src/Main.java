import Parsing.LatinSymbolMapper;
import Parsing.PhoneParser;
import Parsing.XMLParser;
import SoundSystem.Phone;

import java.io.IOException;
import java.util.List;


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

//        System.out.println(XMLParser.parse("corpora/Julius-Caesar_De-bello-Gallico.xml").substring(0, 1000));
        List<Phone> phoneSequence = new PhoneParser(new LatinSymbolMapper()).parse("Gallia");
        System.out.println(phoneSequence);
        //TODO: [null, P(-2 / 3, 0, 0), null, null, null, P(-2 / 3, 0, 0), P(-1, -1, 0), null, null] correct mappings, but wrong order and position!
    }
}