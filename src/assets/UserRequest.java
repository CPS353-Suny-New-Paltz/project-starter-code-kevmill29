package assets;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UserRequest {
    private final String inputSource;
    private final String outputDestination;
    private final String delimiter;
    private static UserRequest instance;
   

    // Private constructor
    private UserRequest(Builder builder) {
    
        this.inputSource = builder.inputSource;
        this.outputDestination = builder.outputDestination;
        this.delimiter = builder.delimiter;
     
    }

    // Getters
    public String getInputSource() {
        return inputSource;
    }

    public String getOutputDestination() {
        return outputDestination;
    }

    public String getDelimiter() {
        return delimiter;
    }
    

    public static void setInstance(UserRequest userRequest) {
        instance = userRequest;
    }
    
    public static UserRequest getInstance() {
        return instance;
    }

    


    //This will check if the userRequest is valid
    public UserRequestCode validation() {
    	if(getInputSource() == null) {
    		return UserRequestCode.NULL_INPUT;
    	}
    	if(getOutputDestination()==null) {
    		return UserRequestCode.NULL_OUTPUT;
    	}
    	
    	
    	return UserRequestCode.SUCCESS_RESPONSE;
    }

    // ✅ Builder Class
    public static class Builder {
        private String inputSource;
        private String outputDestination;
        private String delimiter;
        private int jobID;
        private BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));

        
        public Builder outputDestination(String outputSource) throws IOException {
        	System.out.println("Please enter a valid output destination. If no valid output destination is selected then a default destination will be selected");
//        	String outputDestination = reader.readLine();
            this.outputDestination = outputSource;
            return this;
        }

        public Builder delimiter(String delimiter) throws IOException {
        	String[] allowedLimiters = {",",".","/","-","|","*"};
//        	System.out.println("Please enter a valid delimiter from the following list: "+Arrays.toString(allowedLimiters));
//            delimiter = reader.readLine();
        	
        	boolean contains = Arrays.stream(allowedLimiters).anyMatch(delimiter::contains);
        	if(contains) {
        		this.delimiter = delimiter;
        	}else {
        		System.out.println("Cannot use this delimiter or no delimiter was selected. Using default delimiter");
        		this.delimiter = ",";
        	}
            return this;
        }
        
    

        public Builder inputSource(String inputSource) throws IOException{
        	System.out.println("Please enter an input source else if left blank or invalid file path, you will be prompted to enter a value instead.");
//        	inputSource = reader.readLine();
        	File file = new File(inputSource);
        	if(file.exists() && file.canRead()) {
        		this.inputSource = inputSource;	
        	}
            return this;
        }

        

        public UserRequest build() {
            return new UserRequest(this);
        }
    }
}
