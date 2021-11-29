package com.mutants.mutants.logic;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Set;

@Log4j2
@Component
public class DNA {

    public static final int MIN_VALUE = 3;

    /**
     * Function principal from verify DNA strand
     * @param dna Strands in strings received for service
     * @return True or False (True mutant - False human)
     */
    public Boolean isMutant (String[] dna) throws Exception {

        Matrix matrix = new Matrix(convertToArrayChar(dna));
        if (isValidStrand(matrix)) {
            boolean valor = this.checkRowsColumnsAndDiagonals(matrix);
            log.info("Repeated DNA strand -> " + valor);
            return valor;
        } else {
            throw new Exception("Not Valid DNA Strand");
        }
    }

    /**
     * Function convert strand string to array char
     * @param dna Strands in strings received for service
     * @return New matrix generate for init tasks
     */
    private char[][] convertToArrayChar(String[] dna) {

        char[][] matrix = new char[dna.length][];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = dna[i].toCharArray();
        }
        return matrix;
    }

    /**
     * Function verify values in the matrix with allow letters
     * @param matrix matrix to evaluate characters
     * @return True or False accord to input data
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
     * Function to evaluate repeated characters in line
     * @param matrix matrix to evaluate
     * @return True if repeated is greater to 4
     */
    private boolean checkRowsColumnsAndDiagonals(Matrix matrix) {

        char[][] rows = matrix.getMatrix();
        char[][] cols = matrix.transposeMatrix();
        char[][] diagonalsForward = matrix.getDiagonals(true);
        char[][] diagonalsBackward = matrix.getDiagonals(false);
        int repeated = 0;

        for (int i = 0; i < matrix.getRows(); i++) {    // El primer índice recorre las filas.
            repeated += isRepeated(rows[i]);
            repeated += isRepeated(cols[i]);
            repeated += isRepeated(diagonalsForward[i]);
            repeated += isRepeated(diagonalsBackward[i]);

            if (repeated > 1){
                return true;
            }
        }
        return false;
    }

    /**
     * Function for evaluate just one of line and verified repeated
     * @param sequence Row, Column or diagonal to evaluate
     * @return True if found 2 chains of characters greater to 4 in the lines
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
