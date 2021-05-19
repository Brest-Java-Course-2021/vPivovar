package com.epam.brest.validation;


import com.epam.brest.messengers.MessengerBasic;
import com.epam.brest.requesters.RequestTester;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

class ValidatorRequesterTest {

    @Test
    void validTest() {

        var messenger = mock(MessengerBasic.class);
        var validator = new ValidatorRequesterFailureMessenger<>(() -> -1D, x -> x > 0, messenger);

        /*
        Assertions.assertFalse(validator.test());
        verify(messenger, times(1)).send();
        */

    }

}