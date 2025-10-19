package assets;

public enum ResponseCode {

	   SUCCESS(true),
	    FAILURE(false),
	    NULL_RESPONSE(false),
	    MISSING_OUTPUTSOURCE(false),
	    MISSING_INPUT(false);

	    private final boolean status;

	    ResponseCode(boolean status) {
	        this.status = status;
	    }

	    public boolean isSuccess() {
	        return status;
	    }

}
