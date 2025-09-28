package conceptapi;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

public class ConceptSmokeTest {
	@Test
	void conceptSmokeTest() {
		ConceptAPI mockComponent = mock(ConceptAPI.class);

		//assertion
		assertDoesNotThrow(() ->{
			ConceptAPIPrototype prototype = new ConceptAPIPrototype();
			prototype.prototype(mockComponent);
		});
	}
}
