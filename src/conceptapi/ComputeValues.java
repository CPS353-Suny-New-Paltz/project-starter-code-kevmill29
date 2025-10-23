package conceptapi;

import java.util.HashMap;
import java.util.List;

import assets.UserInputHandler;


public class ComputeValues  {
	//will finish this later but this will return a new hashMap again that includes the answer to be written into a file to be accessed later
	public int computeValues(HashMap<Integer, String>map) {
			//Used Problem 27 from Project EuleR
			UserInputHandler handler = new UserInputHandler();
			int valueA = handler.getValueA(map);
			int valueB = handler.getValueB(map);

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



//using this method to test integration
public int computeValues(int valueA) {
		//Used Problem 27 from Project EuleR
		UserInputHandler handler = new UserInputHandler();
		
		int valueB = 10; // using a static number for now for integration test results

		int n = 0;
		while(primeCount((n *n)+(valueA*n) + valueB)){
			n++;

		}
		return n;
}





public int computeValues(UserComponent userComponent) {
	// TODO Auto-generated method stub
	return 0;
}
}
