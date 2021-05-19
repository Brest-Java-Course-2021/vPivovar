package com.epam.brest.validation;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.messengers.Messenger;
import com.epam.brest.messengers.RequesterFailureMessengerAbstract;
import com.epam.brest.requesters.Requester;

import java.util.function.Predicate;

public class ValidatorRequester<R> extends RequesterFailureMessengerAbstract<R, R> {
    private final Predicate<R> predicate;
    public ValidatorRequester(Requester<R> requester, Predicate<R> predicate, Messenger errorMessenger) {
        super(requester, errorMessenger);
        this.predicate = predicate;
    }
    @Override
    public R request() throws RequestFailureException, RequestInterruptedException {
        var requestValue = requester.request();
        if (predicate.test(requestValue)) {
            return requestValue;
        } else {
            throw new RequestFailureException(errorMessenger.send());
        }
    }
}