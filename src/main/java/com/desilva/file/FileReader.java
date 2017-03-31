package com.desilva.file;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by stevedesilva
 */
public interface FileReader {
    Optional<Stream<String>> fileToStream(String pathToFile);

    boolean fileValidator(String pathToFile);
}
