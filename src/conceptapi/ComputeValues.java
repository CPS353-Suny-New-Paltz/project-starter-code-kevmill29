package conceptapi;

public class ComputeValues  {
	//will finish this later but this will return a new hashMap again that includes the answer to be written into a file to be accessed later
//	public int computeValues(HashMap<Integer, String>map) {
//			//Used Problem 27 from Project EuleR
//			UserInputHandler handler = new UserInputHandler();
//			int valueA = handler.getValueA(map);
//			int valueB = handler.getValueB(map);
//
//			int n = 0;
//			while(primeCount((n *n)+(valueA*n) + valueB)){
//				n++;
//
//			}
//			return n;
//	}





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
public static int computeValues(int valueA) {
		//Used Problem 27 from Project EuleR
		
		int b = 0;
		
		// using a static number for now for integration test results
		int maxCount = 0;
		int n = 0;
		for(int valueB = 10; valueB < 1000;valueB++ ) {
			while(primeCount((n *n)+(valueA*n) + valueB)){
				n++;

			}
			//this will keep track of which valueB with the combination of of valueA gives the best result;
			if(n > maxCount) {
				maxCount = n;
				b = valueB;
			}
		}
		
		System.out.println("The best combination was: "+ valueA+" for a. " +b+ " for b. " +" The number of primes from this combo is: " + maxCount);
		
		return maxCount;
}






}
