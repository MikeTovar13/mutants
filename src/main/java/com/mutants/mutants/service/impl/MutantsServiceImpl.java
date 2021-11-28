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

    @Override
    public ResponseEntity<Map<String, Object>> verifyADN(ModelDNA dna) {

        log.info("Checking DNA strand: " + Arrays.toString(dna.getDna()));

        try {
            boolean isMutant = dnaObject.isMutant(dna.getDna());
            Persons p = new Persons(dna.toString(), isMutant);
            try{
                personsRepository.save(p);
             //   log.info("ppppppppppppppppppppppp" + personsRepository.countMutant());

            }catch (Exception e){

            }
            if (isMutant) {
                return new ResponseEntity<>(
                        new ResponseObject(Constants.HTTP_STATUS_200, "DNA belongs to a mutant")
                                .mapObject(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new ResponseObject(Constants.HTTP_STATUS_403, "DNA doesn't belong to a mutant")
                                .mapObject(), HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseObject(Constants.HTTP_STATUS_422, e.getMessage())
                            .mapObject(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
