import java.util.Scanner;

import userapi.ComputeAPIPrototype;

public class Main {
	public static void main(String[]args) {
		
		//Used Problem 27 from Project Euler
		Scanner input = new Scanner(System.in);
		
		//Prompt user to enter in numbers 
		System.out.println("Find the quadratic expression that produces the maximum number of primes!\nEnter two values for a and b for the equation n2 + an + b.");
		System.out.println("Enter value for a: \n");
		int valueA = input.nextInt();
		while(valueA > 1000 || valueA < -1000) {
			System.out.println("Invalid input please try again!");
			System.out.println("Enter value for a: \n");
			valueA = input.nextInt();
		}
		System.out.println("Enter value for b: \n");
		int valueB = input.nextInt();
		while(valueB > 1000 || valueB < -1000) {
			System.out.println("Invalid input please try again!");
			System.out.println("Enter value for b: \n");
			 valueB = input.nextInt();
		}

		int n = 0;
	

		while(primeCount((n *n)+valueA*n + valueB)){
			n++;
		}
		
//		ComputeAPIPrototype proto=new ComputeAPIPrototype();
//		proto.prototype(compute, valueA, valueB);
	
System.out.println("Number of consective primes starting from n = 0 :     "+ n);
input.close();

//ask user for values of a and b


}

	public static boolean primeCount(int num) {
		if(num < 2) {
			return false;
		}
		for(int i = 2; i*i <= num; i++) {
			if(num % i == 0)return false;
		}
		return true; 
	}
	
	

}
