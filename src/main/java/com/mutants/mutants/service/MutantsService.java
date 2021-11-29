package com.mutants.mutants.service;

import com.mutants.mutants.model.ModelDNA;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface MutantsService {

    ResponseEntity<Map<String, Object>> verifyADN(ModelDNA dna) throws Exception;

    ResponseEntity<Map<String, Object>> consultStats();

}
