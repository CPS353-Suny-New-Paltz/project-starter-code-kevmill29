package userAPI;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface ComputeAPI {

	

	ComputeResponse run(ComputeRequest request);

}
