package userapi;

import java.util.Scanner;
import java.util.UUID;

import project.annotations.NetworkAPIPrototype;

public class ComputeAPIPrototype {
	@NetworkAPIPrototype
	public void prototype(ComputeAPI compute) {
		//1. User Sends Compute Request which triggers 
		//User Input will come from here
		UserInput user = new UserInput("input goes here");
		//Output will go to this location
		OutputPath path = new OutputPath("path goes here");
		
		//Prompt user to input choose delimiter
		Scanner input = new Scanner(System.in);
		String delimiter = input.next();
		
	UserDataSource source=new UserDataSource(user.getInput());
	ComputeRequest request= new ComputeRequest(source , delimiter);
	
		//3. Return Result
		ComputeResponse response = compute.run(request);
		
		if(response.getResult().success()) {
			return;
		}else {
			System.err.println("Error! Compute request rejected!");
		}
		
		}
}


