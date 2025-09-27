package assets;

public enum responseCode {

	   SUCCESS(true),
	    FAILURE(false);

	    private final boolean status;

	    responseCode(boolean status) {
	        this.status = status;
	    }

	    public boolean isSuccess() {
	        return status;
	    }

}
