package smoketest;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import conceptapi.ComputeComponent;
import conceptapi.ImplementConceptAPI;

public class TestComputeComponent {
	ComputeComponent realComponent = new ImplementConceptAPI();
    @Test
    void conceptSmokeTestFail() {


    System.out.println(realComponent.computeValue(92));
        // Assertion purposefully failing test 
   assertNotEquals(realComponent.computeValue(92), 20);
    }
    @Test
	void conceptSmokeTestPass() {
    	
    	
    	System.out.println(realComponent.computeValue(1));
    	assertEquals(realComponent.computeValue(1), 0);
    	
    }
}

