package mapping;

import java.util.Set;

public interface Orthography {
    public IPASymbol mapToSymbol(String representative);

    public String mapToRepresentative(IPASymbol symbol);

    public Set<String> getRepresentativeSet();

    public Set<IPASymbol> getSymbolSet();
}