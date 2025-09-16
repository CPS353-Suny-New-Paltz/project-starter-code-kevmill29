package conceptualapi;

import project.annotations.ConceptualAPIPrototype;

public class ConceptAPIPrototype {
@ConceptualAPIPrototype
	public void prototype(ConceptAPI component) {
	//placeholders
	int jobId= 0;
	Object read = "";
	String input = "";
//User Component
	UserComponent user = new UserComponent(jobId, read, input);
	user.setJobID(jobId);
	user.setWrite(input);
	
//Computation Component
	ComputeComponent compute = component.compute(user);
}
}
