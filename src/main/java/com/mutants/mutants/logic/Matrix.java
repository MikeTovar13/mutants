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
     * Function for verify matrix square
     * @return True (NxN) or False (NxM)
     */
    public Boolean isSquare() {
        boolean square = true;
        for(char[] row : this.matrix){
            if (row.length != this.rows){
                square = false;
                break;
            }
        }
        return square;
    }

    /**
     * Function for transpose matrix -> Columns to rows
     * @return matrix transpose
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

    /**
     * Function for get diagonals in matrix
     * @param back If back = true get diagonals right to left, if false get them from left to right
     * @return new matrix with diagonals as rows
     */
    public char[][] getDiagonals(boolean back){

        ArrayList[] diagonalsF = new ArrayList[this.rows +this.cols-1];
        char[][] diagonalsB = new char[this.rows+this.cols - 1][];

        for (int i = 0; i< this.rows; i++){
            for (int j=0; j< this.cols; j++) {
                int index = i+j;
                if (back){
                    index = i-j+this.rows-1;
                }
                if (diagonalsF[index] == null){
                        diagonalsF[index] = new ArrayList<Character>();
                    }
                diagonalsF[index].add(this.matrix[i][j]);
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
