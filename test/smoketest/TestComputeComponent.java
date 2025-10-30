package smoketest;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import conceptapi.ComputeComponent;
import conceptapi.ConceptAPIPrototype;
import conceptapi.ImplementConceptAPI;

public class TestComputeComponent {
	ComputeComponent realComponent = new ImplementConceptAPI();
    @Test
    void conceptSmokeTestFail() {

        // Assertion purposefully failing test 
   assertNotEquals(realComponent.computeValues(92), 0);
    }
    @Test
	void conceptSmokeTestPass() {
    	assertEquals(realComponent.computeValues(0), 0);
    	
    }
}

