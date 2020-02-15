package by.shakhrai.pavel.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class OutPutFileConfig {
    private static final String OUTPUT_FILENAME = "output.txt";
    private static File outputFile = new File(OUTPUT_FILENAME);


    private static void writeToOutputFile() {
        try {
            Files.write(Paths.get(outputFile.toURI()), "".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {

        }
    }

    public static File getOutputFile(){
        writeToOutputFile();
        return outputFile;
    }
}
