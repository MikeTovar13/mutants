package com.mutants.mutants.logicTest;

import com.mutants.mutants.logic.Matrix;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class MutantsLogicMatrixTest {

    private Matrix matrix;

    @Test
    public void getDiagonalsTest(){
        char [][] initial = new char[][] { {'a', 'b', 'c'},{'d','e','f'}};
        char [][] expected = new char[][] { {'a'},{ 'b', 'd'},{'c','e'},{'f'}};

        matrix = new Matrix(initial);
        Assert.assertEquals(expected, matrix.getDiagonals());

    }

    @Test
    public void getTransposeTest(){
        char [][] initial = new char[][] { {'a', 'b', 'c'},{'d','e','f'}};
        char [][] expected = new char[][] { { 'a', 'd'},{'b','e'},{'c','f'}};

        matrix = new Matrix(initial);
        Assert.assertEquals(expected, matrix.transposeMatrix());
    }

    @Test
    public void isNotSquareTest(){
        char [][] initial = new char[][] { {'a', 'b', 'c'},{'d','e','f'}};
        matrix = new Matrix(initial);
        Assert.assertEquals(false, matrix.isSquare());
    }

    @Test
    public void isSquareTest(){
        char [][] initial = new char[][] { {'a', 'b', 'c'},{'d','e','f'}, {'g','h', 'i'}};
        matrix = new Matrix(initial);
        Assert.assertEquals(true, matrix.isSquare());
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
