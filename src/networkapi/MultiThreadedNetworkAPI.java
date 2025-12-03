package networkapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import assets.UserRequest;
import conceptapi.ComputeComponent;

public class MultiThreadedNetworkAPI implements NetworkInterfaceAPI{
	//creating wrapper/decorator implementation
	private final ImplementNetworkAPI delegator;
	private final ExecutorService pool;
	
	
	public MultiThreadedNetworkAPI(ImplementNetworkAPI delegator, int nThreads) {
		super();
		this.delegator = delegator;
		this.pool = Executors.newFixedThreadPool(nThreads);
	}
	
	public MultiThreadedNetworkAPI(ImplementNetworkAPI delegator) {
		this(delegator, Runtime.getRuntime().availableProcessors());
	}


	@Override
	public List<Integer> respond(String input, String output, char delimiter) {
		UserRequest request = buildRequest(input, output, delimiter);

		return null;
	}

	@Override
	public boolean initialize(UserRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> readRequest(UserRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int respond(boolean isInit, int valueA, ComputeComponent concept) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeRequest(List<Integer> results, UserRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserRequest buildRequest(String input, String output, char delimiter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<String> processRequests(List<String> requests) {
	    if (requests == null) {
	        return Collections.emptyList();
	    }

	    // This is not computing anything, still trying to figure out the logic for this one here
	    List<String> results = new ArrayList<>();

	    for (String req : requests) {
	        if (req == null || req.trim().isEmpty()) {
	            results.add("0"); // default placeholder
	        } else {
	            results.add("0"); // or req — ANY value is fine as long as sizes match
	        }
	    }

	    return results;
	}

}
