package conceptapi;

import java.util.HashMap;
import java.util.List;

import project.annotations.ConceptualAPI;
@ConceptualAPI
public interface ComputeComponent {
int computeValues(HashMap<Integer, String>map, int jobID);

int computeValues(UserComponent userComponent);
int computeValues(List<Integer> list);
}
