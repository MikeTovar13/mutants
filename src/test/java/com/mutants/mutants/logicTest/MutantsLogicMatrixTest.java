package com.mutants.mutants.logicTest;

import com.mutants.mutants.logic.DNA;
import com.mutants.mutants.logic.Matrix;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
public class MutantsLogicMatrixTest {

    private Matrix matrix;

    private static Iterable dataMatrix(){
        return Arrays.asList(
                new Object[][]{
                        {new char[][]{{'a', 'b', 'c'},{'d','e','f'}}, false},
                        {new char[][]{{'a', 'b', 'c'},{'d','e','f'},{'g','h', 'i'}}, true},
                        {new char[][]{{'a', 'b', 'c'},{'d','e'},{'g','h', 'i'}}, false},
                });
    }

    @Test
    public void getDiagonalsTest(){
        char [][] initial = new char[][] { {'a', 'b', 'c'},{'d','e','f'}, {'g','h','i'}};
        char [][] expectedForward = new char[][] { {'a'},{ 'b', 'd'},{'c','e', 'g'},{'f', 'h'}, {'i'}};
        char [][] expectedBackward = new char[][] { {'c'},{ 'b', 'f'},{'a','e', 'i'},{'d', 'h'}, {'g'}};

        matrix = new Matrix(initial);
        Assert.assertEquals(expectedBackward, matrix.getDiagonals(true));
        Assert.assertEquals(expectedForward, matrix.getDiagonals(false));
    }

    @Test
    public void getTransposeTest(){
        char [][] initial = new char[][] { {'a', 'b', 'c'},{'d','e','f'}};
        char [][] expected = new char[][] { { 'a', 'd'},{'b','e'},{'c','f'}};

        matrix = new Matrix(initial);
        Assert.assertEquals(expected, matrix.transposeMatrix());
    }

    @Test
    public void isSquareTest(){
        for (Object obj: dataMatrix()){
            Object[] data = (Object[])obj;
            char[][] dna_matrix = (char[][])data[0];
            boolean is_square = (boolean) data[1];
            matrix = new Matrix(dna_matrix);
            Assert.assertEquals(is_square, matrix.isSquare());
        }
    }

    @Test
    public void getColsAndRowsTest(){
        char [][] initial = new char[][] { {'a', 'b', 'c'},{'d','e','f'}};
        matrix = new Matrix(initial);
        int cols = 3;
        int rows = 2;

        Assert.assertEquals(cols, matrix.getCols());
        Assert.assertEquals(rows, matrix.getRows());
    }

    @Test
    public void getMatrixTest(){
        char [][] initial = new char[][] { {'a', 'b', 'c'},{'d','e','f'}};
        matrix = new Matrix(initial);
        Assert.assertEquals(initial, matrix.getMatrix());

    }


}
