package assets;

public enum UserRequestCode {
		SUCCESS_RESPONSE(ResponseCode.SUCCESS, ()-> System.out.println("Request successfully received, please wait!")),
		NULL_RESPONSE(ResponseCode.NULL_RESPONSE, ()-> System.out.println("Request is null, please enter a response!")),
		NULL_INPUT(ResponseCode.NULL_INPUT, () -> System.out.println("No input has been found, please enter an input location or enter a value when prompted.")),
		NULL_OUTPUT(ResponseCode.NULL_OUTPUT, ()-> System.out.println("No output destination specified/or incorrect ouput destination specified, please try again!"));
		
		;
		private final Runnable action;
		private final ResponseCode request;
		private UserRequestCode(ResponseCode request, Runnable action) {
			this.request = request;
			this.action = action;
		}
		
		
		
}
