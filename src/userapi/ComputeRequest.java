package userapi;

public class ComputeRequest {
private DataSource source;
private String delimiter;

public ComputeRequest(DataSource source, String delimiter) {
	super();
	this.source = source;
	if(delimiter == null || delimiter.isBlank()) {
		//default
		this.delimiter = ",";
	}else if(delimiter.equals(",")|| delimiter.equals("|")) {
		this.delimiter = delimiter;
	}
}

public DataSource getSource() {
	return source;
}
public String getDelimiter() {
	return delimiter;
}

}
