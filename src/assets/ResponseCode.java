package assets;

public enum ResponseCode {

	   SUCCESS(true),
	    NULL_RESPONSE(false),
	    NULL_INPUT(false),
	    NULL_OUTPUT(false),
	    ;

	    private final boolean status;

	    ResponseCode(boolean status) {
	        this.status = status;
	    }

	    public boolean isSuccess() {
	        return status;
	    }

}
