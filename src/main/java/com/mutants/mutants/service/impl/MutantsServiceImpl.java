package com.mutants.mutants.service.impl;

import com.mutants.mutants.logic.MutantsLogic;
import com.mutants.mutants.model.ModelDNA;
import com.mutants.mutants.service.MutantsService;
import com.mutants.mutants.utils.Constants;
import com.mutants.mutants.utils.ResponseObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Log4j2
@Service
public class MutantsServiceImpl implements MutantsService {

    private MutantsLogic mutantsLogic = new MutantsLogic();

    @Override
    public ResponseEntity<Map<String, Object>> verifyADN(ModelDNA dna) {

        log.info("Checking DNA strand: " + Arrays.toString(dna.getDna()));

        if (mutantsLogic.isMutant(dna)) {
            return new ResponseEntity<>(
                    new ResponseObject(Constants.HTTP_STATUS_200, "DNA belongs to a mutant")
                            .mapObject(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(
                    new ResponseObject(Constants.HTTP_STATUS_403, "DNA doesn't belong to a mutant")
                            .mapObject(), HttpStatus.FORBIDDEN);
        }
    }
}
