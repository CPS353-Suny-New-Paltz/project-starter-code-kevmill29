package conceptapi;

import java.io.IOException;
import java.util.HashMap;

import assets.UserRequest;
import project.annotations.ConceptualAPIPrototype;



public class ConceptAPIPrototype {
@ConceptualAPIPrototype
	public void prototype(ComputeComponent component) {
//User Component user component will take in the processAPI and the userRequest as parameters and
//create readable data for the Computation component to complete the computation
	UserRequest request = request.getInstance();
	String inputSource= request.getInputSource();
	int value;
	try {
		UserInterface userComponent = new UserComponent();
		value = userComponent.getValue(inputSource);

		ComputeValues computation = new ComputeValues();
		int result=computation.computeValues(value);
		System.out.println("Computation result is: "+ result);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//creates hashmap with valueB generated along with jobID assigned
	//Computation Component
}
}
