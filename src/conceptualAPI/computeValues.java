package conceptualAPI;

import java.util.HashMap;

import assets.userInputHandler;

public class computeValues {
	//will finish this later but this will return a new hashMap again that includes the answer to be written into a file to be accessed later
	public static int computeValues(HashMap<Integer, String>map, int jobID) { 
			//Used Problem 27 from Project EuleR
			userInputHandler handler = new userInputHandler();
			int valueA = handler.getValueA(map, jobID);
			int valueB = handler.getValueB(map, jobID);
			
			int n = 0;
			while(primeCount((n *n)+(valueA*n) + valueB)){
				n++;
				
			}
			return n;	
	}


	

		
	public static boolean primeCount(int num) {
		if(num < 2) {
			return false;
		}
		for(int i = 2; i*i <= num; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true; 
	}

}
