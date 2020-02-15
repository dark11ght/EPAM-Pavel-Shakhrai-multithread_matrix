package by.shakhrai.pavel.entity;

import by.shakhrai.pavel.exception.IndexOutOfMaxtrixBoundsException;

import java.util.Arrays;


public class Matrix {
    private Element[][] matrix;
    private int matrixSize;

    private static class MatrixHolder {
        private static final Matrix INSTANCE = new Matrix();
    }

    public static Matrix getInstance() {
        return MatrixHolder.INSTANCE;
    }


    public void initMatrix(int matrixSize) {
        this.matrixSize = matrixSize;
        matrix = new Element[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = new Element(0);
            }
        }
    }

    public Element[][] getMatrix() {
        return matrix;
    }

    public Element getElement(int i, int j) {
        return matrix[i][j];
    }

    public int getElementValue(int i, int j) {
        Element currentElement = matrix[i][j];
        int elementValue = currentElement.getValue();
        return elementValue;
    }

    public boolean trySetValue(int i, int j, int value) throws IndexOutOfMaxtrixBoundsException {

        Element currentElement = matrix[i][j];
        try {
            return currentElement.changeElement(value);
        } finally {
            currentElement.unlock();
        }

    }

    public void setMatrix(Element[][] matrix) {
        this.matrix = matrix;
    }

    public int getMatrixSize() {
        return matrixSize;
    }

    public void setMatrixSize(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return Arrays.equals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                Element currentElement = matrix[i][j];
                int elementValue = currentElement.getValue();
                stringBuilder.append(String.format("%4d", elementValue));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
