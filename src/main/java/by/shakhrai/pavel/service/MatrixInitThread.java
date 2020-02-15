package by.shakhrai.pavel.service;

import by.shakhrai.pavel.entity.Matrix;

public class MatrixInitThread  extends Thread{
    private int matrixSize;

    public MatrixInitThread(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    @Override
    public void run() {
        Matrix.getInstance().initMatrix(matrixSize);
    }
}

