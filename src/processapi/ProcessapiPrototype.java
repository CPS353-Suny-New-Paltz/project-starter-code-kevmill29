package processapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import project.annotations.ProcessAPIPrototype;


public class ProcessapiPrototype {
	@ProcessAPIPrototype
			public HashMap<Integer, ArrayList<ArrayList<Integer>>> processAPI(Processapi process) {
		//Runs conversion from txt document to hashMap
		HashMap<Integer, ArrayList<ArrayList<Integer>>> newMap = ConvertData.convertData();
				 

	    if (newMap.isEmpty()) {
	        System.out.println("Conversion failed: No data was added to the map.");
	       
	    } else {
	        System.out.println("Conversion successful: " + newMap.size() + " entries added.");
	    }

			
				
			
			 return newMap;
		}
}
