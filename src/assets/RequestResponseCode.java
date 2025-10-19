package assets;

public enum RequestResponseCode {
	SUCCESSRESPONSE(ResponseCode.SUCCESS, () -> System.out.println("Request was successful!")),
	NULLRESPONSE(ResponseCode.NULL_RESPONSE, () -> System.out.println("Request was null, please try again!")),
	INPUTMISSING(ResponseCode.MISSING_INPUT, () -> System.out.println("Request is missing input, please try again!")),
	NOOUTPUT(ResponseCode.MISSING_OUTPUTSOURCE, ()-> System.out.println("Request is missing output source, please try again!"))
	;

	private final ResponseCode code;
	private final Runnable action;
	
	public ResponseCode getCode() {
		return code;
	}


	RequestResponseCode(ResponseCode code, Runnable action){
		this.action = action;
		this.code = code;
	}


}