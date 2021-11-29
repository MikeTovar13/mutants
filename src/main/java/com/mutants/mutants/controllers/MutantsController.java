package com.mutants.mutants.controllers;

import com.mutants.mutants.model.ModelDNA;
import com.mutants.mutants.service.MutantsService;
import com.mutants.mutants.utils.Constants;
import com.mutants.mutants.utils.ResponseObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * Function for requests for state of general service
     * @return Response string with program version
     */
    @GetMapping("healthCheck")
    @ApiOperation(value = "Is Alive operation",
            notes = "Return is the microservice is alive with a get operation returning the version")
    public String healthCheck() {
        return Constants.API_REST_VERSION;
    }


    /**
     * Function for receive DNA Strand to test
     * @param dna
     * @return Response object with validation DNA (Is mutant or not)
     */
    @PostMapping("mutants")
    @ApiOperation(value = "Is a mutant DNA",
            notes = "Return 200 is a mutant or 403 is a human the DNA received")
    public ResponseEntity<Map<String, Object>> receiveDNA(
            @RequestBody ModelDNA dna) {

        try {
            log.info("Init request");
            return mutantsService.verifyADN(dna);

        } catch (Exception e) {
            log.info("Failed request, check input data");
            return new ResponseEntity<>(
                    new ResponseObject(Constants.HTTP_STATUS_500, Constants.MESSAGE.concat(e.toString()))
                            .mapObject(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Function show data about humans and mutants
     * @return Response object with data history in DB
     */
    @GetMapping("stats")
    @ApiOperation(value = "Stats saved",
            notes = "Return JSON con history data in DB con DNA old verified")
    public ResponseEntity<Map<String, Object>> generalStats() {
        return mutantsService.consultStats();
    }
}
