package networkapi;

import assets.UserRequestCode;
import conceptapi.ComputeComponent;

public class ImplementNetworkAPI implements NetworkInterfaceAPI {


	@Override
	public int respond(boolean isInit, int valueA, ComputeComponent concept) {
		// TODO Auto-generated method stub
		//may not be necessary but using boolean to check if initialization has started if it has then call conceptAPi to start calculation
		int result = 0;
		if(isInit) {
			result =concept.computeValues(valueA);
		}else {
			//if false will cause integration test to purposefully fail by giving wrong numbers
			result = valueA;
		}
return result;
	}

	@Override
	public boolean initialize(UserRequestCode code) {
//		if(code==UserRequestCode.SUCCESS_RESPONSE) {
//			return true;
//		}
		return false;
	}


}
