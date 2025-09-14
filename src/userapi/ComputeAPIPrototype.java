package userapi;

import java.util.UUID;

import project.annotations.NetworkAPIPrototype;

public class ComputeAPIPrototype {
	@NetworkAPIPrototype
	public void prototype(ComputeAPI compute, int valueA, int valueB, String delimiter) {
		//1. User Sends Compute Request which triggers 
	DataSource source = new UserDataSource(valueA, valueB);
	
	ComputeRequest request= new ComputeRequest(source , delimiter);
		//3. Return Result
		ComputeResponse response = compute.run(request);
		}
}


