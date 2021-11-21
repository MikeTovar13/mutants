package com.mutants.mutants.logic;

import com.mutants.mutants.model.ModelDNA;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Log4j2
@Component
public class MutantsLogic {

    public Boolean isMutant (ModelDNA dna) {

        // Get length dna
        Integer dnaLength = dna.getDna().length;
        log.info("Tamano de la matriz: " + dnaLength);

        log.info(Arrays.toString(dna.getDna()));

        Boolean valor = this.checkRowsAndColumns(dnaLength, dna.getDna());


        return valor;
    }


    private Boolean checkRowsAndColumns(Integer size, String[] matrix) {

            //int[][] matriz = new int[50][100]; // Matriz de números enteros que supondremos llena.
            // 50 filas y 100 columnas.

            for (int i = 0; i < size; i++) {    // El primer índice recorre las filas.
                for (int j = 0; j < size; j++) {    // El segundo índice recorre las columnas.
                    // Procesamos cada elemento de la matriz.
                    System.out.println(matrix[i]);
                }
            }

            return false;
    }

}
