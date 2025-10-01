package smoketest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import conceptapi.ConceptAPI;
import conceptapi.ConceptAPIPrototype;
import conceptapi.ImplementConceptAPI;

public class TestConceptAPI {
    @Test
    void conceptSmokeTest() {
        // Explicit constructor call for Spoon to detect
        ConceptAPI realComponent = new ImplementConceptAPI();

        // Assertion
        assertDoesNotThrow(() -> {
            ConceptAPIPrototype prototype = new ConceptAPIPrototype();
            prototype.prototype(realComponent);
        });
    }
}
