package by.shakhrai.pavel.input.reader;

import java.io.*;

public class InputFileReader {
    private static final int N = 0;
    private static final int Y = 1;

    private File file;
    private int n;
    private int y;


    public InputFileReader(File file) {
        this.file = file;
    }


    public int getN() {
        return n;
    }

    public int getY() {
        return y;
    }


    private void readMatrixParametrs() {
        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                if (line != null) {
                    String[] numbersStringArray = line.split("x");
                    n = Integer.parseInt(numbersStringArray[N]);
                    y = Integer.parseInt(numbersStringArray[Y]);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}