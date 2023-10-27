package mapping;

import soundsystem.Phone;

import java.util.Set;

public interface PhoneticAlphabet {
    public Phone mapToPhone(IPASymbol symbol);

    public IPASymbol mapToSymbol(Phone phone);

    public Set<IPASymbol> getSymbolSet();

    public Set<Phone> getPhoneSet();
}
