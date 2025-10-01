package smoketest;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import conceptapi.ConceptAPI;
import conceptapi.ConceptAPIPrototype;
import conceptapi.ImplementConceptAPI;

public class TestConceptAPI {
    @Test
    void conceptSmokeTest() {
        
        ImplementConceptAPI realComponent = new ImplementConceptAPI();

        // Assertion
   assertNotNull(realComponent);
    }
}
