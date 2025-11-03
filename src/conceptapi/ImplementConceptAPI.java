package conceptapi;

public class ImplementConceptAPI implements ComputeComponent {

//	@Override
//	public int computeValues(HashMap<Integer, String> map, int jobID) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public int computeValue(int valueA) {
		// This method will perform the computation using valueA from b = 10 -> b = 1000
	
		return ComputeValues.computeValues(valueA);
	}

}
