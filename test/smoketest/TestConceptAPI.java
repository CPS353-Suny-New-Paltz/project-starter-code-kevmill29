package smoketest;




import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import conceptapi.ConceptAPI;
import conceptapi.ConceptAPIPrototype;
import conceptapi.ImplementConceptAPI;

public class TestConceptAPI {
    @Test
    void conceptSmokeTest() {
        
        ConceptAPI realComponent = new ImplementConceptAPI();

        // Assertion
   assertNotNull(realComponent);
    }
}
