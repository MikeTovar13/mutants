package com.mutants.mutants.logic;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

@Log4j2
@Component
public class DNA {

    public static final int MIN_VALUE = 3;

    /**
     *
     * @param dna
     * @return
     */
    public Boolean isMutant (String[] dna) throws Exception {

        // Get length dna
        Integer dnaLength = dna.length;
        log.info("Tamano de la matriz: " + dnaLength);

        //log.info(Arrays.toString(dna));

        log.info("here ------->" + Arrays.toString(convertToArrayChar(dna)));
        Matrix matrix = new Matrix(convertToArrayChar(dna));
        if (isValidStrand(matrix)) {
            boolean valor = this.checkRowsAndColumns(matrix);
            log.info("repeated dna strand -> "+valor);
            boolean valor2 = false;
            return valor || valor2;
        } else {
            throw new Exception("Not Valid DNA Strand");
        }
    }

    /**
     *
     * @param dna
     * @return
     */
    private char[][] convertToArrayChar(String[] dna) {

        char[][] matrix = new char[dna.length][];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = dna[i].toCharArray();
        }

        return matrix;
    }

    /**
     *
     * @param matrix
     * @return
     */
    private Boolean isValidStrand(Matrix matrix) {

        if (!matrix.isSquare() || matrix.getRows() <= MIN_VALUE) {
            return false;
        } else {
            Set<Character> valuesAllow = Set.of('A', 'T', 'C', 'G');
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getCols(); j++) {
                    if (!valuesAllow.contains(matrix.getMatrix()[i][j])) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /**
     *
     * @param matrix
     * @return
     */
    private boolean checkRowsAndColumns(Matrix matrix) {

        char[][] rows = matrix.getMatrix();
        char[][] cols = matrix.transposeMatrix();
        int repeated = 0;

        for (int i = 0; i < matrix.getRows(); i++) {    // El primer índice recorre las filas.
            repeated += isRepeated(rows[i]);
            repeated += isRepeated(cols[i]);
            if (repeated > 1){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param sequence
     * @return
     */
    private int isRepeated(char[] sequence){

        int count_repeated = 0;
        int i = 0, j=1;
        int found = 0;

        while (i<(sequence.length - MIN_VALUE)) {
            if (j == sequence.length || (sequence[i] != sequence[j] && count_repeated >= MIN_VALUE)) {
                found++;
                i = j;
                j = i + 1;
                count_repeated = 0;
            } else if (sequence[i] != sequence[j]) {
                i = j;
                j = i + 1;
            } else {
                count_repeated++;
                j++;
            }
        }
        return found;
    }
}
