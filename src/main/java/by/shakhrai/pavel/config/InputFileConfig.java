package by.shakhrai.pavel.config;

import java.io.File;
import java.net.URL;

public class InputFileConfig {

    private static final String INPUT_FILENAME = "input.txt";
    private static File inputFile = null;


    public static File getInputFile() {
        URL inputFileUrl = InputFileConfig.class.getClassLoader().getResource(INPUT_FILENAME);
        if (inputFileUrl != null) {
            inputFile = new File(inputFileUrl.getFile());
        } else {

        }
        return inputFile;
    }
}


