package processapi;

import java.util.ArrayList;
import java.util.HashMap;

public class DataStorePrototype {
//store bytes for now
	public void prototype(DataStore store) {
		HashMap<Integer, ArrayList<ArrayList<Integer>>> map = ConvertData.convertData();
		//checks if map is empty if not sends insert request to the dataStore
		if(!map.isEmpty()) {
			store.InsertRequest(new DataRequest("hello world".getBytes()));
		}
		
	}
}
