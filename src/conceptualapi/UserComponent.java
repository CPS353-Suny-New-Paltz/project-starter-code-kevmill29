package conceptualapi;

public class UserComponent {
int jobID;



Object read;
String write; 

public UserComponent(int jobID, Object read, String write) {
	super();
	this.jobID = jobID;
	this.read = read;
	this.write = write;
}


public int getJobID() {
	return jobID;
}

public void setJobID(int jobID) {
	this.jobID = jobID;
}

public Object getRead() {
	return read;
}

public void setWrite(String write) {
	this.write = write;
}
}
