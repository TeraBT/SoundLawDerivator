package mapping;

import java.util.List;
import java.util.Set;

public interface Orthography {
    public List<IPASymbol> mapToSymbolList(String representative);

    public String mapToRepresentative(List<IPASymbol> symbolList);

    public Set<String> getRepresentativeSet();

    public Set<List<IPASymbol>> getSymbolListSet();
}