package com.mutants.mutants.service.impl;

import com.mutants.mutants.logic.DNA;
import com.mutants.mutants.model.ModelDNA;
import com.mutants.mutants.model.Persons;
import com.mutants.mutants.repository.PersonsRepository;
import com.mutants.mutants.service.MutantsService;
import com.mutants.mutants.utils.Constants;
import com.mutants.mutants.utils.ResponseObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class MutantsServiceImpl implements MutantsService {

    private DNA dnaObject = new DNA();
    private final PersonsRepository personsRepository;

    @Autowired
    public MutantsServiceImpl(PersonsRepository pr){
        this.personsRepository = pr;
    }

    /**
     * Function for validate dna and save in DB
     * @param dna model received for service
     * @return Response entity with 200 is a mutant, 403 is a human or 422 if dna is invalid
     * @throws Exception
     */
    @Override
    public ResponseEntity<Map<String, Object>> verifyADN(ModelDNA dna) throws Exception {

        log.info("Checking DNA strand: " + Arrays.toString(dna.getDna()));
        boolean isMutant;
        ResponseObject object = new ResponseObject();
        HttpStatus status = HttpStatus.OK;
        try {
            isMutant = dnaObject.isMutant(dna.getDna());
            Persons p = new Persons(dna.toString(), isMutant);
            personsRepository.save(p);
            if (isMutant){
                object.setMessage("DNA belongs to a mutant");
                object.setStatus(Constants.HTTP_STATUS_200);
                status = HttpStatus.OK;
            }else{
                object.setMessage("DNA doesn't belong to a mutant");
                object.setStatus(Constants.HTTP_STATUS_403);
                status = HttpStatus.FORBIDDEN;
            }

        }catch (IllegalArgumentException e) {
            log.info("This DNA was already register in Database" + e.toString());
        }catch (Exception e) {
            log.info(e.toString());
            object.setMessage("This DNA was already register in Database - Error saved: " + e.getMessage());
            object.setStatus(Constants.HTTP_STATUS_422);
            status = HttpStatus.UNPROCESSABLE_ENTITY;

        }finally{
            return  new ResponseEntity<>(object.mapObject(), status);
         }
    }

    /**
     * Function for get data in DB and response service
     * @return Response entity with JSON for history data in DB
     */
    @Override
    public ResponseEntity<Map<String, Object>> consultStats() {

        long mutants = personsRepository.countMutant();
        long humans = personsRepository.countNotMutant();

        Map<String, Object> data = new HashMap<>();
        data.put("count_mutant_dna", mutants);
        data.put("count_human_dna", humans);
        if (humans == 0) {
            humans = 1;
        }
        data.put("ratio", (float)mutants/(float)humans);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
