import org.apache.commons.numbers.fraction.Fraction;

public class Main {
    public static void main(String[] args) {
        Phone phone = new Phone(FFE.of(1, 1), FFE.of(1, 1), FFE.of(1, 1));
        Consonant consonant = new Consonant(FFE.of(1, 1), FFE.of(1, 1), FFE.of(1, 1));
        Vowel vowel = new Vowel(FFE.of(-1, 1), FFE.of(-1, 1), FFE.of(-1, 1));
        System.out.println(phone);
        System.out.println(consonant);
        System.out.println(vowel);

        System.out.println(SoundLawMatrix.getIdentityMatrix());
    }
}