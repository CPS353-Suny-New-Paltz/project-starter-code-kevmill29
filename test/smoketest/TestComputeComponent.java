package smoketest;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import concept.ComputeComponent;
import concept.ConceptAPIPrototype;
import concept.ImplementConceptAPI;

public class TestComputeComponent {
	ComputeComponent realComponent = new ImplementConceptAPI();
    @Test
    void conceptSmokeTestFail() {
    ConceptAPIPrototype prototype = new ConceptAPIPrototype();
    
    	prototype.prototype(realComponent);
    System.out.println(realComponent.computeValue(92));
        // Assertion purposefully failing test 
   assertNotEquals(realComponent.computeValue(92), 20);
    }
    @Test
	void conceptSmokeTestPass() {
        ConceptAPIPrototype prototype = new ConceptAPIPrototype();
    	prototype.prototype(realComponent);
    	System.out.println(realComponent.computeValue(1));
    	assertEquals(realComponent.computeValue(1), 204);
    	
    }
}

