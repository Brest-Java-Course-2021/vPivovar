package com.epam.brest.messengers;

import com.epam.brest.requesters.Requester;
import com.epam.brest.requesters.RequesterAbstract;

public abstract class ErrorMessengerRequesterAbstract<I, R> extends RequesterAbstract<I, R> {

    protected final Messenger errorMessenger;

    public ErrorMessengerRequesterAbstract(Requester<I> requester, Messenger errorMessenger) {
        super(requester);
        this.errorMessenger = errorMessenger;
    }
}