package conceptualAPI;

import java.io.IOException;
import java.util.HashMap;

import project.annotations.ConceptualAPIPrototype;

public class ConceptAPIPrototype {
@ConceptualAPIPrototype
	public void prototype(ConceptAPI component) {
//User Component user component will take in the processAPI and the userRequest as parameters and 
//create readable data for the Computation component to complete the computation
	int rand = (int) ((Math.random() * 2000) + 1);// create random jobID between 1 -2000
	int jobID = rand;
	try {
		HashMap<Integer, String>values = UserComponent.getValues(jobID);//creates hashmap with valueB generated along with jobID assigned
		//Computation Component
		System.out.println(ComputeValues.computeValues(values, jobID));
	}catch(IOException e) {
		System.out.println("Error reading valueA from file.");
	}

	
	
}
}
