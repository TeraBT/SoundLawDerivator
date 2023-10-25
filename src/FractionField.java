import org.apache.commons.math4.legacy.core.Field;
import org.apache.commons.math4.legacy.core.FieldElement;
import org.apache.commons.numbers.fraction.Fraction;

public class FractionField implements Field<FFE> {
    @Override
    public FFE getZero() {
        return FFE.of(0, 1);
    }

    @Override
    public FFE getOne() {
        return FFE.of(1, 1);
    }

    @Override
    public Class<? extends FieldElement<FFE>> getRuntimeClass() {
        return FFE.class;
    }
}
