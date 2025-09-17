package processapi;

public enum ProcessResponseSuccessCode {
			SUCCESS(true),
			FAILURE(false);
	
	private final boolean success;
	private ProcessResponseSuccessCode(boolean success) {
		this.success = success;
	}
	
	public boolean success() {
		return success;
	}
}
