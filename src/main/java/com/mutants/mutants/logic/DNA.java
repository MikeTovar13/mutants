package com.mutants.mutants.logic;

import com.mutants.mutants.model.ModelDNA;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Log4j2
@Component
public class DNA {

    public static final int MIN_VALUE = 3;

    public Boolean isMutant (String[] dna) {

        // Get length dna
        Integer dnaLength = dna.length;
        log.info("Tamano de la matriz: " + dnaLength);

        //log.info(Arrays.toString(dna));

        log.info("here ------->" + Arrays.toString(convertToArrayChar(dna)));
        Matrix matrix = new Matrix(convertToArrayChar(dna));
        boolean valor = this.checkRowsAndColumns(matrix);
        log.info("repeated dna strand -> "+valor);
        boolean valor2 = false;
        return valor || valor2;
    }

    private char[][] convertToArrayChar(String[] dna) {

        char[][] matrix = new char[dna.length][];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = dna[i].toCharArray();
        }

        return matrix;
    }

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


    private int isRepeated(char[] sequence){
        int count_repeated = 0;
        int i = 0, j=1;
        int found = 0;
        while (i<(sequence.length - MIN_VALUE)) {
            if (sequence[i] != sequence[j] && count_repeated >= MIN_VALUE) {
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
