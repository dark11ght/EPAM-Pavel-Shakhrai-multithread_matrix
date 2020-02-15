package by.shakhrai.pavel.input.reader;

import java.io.*;

public class InputFileReader {
    private File file;
    private int n;
    private int y;


    public InputFileReader(File file) {
        this.file = file;
        readMatrixParametrs();
    }


    public int getN() {
        return n;
    }

    public int getY() {
        return y;
    }


    private void readMatrixParametrs() {
        if (file != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

                String line = bufferedReader.readLine();
                if (line != null) {
                    String[] numbersStringArray = line.split(";");
                    n = Integer.parseInt(numbersStringArray[0]);
                    y = Integer.parseInt(numbersStringArray[1]);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}