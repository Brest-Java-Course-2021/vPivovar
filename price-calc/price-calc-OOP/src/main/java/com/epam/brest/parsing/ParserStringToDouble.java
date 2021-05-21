package com.epam.brest.parsing;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.messengers.RequesterFailureMessengerAbstract;
import com.epam.brest.requesters.Requester;
import com.epam.brest.messengers.Messenger;

public class ParserStringToDouble extends RequesterFailureMessengerAbstract<String, Double> {

    public ParserStringToDouble(Requester<String> requester, Messenger errorMessenger) {
        super(requester, errorMessenger);
    }

    @Override
    public Double request() throws RequestFailureException, RequestInterruptedException {
        try {
            return Double.parseDouble(requester.request());
        } catch (NumberFormatException e) {
            errorMessenger.send();
            return this.request();
        }
    }
}
