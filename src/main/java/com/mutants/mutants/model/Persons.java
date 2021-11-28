package com.mutants.mutants.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Persons {
    public Persons (String dna, Boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String dna;
    private Boolean isMutant;
}
