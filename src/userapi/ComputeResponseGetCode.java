package userapi;

public enum ComputeResponseGetCode {
				SUCCESS(true),
				FAILURE(false);

private final boolean success;
private ComputeResponseGetCode(boolean success){
	this.success = success;
}

public boolean success() {
	return success;
}


}


