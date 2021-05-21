package com.epam.brest.validation;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.messengers.RequesterFailureMessengerAbstract;
import com.epam.brest.requesters.Requester;
import com.epam.brest.messengers.Messenger;

import java.util.function.Predicate;

public class ValidatorRequesterFailureMessenger<R> extends RequesterFailureMessengerAbstract<R, R> {

    private final Predicate<R> predicate;

    public ValidatorRequesterFailureMessenger(Requester<R> requester, Predicate<R> predicate, Messenger errorMessenger) {
        super(requester, errorMessenger);
        this.predicate = predicate;
    }

    @Override
    public R request() throws RequestFailureException, RequestInterruptedException {
        var requestValue = requester.request();
        if (predicate.test(requestValue)) {
            return requestValue;
        } else {
            errorMessenger.send();
            return requester.request();
        }
    }
}
