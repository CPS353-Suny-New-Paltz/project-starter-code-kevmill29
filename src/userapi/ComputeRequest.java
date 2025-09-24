package userapi;

public class ComputeRequest {

public ComputeRequest( String delimiter, UserInput input) {
	super();
	
	if(delimiter == null || delimiter.isBlank()) {
		//default
		this.delimiter = ",";
	}else if(delimiter.matches("\\p{Punct}+")) {
		this.delimiter = delimiter;
	}
	this.input = input;
}



private String delimiter;
private UserInput input;




public UserInput getInput() {
	return input;
}



public void setInput(UserInput input) {
	this.input = input;
}



public void setDelimiter(String delimiter) {
	this.delimiter = delimiter;
}








public String getDelimiter() {
	return delimiter;
}

}
