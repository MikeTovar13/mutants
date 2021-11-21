package com.mutants.mutants.service;

import com.mutants.mutants.model.ModelDNA;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;

public interface MutantsService {

    ResponseEntity<Map<String, Object>> verifyADN(ModelDNA dna) throws IOException;

}
