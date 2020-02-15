package by.shakhrai.pavel.service;

import by.shakhrai.pavel.entity.Matrix;
import by.shakhrai.pavel.exception.IndexOutOfMaxtrixBoundsException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MatrixChangerService extends Thread{
    private int id;
    private int sum;
    private CountDownLatch countDownLatch;
    private CyclicBarrier cyclicBarrier;
    private Matrix matrix;

    public MatrixChangerService(int id, CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier) {
        super(String.valueOf(id));
        this.id = id;
        this.countDownLatch = countDownLatch;
        this.cyclicBarrier = cyclicBarrier;
        matrix = Matrix.getInstance();
    }

    @Override
    public void run() {

        try {
            int diagonalIndex = setMatrixDiagonalValue();

            if (isRowChosen()) {
                setMatrixRowValue(diagonalIndex);
            } else {
                setMatrixColumnValue(diagonalIndex);
            }

            countDownLatch.countDown();
            countDownLatch.await();

            sum = calculateSumRowAndColumn(diagonalIndex);

            cyclicBarrier.await();

        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {

            Thread.currentThread().interrupt();
        } catch (IndexOutOfMaxtrixBoundsException e) {

        }
    }

    private void setMatrixRowValue(int diagonalIndex) throws IndexOutOfMaxtrixBoundsException {
        int index = getRandomIndex(matrix.getMatrixSize());
        while (index == diagonalIndex || !matrix.trySetValue(diagonalIndex, index, id)) {
            index = getRandomIndex(matrix.getMatrixSize());
        }
    }

    private void setMatrixColumnValue(int diagonalIndex) throws IndexOutOfMaxtrixBoundsException {
        int index = getRandomIndex(matrix.getMatrixSize());
        while (index == diagonalIndex || !matrix.trySetValue(index, diagonalIndex, id)) {
            index = getRandomIndex(matrix.getMatrixSize());
        }
    }

    private boolean isRowChosen() {
        return new Random().nextBoolean();
    }

    private int setMatrixDiagonalValue() throws IndexOutOfMaxtrixBoundsException {
        int diagonalIndex = getRandomIndex(matrix.getMatrixSize());
        while (!matrix.trySetValue(diagonalIndex, diagonalIndex, id)) {
            diagonalIndex = getRandomIndex(matrix.getMatrixSize());
        }
        return diagonalIndex;
    }

    private int getRandomIndex(int maxValue) {
        return new Random().nextInt(maxValue);
    }

    private int calculateSumRowAndColumn(int diagonalIndex) {
        int rowSum = 0;
        int columnSum = 0;
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            rowSum += matrix.getElementValue(i, diagonalIndex);
            columnSum += matrix.getElementValue(diagonalIndex, i);
        }
        return rowSum + columnSum - id;
    }

    public int getSum() {
        return sum;
    }


}
