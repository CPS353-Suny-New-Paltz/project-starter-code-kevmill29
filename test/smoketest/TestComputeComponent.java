package smoketest;




import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import conceptapi.ComputeComponent;
import conceptapi.ConceptAPIPrototype;
import conceptapi.ImplementConceptAPI;

public class TestComputeComponent {
    @Test
    void conceptSmokeTest() {
        
        ComputeComponent realComponent = new ImplementConceptAPI();

        // Assertion
   assertNotNull(realComponent);
    }
}
