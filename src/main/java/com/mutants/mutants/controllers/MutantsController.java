package com.mutants.mutants.controllers;

import com.mutants.mutants.model.ModelDNA;
import com.mutants.mutants.service.MutantsService;
import com.mutants.mutants.utils.Constants;
import com.mutants.mutants.utils.ResponseObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@Log4j2
public class MutantsController {

    MutantsService mutantsService;

    public MutantsController(MutantsService mutantsService) {
        this.mutantsService = mutantsService;
    }

    /**
     * Funtion for requests for state of general service
     * @return Response string with program version
     */
    @GetMapping("healthCheck")
    public String healthCheck() {
        return Constants.API_REST_VERSION;
    }


    @PostMapping("mutants")
    public ResponseEntity<Map<String, Object>> receiveDNA(
            @RequestBody ModelDNA dna) {

        try {
            log.info("Init request");
            return mutantsService.verifyADN(dna);
        } catch (IOException e) {
            log.info("Failed request, check input data");
            return new ResponseEntity<>(
                    new ResponseObject(Constants.HTTP_STATUS_500, Constants.MESSAGE.concat(e.toString()))
                            .mapObject(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
