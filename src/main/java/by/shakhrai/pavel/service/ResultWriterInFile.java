package by.shakhrai.pavel.service;

import by.shakhrai.pavel.entity.Matrix;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResultWriterInFile extends Thread {
    private File outFile;
    private MatrixChangerService[] threads;


    public ResultWriterInFile(File file, MatrixChangerService[] threads) {
        this.outFile = file;
        this.threads = threads;
    }

    @Override
    public void run() {
        Matrix matrix = Matrix.getInstance();
        if (outFile != null) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile, true))) {
                bufferedWriter.write(matrix.toString());
                for (MatrixChangerService thread : threads) {
                    bufferedWriter.write("thread " + thread.getName()
                            + " sum = " + thread.getSum()
                            + System.lineSeparator());
                }
            } catch (IOException e) {

            }

        }
    }
}
