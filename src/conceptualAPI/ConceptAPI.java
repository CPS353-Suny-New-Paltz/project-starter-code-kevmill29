package conceptualAPI;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ConceptAPI {
ComputeComponent compute(UserComponent user);
}
