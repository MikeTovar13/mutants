package com.mutants.mutants.repository;

import com.mutants.mutants.model.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonsRepository extends JpaRepository<Persons, Integer> {

   // @Query("select count(*) from persons where is_mutant=1")
    //public Integer countMutant();
}
