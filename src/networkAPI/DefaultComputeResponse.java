package networkAPI;

import assets.ResponseCode;

public class DefaultComputeResponse implements ComputeResponse {

	private final ResponseCode result;

	public ResponseCode getResult() {
		return result;
	}

	public DefaultComputeResponse(ResponseCode result) {
		super();
		this.result = result;
	}

	public boolean isSuccess() {
		return result == ResponseCode.SUCCESS;
	}

	public boolean isFailure() {
		return result == ResponseCode.FAILURE;
	}

}
