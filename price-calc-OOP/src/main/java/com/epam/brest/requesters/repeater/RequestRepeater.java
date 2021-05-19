package com.epam.brest.requesters.repeater;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.requesters.Requester;
import com.epam.brest.requesters.RequesterAbstract;

public class RequestRepeater<R> extends RequesterAbstract<R, R> {

    public RequestRepeater(Requester<R> requester) {
        super(requester);
    }

    @Override
    public R request() throws RequestInterruptedException {
        try {
            return requester.request();
        } catch (RequestFailureException re) {
            return request();
        }
    }
}