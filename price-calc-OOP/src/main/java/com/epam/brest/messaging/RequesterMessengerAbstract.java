package com.epam.brest.messaging;

import com.epam.brest.requesters.Requester;
import com.epam.brest.requesters.RequesterAbstract;
import com.epam.brest.messengers.Messenger;

public abstract class RequesterMessengerAbstract<I, R> extends RequesterAbstract<I, R> {

    protected final Messenger errorMessenger;

    public RequesterMessengerAbstract(Requester<I> requester, Messenger errorMessenger) {
        super(requester);
        this.errorMessenger = errorMessenger;
    }

}
