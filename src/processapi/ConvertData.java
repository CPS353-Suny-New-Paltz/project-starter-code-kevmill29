package processapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import assets.DelimiterContext;


public class ConvertData {
	public static HashMap<Integer, ArrayList<ArrayList<Integer>>> convertData (){
		File file = new File("src/Output/output.txt");
		Scanner scanner = null;
		String delimiter = DelimiterContext.getDelimiter();
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//Create array list for 2d arraylist
		ArrayList<Integer> values1 = new ArrayList<>();
		ArrayList<Integer>values2 = new ArrayList<>();
		//2d arraylist to nest inside of hashMap so that a job ID can be matched to the values entered in allowing easier access to search
		ArrayList<ArrayList<Integer>> values = new ArrayList<>();
		HashMap<Integer, ArrayList<ArrayList<Integer>>>map = new HashMap<>();
		
		int jobID = 0;
				
				//Scanner will parse the file with the written values and separate it by the delimiter entered
				
				while(scanner.hasNext()) {
					jobID =  (int)(Math.random()* 2000)+1; //allowing 2000 unique job ids to be created
					String line = scanner.nextLine(); //reads next line 
					String[]value=line.split(delimiter); // splits values into a String array using the delimiter
					values1.add(Integer.parseInt(value[0])); //checks values inside of string and adds to arraylists
					values2.add(Integer.parseInt(value[1]));
					//add to the 2d arraylist and place inside of hashmap with a key
					values.add(values1);
					values.add(values2);
					map.put(jobID, values);
	}
				
				return map;
	}

}
