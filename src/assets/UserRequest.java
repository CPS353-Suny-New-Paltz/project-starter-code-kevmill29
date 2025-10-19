package assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserRequest {
<<<<<<< Updated upstream
    private String inputSource;
    private String outputDestination;
    private String delimiter;
    private boolean useDefaultDelimiter;
=======
    private final String inputSource;
    private final String outputDestination;
    private final char delimiter;
    private final boolean useDefaultDelimiter;
    
>>>>>>> Stashed changes

    // Private constructor
    private UserRequest(Builder builder) {
        this.inputSource = builder.inputSource;
        this.outputDestination = builder.outputDestination;
        this.delimiter = builder.delimiter;
        this.useDefaultDelimiter = builder.useDefaultDelimiter;
    }

    // Getters
    public String getInputSource() {
        return inputSource;
    }

    public String getOutputDestination() {
        return outputDestination;
    }

    public char getDelimiter() {
        return delimiter;
    }

    
    //This will check if the userRequest is valid
    public boolean isValid() {
        return inputSource != null && !inputSource.isEmpty()
            && outputDestination != null && !outputDestination.isEmpty()
           ;
    }
    
    public RequestResponseCode secondValidation() {
    	if(inputSource == null || inputSource.isEmpty()) {
    		return RequestResponseCode.INPUTMISSING;
    	}
    	if(outputDestination == null || outputDestination.isEmpty()) {
    		return RequestResponseCode.NOOUTPUT;
    	}
    	
    	
    	return RequestResponseCode.SUCCESSRESPONSE;
    }
    

    // ✅ Builder Class
    public static class Builder {
        private String inputSource;
        private String outputDestination;
        private char delimiter;
        private boolean useDefaultDelimiter = false; // default to false
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        public Builder inputSource() throws IOException {
            this.inputSource = reader.readLine();
            return this;
        }

        public Builder outputDestination() throws IOException {
            this.outputDestination = reader.readLine();
            return this;
        }

        public Builder delimiter() throws IOException {
        	System.out.println("Enter a delimiter character(must be a symbol or a default delimiter will be assigned.)");
            String value = reader.readLine();
            
            char ch = value.charAt(0);
            if(!Character.isLetterOrDigit(ch) && !Character.isWhitespace(ch)) {
             this.delimiter = ch;
            }else {
            	this.delimiter = ',';
            }
            return this;
        }

       

        public UserRequest build() {
            return new UserRequest(this);
        }
    }
}
