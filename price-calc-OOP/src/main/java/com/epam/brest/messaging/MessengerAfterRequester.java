package com.epam.brest.messaging;

import com.epam.brest.requesters.Requester;

import java.io.OutputStream;

public class MessengerAfterRequester<R> extends MessengerRequesterAbstract<R> {

    public MessengerAfterRequester(String message, OutputStream outputStream, Requester<R> requester) {
        super(message, outputStream, requester);
    }

    @Override
    public R request() {
        var requestResult = requester.request();
        send();
        return requestResult;
    }
}