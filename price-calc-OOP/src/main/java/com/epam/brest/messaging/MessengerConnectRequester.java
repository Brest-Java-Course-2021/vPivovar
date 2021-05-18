package com.epam.brest.messaging;

import com.epam.brest.messengers.MessengerOutput;
import com.epam.brest.request.Requester;

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
    public R request() {
        var requestResult = requester.request();
        var connectedMessage = connectFunction.apply(message, requestResult);
        new MessengerOutput(connectedMessage, outputStream).send();
        return requestResult;
    }
}