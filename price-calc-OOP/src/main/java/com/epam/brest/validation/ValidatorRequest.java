package com.epam.brest.validation;

import com.epam.brest.messengers.ErrorMessengerRequesterAbstract;
import com.epam.brest.requesters.Requester;
import com.epam.brest.messengers.Messenger;

import java.util.function.Predicate;

public class ValidatorRequest<R> extends ErrorMessengerRequesterAbstract<R, R> {

    private final Predicate<R> predicate;
    public ValidatorRequest(Requester<R> requester, Predicate<R> predicate, Messenger errorMessenger) {
        super(requester, errorMessenger);
        this.predicate = predicate;
    }

    @Override
    public R request() {
        var requestValue = requester.request();
        if (predicate.test(requestValue)) {
            return requestValue;
        } else {
            errorMessenger.send();
            return requester.request();
        }
    }

 

}
