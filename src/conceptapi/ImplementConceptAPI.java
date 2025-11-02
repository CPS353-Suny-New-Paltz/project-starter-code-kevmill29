package conceptapi;

import java.util.HashMap;
import java.util.List;

public class ImplementConceptAPI implements ComputeComponent {

//	@Override
//	public int computeValues(HashMap<Integer, String> map, int jobID) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public int computeValue(int valueA) {
		// TODO Auto-generated method stub
	
		return ComputeValues.computeValues(valueA);
	}

}
