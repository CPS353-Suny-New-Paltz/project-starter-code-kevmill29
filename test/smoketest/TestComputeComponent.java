package smoketest;




import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import conceptapi.ComputeComponent;
import conceptapi.ConceptAPIPrototype;
import conceptapi.ImplementConceptAPI;

public class TestComputeComponent {
	ComputeComponent realComponent = new ImplementConceptAPI();
    @Test
    void conceptSmokeTestFail() {

    ConceptAPIPrototype prototype = new ConceptAPIPrototype();
    prototype.prototype(realComponent);
        // Assertion purposefully failing test 
   assertEquals(realComponent.computeValues(92), 0);
    }
    @Test
	void conceptSmokeTestPass() {
    	ConceptAPIPrototype prototype = new ConceptAPIPrototype();
    	prototype.prototype(realComponent);
    	assertEquals(realComponent.computeValues(0), 0);
    	
    }
}

