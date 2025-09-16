package processapi;

public class DataStorePrototype {
//store bytes for now
	public void prototype(DataStore store) {
		store.InsertRequest(new DataRequest("hello world".getBytes()));
	}
}
