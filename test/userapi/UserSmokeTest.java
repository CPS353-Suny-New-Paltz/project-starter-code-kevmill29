package userapi;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserSmokeTest {
	@Test
	public void usersmokeTest(){
		//Mock the interface used in prototype
		ComputeAPI mockAPI = Mockito.mock(ComputeAPI.class);
		
		when(mockAPI.run(any(ComputeRequest.class)))
			.thenReturn(new DefaultComputeResponse(ComputeResponseGetCode.SUCCESS));
		
		ComputeAPIPrototype prototype = new ComputeAPIPrototype();
		
		//start process
		prototype.prototype(mockAPI);
		
		
		//assertions
		verify(mockAPI, times(1)).run(any(ComputeRequest.class));
		//if success 
		
	}

}
