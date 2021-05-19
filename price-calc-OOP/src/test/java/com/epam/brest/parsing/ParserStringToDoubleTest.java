package com.epam.brest.parsing;

import com.epam.brest.messengers.MessengerOutput;
import com.epam.brest.requesters.RequestTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

class ParserStringToDoubleTest extends RequestTester {

    MessengerOutput error = mock(MessengerOutput.class);

    @Test
    void requestNoErrorIfNumber() {
        var parser = new ParserStringToDouble(createBasicRequest("123"), error);
        parser.request();
        verify(error, times(0)).send();
    }

    @Test
    void requestOneErrorAfterSuccess() {
        var parser = new ParserStringToDouble(createBasicRequest("asd 123"), error);
        parser.request();
        verify(error, times(1)).send();
        Assertions.assertThrows(NoSuchElementException.class, parser::request);
    }

}