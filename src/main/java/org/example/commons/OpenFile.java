package org.example.commons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OpenFile {

    public List<String> readAllFile(final String path) throws IOException {
        return returnFileLines(definePath(path));
    }

    private Path definePath(final String pathString) {
        return Paths.get(pathString);
    }

    private List<String> returnFileLines(final Path path) throws IOException {
        return Files.readAllLines(path);
    }

}
