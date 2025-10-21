package conceptapi;

import java.util.HashMap;
import java.util.List;

import project.annotations.ConceptualAPI;
@ConceptualAPI
public interface ComputeComponent {


int computeValues(List<Integer> pair);
//int computeValues(HashMap<Integer, String>map, int jobID); will be used later



}
