package userapi;

import java.util.Scanner;
import java.util.UUID;

import project.annotations.NetworkAPIPrototype;

public class ComputeAPIPrototype {
	@NetworkAPIPrototype
	public void prototype(ComputeAPI compute) {
		//1. User Sends Compute Request which triggers 
		//User Input will come from here
		Scanner input = new Scanner(System.in);
		int valueA = 0;
		int valueB = 0;
		//For euler project 27 user cannot enter an integer value higher than 1000 or lower than -1000
		
		System.out.println("Enter value for a: \n");
		valueA = input.nextInt();
		if(valueA > 1000 || valueA < -1000 || valueA == 0) {
			System.out.println("Invalid input please try again!");
			System.out.println("Enter value for a: \n");
			valueA = input.nextInt();
		} 
		
		System.out.println("Enter value for b: \n");
		 valueB = input.nextInt();
		
		
		if(valueB > 1000 || valueB < -1000) {
			System.out.println("Invalid input please try again!");
			System.out.println("Enter value for b: \n");
			 valueB = input.nextInt();
		}
		
		
		UserInput user = new UserInput(valueA, valueB);
		//Output will go to this location
		OutputPath path = new OutputPath("path goes here");
		
		//Prompt user to input choose delimiter
		System.out.println("Select a delimiter, this has to be a punctuation mark! EX: !,/?");
		String delimiter = input.next();
	
	ComputeResponse code = new DefaultComputeResponse(ComputeResponseGetCode.FAILURE);
	ComputeRequest request= new ComputeRequest(delimiter, user);
	
		//3. Return Result
	
	if(request != null && request.getInput()!= null && request.getDelimiter()!= null) {
		code = new DefaultComputeResponse(ComputeResponseGetCode.SUCCESS);
	
	}
	
	
	if(((DefaultComputeResponse)code).isSuccess()) {
		ComputeResponse response = compute.run(request);
		System.out.println("This is a success!");
	}else {
		System.out.println("Error! Compute request rejected!");
	}
	
	
	}
}



