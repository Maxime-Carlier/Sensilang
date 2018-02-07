package fr.unice.polytech.dsl.sensilang.model.data.adapters;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class which serve as an adapter between files in the file system and our object representation
 */
public class FileSystemDataAdpter implements FileAdapter{

    private File dataFile;

    public FileSystemDataAdpter(String path) {
        if (!Files.exists(Paths.get(path))) {
            throw new IllegalArgumentException("The path must point to an existing file: " + path);
        }
        dataFile = new File(path);
    }

    @Override
    public File get() {
        return dataFile;
    }
}