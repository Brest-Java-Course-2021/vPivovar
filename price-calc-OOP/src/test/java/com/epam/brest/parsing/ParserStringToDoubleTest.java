package com.epam.brest.parsing;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.messengers.Messenger;
import com.epam.brest.messengers.MessengerBasic;
import com.epam.brest.requesters.RequestTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

class ParserStringToDoubleTest extends RequestTester {

//  MessengerBasic error = mock(MessengerBasic.class);
    Messenger error = mock(Messenger.class);

    @Test
    void requestNoErrorIfNumber()  {
//      var parser = new ParserStringToDouble(createBasicRequest("123"), error);
//      parser.request();
        var parser = new ParserStringToDouble(() -> "213.123", error);
        Assertions.assertDoesNotThrow(
                () -> Assertions.assertEquals(213.123, parser.request())
        );
        verify(error, times(0)).send();
    }

    @Test
    void requestOneErrorAfterSuccess() {
//      var parser = new ParserStringToDouble(createBasicRequest("asd 123"), error);
//      parser.request();
        var parser = new ParserStringToDouble(() -> "asd", error);
        verify(error, times(1)).send();
//      Assertions.assertThrows(NoSuchElementException.class, parser::request);
        Assertions.assertThrows(RequestFailureException.class, parser::request);
    }

}