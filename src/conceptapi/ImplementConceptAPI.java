package conceptapi;

import java.util.HashMap;
import java.util.List;

public class ImplementConceptAPI implements ComputeComponent {

	
	public ComputeComponent compute(UserComponent user) {
        return new ComputeValues(); // or any other real ComputeComponent implementation
    }

	@Override
	public int computeValues(HashMap<Integer, String> map, int jobID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int computeValues(List<Integer> of) {
		// TODO Auto-generated method stub
		return 0;
	}
}
