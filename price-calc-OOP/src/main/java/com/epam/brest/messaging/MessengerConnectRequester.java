package com.epam.brest.messaging;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.messengers.MessengerBasic;
import com.epam.brest.requesters.Requester;

import java.io.OutputStream;
import java.util.function.BiFunction;

public class MessengerConnectRequester<R> extends MessengerRequesterAbstract<R> {

    private final BiFunction<String, R, String> connectFunction;

    public MessengerConnectRequester(String message,
                                     OutputStream outputStream,
                                     Requester<R> requester,
                                     BiFunction<String, R, String> connectFunction) {
        super(message, outputStream, requester);
        this.connectFunction = connectFunction;
    }

    @Override
    public R request() throws RequestFailureException, RequestInterruptedException {
        var requestResult = requester.request();
        var connectedMessage = connectFunction.apply(message, requestResult);
        new MessengerBasic(connectedMessage, outputStream).send();
        return requestResult;
    }
}