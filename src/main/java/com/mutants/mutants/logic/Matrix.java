package com.mutants.mutants.logic;

import java.util.ArrayList;

public class Matrix {

    private char[][] matrix;
    private int cols;
    private int rows;

    public Matrix (char[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    /**
     *
     * @return
     */
    public Boolean isSquare() {
        boolean square = false;
        if (this.cols == this.rows) {
            square = true;
        }
        return square;
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

    public char[][] getDiagonals(){

        ArrayList[] diagonalsF = new ArrayList[this.rows +this.cols-1];
        char[][] diagonalsB = new char[this.rows+this.cols - 1][];

        for (int i = 0; i< this.rows; i++){
            for (int j=0; j< this.cols; j++) {
                if (diagonalsF[i+j] == null){
                    diagonalsF[i+j] = new ArrayList<Character>();
                }
                diagonalsF[i+j].add(this.matrix[i][j]);
            }

        }
        for (int i =0; i<diagonalsF.length;i++){
            diagonalsB[i] = new char[diagonalsF[i].size()];
            for (int j=0; j< diagonalsF[i].size();j++){

                diagonalsB[i][j] = (char)diagonalsF[i].get(j);

            }
        }
        return  diagonalsB;
    }


    public char[][] getMatrix() {
        return matrix;
    }

}
