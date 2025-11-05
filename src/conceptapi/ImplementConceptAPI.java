package conceptapi;
import assets.InvalidValueException;
public class ImplementConceptAPI implements ComputeComponent {

//	@Override
//	public int computeValues(HashMap<Integer, String> map, int jobID) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public int computeValue(int valueA) {
		//user component will validate the value for computation
		UserInterface validator = new UserComponent();
		// This method will perform the computation using valueA from b = 10 -> b = 1000
		if(validator.checkValue(valueA)) {
			throw new InvalidValueException("Value must be less than 1000 and more than -1000");
		}
	
		return ComputeValues.computeValues(valueA);
	}

}

