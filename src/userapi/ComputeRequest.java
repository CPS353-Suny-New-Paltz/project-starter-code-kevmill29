package userapi;

public class ComputeRequest {
private UserDataSource source;
private String delimiter;


public ComputeRequest(UserDataSource source, String delimiter) {
	super();
	this.source = source;
	if(delimiter == null || delimiter.isBlank()) {
		//default
		this.delimiter = ",";
	}
	else if(delimiter.equals(",")|| delimiter.equals("|")) {
		this.delimiter = delimiter;
	}
}



public String getDelimiter() {
	return delimiter;
}

}
