package com.epam.brest.messaging;

import com.epam.brest.messengers.MessengerBasic;
import com.epam.brest.requesters.Requester;

import java.io.OutputStream;

public abstract class MessengerRequesterAbstract<R> extends MessengerBasic implements Requester<R> {

    protected final Requester<R> requester;

    public MessengerRequesterAbstract(String message, OutputStream outputStream, Requester<R> requester) {
        super(message, outputStream);
        this.requester = requester;
    }
}