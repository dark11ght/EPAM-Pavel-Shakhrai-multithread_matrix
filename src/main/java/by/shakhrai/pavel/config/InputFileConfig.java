package by.shakhrai.pavel.config;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class InputFileConfig {
    private static final String OUTPUT_FILENAME = "output.txt";
    private static final String INPUT_FILENAME = "input.txt";

    private static File outputFile = new File(OUTPUT_FILENAME);
    private static File inputFile = null;

    static {
        try {
            Files.write(Paths.get(outputFile.toURI()), "".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {

        }

        URL inputFileUrl = InputFileConfig.class.getClassLoader().getResource(INPUT_FILENAME);
        if (inputFileUrl != null) {
            inputFile = new File(inputFileUrl.getFile());
        } else {

        }
    }
}
