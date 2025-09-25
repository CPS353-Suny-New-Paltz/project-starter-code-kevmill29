package processapi;

public enum DataSuccessCode {
			SUCCESS(true),
			FAILURE(false);
	
	private final boolean success;
	private DataSuccessCode(boolean success) {
		this.success = success;
	}
	
	public boolean success() {
		return success;
	}
	
	
}
