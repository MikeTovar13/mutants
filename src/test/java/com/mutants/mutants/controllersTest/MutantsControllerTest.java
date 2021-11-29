package com.mutants.mutants.controllersTest;

import com.mutants.mutants.controllers.MutantsController;
import com.mutants.mutants.model.ModelDNA;
import com.mutants.mutants.service.MutantsService;
import com.mutants.mutants.utils.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class MutantsControllerTest {

    public static final String[] OK_DNA = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    public static final String[] FAILED_DNA = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCKCTA","TCACTG"};

    @Mock
    MutantsService mutantsService;

    @InjectMocks
    private MutantsController mutantsController;


    @Test
    public void checkHealtOk() {
        String response = mutantsController.healthCheck();
        Assert.assertNotNull(response);
        Assert.assertEquals(Constants.API_REST_VERSION, response);
    }

    @Test
    public void receiveDNAOk() throws Exception {

        Mockito.when(mutantsService.verifyADN(Mockito.any(ModelDNA.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ModelDNA modelDNA = new ModelDNA(OK_DNA);

        ResponseEntity<?> controllerResponse = mutantsController.receiveDNA(modelDNA);
        Assert.assertEquals(200, controllerResponse.getStatusCodeValue());
    }

    @Test
    public void receiveDNAFailed() throws Exception {

        Mockito.when(mutantsService.verifyADN(Mockito.any(ModelDNA.class)))
                .thenThrow(new Exception());
        ModelDNA modelDNA = new ModelDNA(FAILED_DNA);

        ResponseEntity<?> controllerResponse = mutantsController.receiveDNA(modelDNA);
        Assert.assertEquals(500, controllerResponse.getStatusCodeValue());
    }

    @Test
    public void statsGeneralsOk() {

        Mockito.when(mutantsService.consultStats()).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> controllerResponse = mutantsController.generalStats();
        Assert.assertEquals(200, controllerResponse.getStatusCodeValue());
    }

}
