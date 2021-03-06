package by.shakhrai.pavel;

import by.shakhrai.pavel.config.OutPutFileConfig;
import by.shakhrai.pavel.input.reader.InputFileReader;
import by.shakhrai.pavel.service.MatrixChangerService;
import by.shakhrai.pavel.service.MatrixInitThread;
import by.shakhrai.pavel.service.ResultWriterInFile;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {

        File outputFile = OutPutFileConfig.getOutputFile();

        File inputFile = new File("src/main/resources/input.txt");

        InputFileReader inputFileReader = new InputFileReader(inputFile);
        int n = inputFileReader.getN();
        int y = inputFileReader.getY();
       


MatrixInitThread matrixInitiator = new MatrixInitThread(n);
        matrixInitiator.start();
        try {
            matrixInitiator.join();
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        MatrixChangerService[] threads = new MatrixChangerService[n];
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n, new ResultWriterInFile(outputFile, threads));

        int id = 0;
        for (int i = 0; i < y; i++) {

            CountDownLatch syncLatch = new CountDownLatch(n);
            for (int j = 0; j < n; j++) {
                threads[j] = new MatrixChangerService(id++, syncLatch, cyclicBarrier);
                threads[j].start();
            }

            for (int j = 0; j < n; j++) {
                try {
                    threads[j].join();
                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                }
            }
        }

    }
}

