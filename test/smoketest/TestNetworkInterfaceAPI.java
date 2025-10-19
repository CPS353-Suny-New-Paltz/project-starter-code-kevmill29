package smoketest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import assets.UserRequest;
import assets.UserRequestCode;
import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import networkapi.NetworkPrototype;

public class TestNetworkInterfaceAPI {

    @Test
    public void smokeTest() throws IOException {

        NetworkInterfaceAPI realAPI = new ImplementNetworkAPI();
        UserRequest mockRequest = mock(UserRequest.class);
        when(mockRequest.validation()).thenReturn(UserRequestCode.SUCCESS_RESPONSE);

        try (MockedConstruction<UserRequest.Builder> mockedBuilder = mockConstruction(UserRequest.Builder.class,
                (builderMock, context) -> {
                    when(builderMock.inputSource()).thenReturn(builderMock);
                    when(builderMock.outputDestination()).thenReturn(builderMock);
                    when(builderMock.delimiter()).thenReturn(builderMock);
                    when(builderMock.build()).thenReturn(mockRequest);
                })) {

            // Call your real prototype
            NetworkPrototype prototype = new NetworkPrototype();
            prototype.prototype(realAPI);

            // Assert the validation result from the mock
            assertEquals(UserRequestCode.SUCCESS_RESPONSE, mockRequest.validation());
        }
    }
}
