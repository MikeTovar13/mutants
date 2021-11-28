package com.mutants.mutants.logic;

public class Matrix {

    private char[][] matrix;

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    private int cols = 0;
    private int rows = 0;

    public Matrix (char[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
    }

    /**
     *
     * @return
     */
    public Boolean isSquare() {
        if (this.cols == this.rows) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public char[][] transposeMatrix() {

        char[][] columns = new char[this.cols][];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (columns[j] == null){
                    columns[j] = new char[this.rows];
                }
                columns[j][i] = this.matrix[i][j];
            }
        }

        return columns;
    }

  /*  public char[][] getDiagonals(){
        char[][] diagonalsF = new char[this.rows+this.cols - 1][];
        //char[][] diagonalsB = new char[this.rows+this.cols - 1][];

        for (int i = 0; i< this.rows; i++){
            for (int j=0; j< this.cols; j++) {

                diagonalsF[i+j] = new char[];
            }

        }
        return  diagonals;
    }*/


    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }
}
