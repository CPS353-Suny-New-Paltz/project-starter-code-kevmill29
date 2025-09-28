package networkapi;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import assets.UserRequest;
import assets.UserRequest.Builder;
import networkapi.NetworkInterfaceAPI;
import networkapi.NetworkPrototype;

public class NetworkSmokeTest {
	@Test

	public void smokeTest() {
		//Create classes
		NetworkInterfaceAPI mockAPI = mock(NetworkInterfaceAPI.class);


		//Mock user request to make it return a valid request
		UserRequest mockRequest = mock(UserRequest.class);
		when(mockRequest.isValid()).thenReturn(true);

		   try (MockedConstruction<Builder> mockedBuilder = mockConstruction(Builder.class,
		            (builderMock, context) -> {
		                when(builderMock.inputSource(anyString())).thenReturn(builderMock);
		                when(builderMock.outputDestination(anyString())).thenReturn(builderMock);
		                when(builderMock.delimiter(anyString())).thenReturn(builderMock);
		                when(builderMock.useDefaultDelimiter(anyBoolean())).thenReturn(builderMock);
		                when(builderMock.build()).thenReturn(mockRequest);
		            })) {

		NetworkPrototype prototype = new NetworkPrototype();
		prototype.prototype(mockAPI);
	}

}
}
