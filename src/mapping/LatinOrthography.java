package mapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LatinOrthography implements Orthography {
    private final Map<String, IPASymbol> representativeToSymbolMap = new HashMap<>();
    private final Map<IPASymbol, String> symbolToRepresentativeMap = new HashMap<>();

    public LatinOrthography() {
        representativeToSymbolMap.put("a", IPASymbol.A);
        representativeToSymbolMap.put("e", IPASymbol.E);
        representativeToSymbolMap.put("i", IPASymbol.I);
        representativeToSymbolMap.put("o", IPASymbol.O);
        representativeToSymbolMap.put("u", IPASymbol.U);

        representativeToSymbolMap.put("qu", IPASymbol.K_LABIALIZED);

        for (Map.Entry<String, IPASymbol> stringPhoneEntry : representativeToSymbolMap.entrySet()) {
            symbolToRepresentativeMap.put(stringPhoneEntry.getValue(), stringPhoneEntry.getKey());
        }
    }

    @Override
    public IPASymbol mapToSymbol(String sequence) {
        return representativeToSymbolMap.get(sequence);
    }

    @Override
    public String mapToRepresentative(IPASymbol symbol) {
        return symbolToRepresentativeMap.get(symbol);
    }

    @Override
    public Set<String> getRepresentativeSet() {
        return Collections.unmodifiableSet(representativeToSymbolMap.keySet());
    }

    @Override
    public Set<IPASymbol> getSymbolSet() {
        return Collections.unmodifiableSet(symbolToRepresentativeMap.keySet());
    }
}
