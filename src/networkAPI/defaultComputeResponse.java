package networkAPI;

import assets.responseCode;

public class defaultComputeResponse implements computeResponse {

	private final responseCode result;

	public responseCode getResult() {
		return result;
	}

	public defaultComputeResponse(responseCode result) {
		super();
		this.result = result;
	}

	public boolean isSuccess() {
		return result == responseCode.SUCCESS;
	}

	public boolean isFailure() {
		return result == responseCode.FAILURE;
	}

}
