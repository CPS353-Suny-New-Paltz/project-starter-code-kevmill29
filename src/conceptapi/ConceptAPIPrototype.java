package conceptapi;

import project.annotations.ConceptualAPIPrototype;



public class ConceptAPIPrototype {
@ConceptualAPIPrototype
	public void prototype(ComputeComponent component) {
//User Component user component will take in the processAPI and the userRequest as parameters and
//create readable data for the Computation component to complete the computation
component = new ImplementConceptAPI();
component.computeValue(0);
}
}
