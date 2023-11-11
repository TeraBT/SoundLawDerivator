package mapping;

import java.util.*;

public class LatinOrthography implements Orthography {
    private final Map<String, List<IPASymbol>> representativeToSymbolMap = new HashMap<>();
    private final Map<List<IPASymbol>, String> symbolToRepresentativeMap = new HashMap<>();

    public LatinOrthography() {
        representativeToSymbolMap.put("a", List.of(IPASymbol.A));
        representativeToSymbolMap.put("e", List.of(IPASymbol.E));
        representativeToSymbolMap.put("i", List.of(IPASymbol.I));
        representativeToSymbolMap.put("o", List.of(IPASymbol.O));
        representativeToSymbolMap.put("u", List.of(IPASymbol.U));

        representativeToSymbolMap.put("p", List.of(IPASymbol.VOICELESS_BILABIAL_PLOSIVE));
        representativeToSymbolMap.put("b", List.of(IPASymbol.VOICED_BILABIAL_PLOSIVE));
        representativeToSymbolMap.put("t", List.of(IPASymbol.VOICELESS_ALVEOLAR_PLOSIVE));
        representativeToSymbolMap.put("d", List.of(IPASymbol.VOICED_ALVEOLAR_PLOSIVE));
        representativeToSymbolMap.put("k", List.of(IPASymbol.VOICELESS_VELAR_PLOSIVE));
        representativeToSymbolMap.put("g", List.of(IPASymbol.VOICED_VELAR_PLOSIVE));
        
        representativeToSymbolMap.put("qu", List.of(IPASymbol.VOICELESS_VELAR_PLOSIVE_LABIALIZED));
        
        representativeToSymbolMap.put("m", List.of(IPASymbol.BILABIAL_NASAL));
        representativeToSymbolMap.put("n", List.of(IPASymbol.ALVEOLAR_NASAL));
        representativeToSymbolMap.put("ng", List.of(IPASymbol.VELAR_NASAL));
        
        representativeToSymbolMap.put("gn", List.of(IPASymbol.VELAR_NASAL, IPASymbol.ALVEOLAR_NASAL));
        
        representativeToSymbolMap.put("r", List.of(IPASymbol.ALVEOLAR_TRILL));
        
        representativeToSymbolMap.put("f", List.of(IPASymbol.VOICELESS_LABIODENTAL_FRICATIVE));
        representativeToSymbolMap.put("v", List.of(IPASymbol.VOICED_LABIODENTAL_FRICATIVE));
        representativeToSymbolMap.put("s", List.of(IPASymbol.VOICELESS_ALVEOLAR_FRICATIVE));
        representativeToSymbolMap.put("z", List.of(IPASymbol.VOICED_ALVEOLAR_FRICATIVE));
        representativeToSymbolMap.put("h", List.of(IPASymbol.VOICELESS_GLOTTAL_FRICATIVE));
        
//        representativeToSymbolMap.put("i", List.of(IPASymbol.PALATAL_APPROXIMANT));
        
        representativeToSymbolMap.put("l", List.of(IPASymbol.ALVEOLAR_LATERAL_APPROXIMANT));


        for (Map.Entry<String, List<IPASymbol>> representativeSymbolEntry : representativeToSymbolMap.entrySet()) {
            symbolToRepresentativeMap.put(representativeSymbolEntry.getValue(), representativeSymbolEntry.getKey());
        }
    }

    @Override
    public List<IPASymbol> mapToSymbolList(String representative) {
        return representativeToSymbolMap.get(representative);
    }

    @Override
    public String mapToRepresentative(List<IPASymbol> symbolList) {
        return symbolToRepresentativeMap.get(symbolList);
    }

    @Override
    public Set<String> getRepresentativeSet() {
        return Collections.unmodifiableSet(representativeToSymbolMap.keySet());
    }

    @Override
    public Set<List<IPASymbol>> getSymbolListSet() {
        return Collections.unmodifiableSet(symbolToRepresentativeMap.keySet());
    }
}
