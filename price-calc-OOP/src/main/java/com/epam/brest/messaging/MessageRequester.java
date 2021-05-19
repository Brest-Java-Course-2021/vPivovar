package com.epam.brest.messaging;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.requesters.Requester;
import com.epam.brest.messengers.MessengerOutput;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MessageRequester<R>  extends MessengerOutput implements Requester<R> {
    private final Requester<R> requester;

    public MessageRequester(String message, OutputStream outputStream, Requester<R> requester) {
        super(message, outputStream);
        this.requester = requester;
    }

    @Override
    public String send() {
        try {
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception ignored){}
        return message;
    }

    @Override
    public R request() throws RequestFailureException, RequestInterruptedException {
        send();
        return requester.request();
    }
}
