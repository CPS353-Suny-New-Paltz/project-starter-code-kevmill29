package userapi;

public enum ComputeResponseSuccessCode {
				SUCCESS(true),
				FAILURE(false);

private final boolean success;
private ComputeResponseSuccessCode(boolean success){
	this.success = success;
}

public boolean success() {
	return success;
}


}


