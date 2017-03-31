package com.desilva.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by stevedesilva
 */
public class FileReaderImp implements FileReader {

    public Optional<Stream<String>> fileToStream(String pathToFile) {

        Stream<String> stream = null;
        if (fileValidator(pathToFile)) {
            try {
                stream = Files.lines(Paths.get(pathToFile));
            } catch (IOException e) {
                Optional.empty();
            }
        }

        return Optional.ofNullable(stream);
    }

    public boolean fileValidator(String pathToFile) {

        if (pathToFile == null) {
            return false;
        }

        // is a file
        final File inputFile = new File(pathToFile);
        if (!inputFile.exists() || !inputFile.isFile()) {
            return false;
        }

        // Valid file
        return true;
    }

}
