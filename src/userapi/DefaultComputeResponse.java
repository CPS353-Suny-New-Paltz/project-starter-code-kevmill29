package userapi;

public class DefaultComputeResponse implements ComputeResponse {

	
	private final ComputeResponseGetCode result;

	public ComputeResponseGetCode getResult() {
		return result;
	}

	public DefaultComputeResponse(ComputeResponseGetCode result) {
		super();
		this.result = result;
	}
	
	public boolean isSuccess() {
		return result == ComputeResponseGetCode.SUCCESS;
	}
	
	public boolean isFailure() {
		return result == ComputeResponseGetCode.FAILURE;
	}

}
