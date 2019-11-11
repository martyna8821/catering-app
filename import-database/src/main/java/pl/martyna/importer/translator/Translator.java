package pl.martyna.importer.translator;

import java.util.Optional;

@FunctionalInterface
public interface Translator {

    public String translate(String item);
}
