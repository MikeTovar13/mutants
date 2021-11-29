package com.mutants.mutants.repository;

import com.mutants.mutants.model.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Integer> {

   /**
    * Function for get count mutants saved in DB
    * @return long mutants
    */
   @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM persons WHERE is_mutant = true")
   public long countMutant();

   /**
    * Function for get count humans saved in DB
    * @return long humans
    */
   @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM persons WHERE is_mutant = false")
   public long countNotMutant();

}
