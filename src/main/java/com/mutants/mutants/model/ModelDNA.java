package com.mutants.mutants.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ModelDNA {

    private String[] dna;

    public String toString(){
        StringBuilder dnaString = new StringBuilder();
        for (String strand: dna){
            dnaString.append(strand+"|");
        }
        return new String(dnaString.deleteCharAt(dnaString.length()-1));
    }

}
