package com.epam.brest.parsing;

import com.epam.brest.messengers.ErrorMessengerRequesterAbstract;
import com.epam.brest.requesters.Requester;
import com.epam.brest.messengers.Messenger;

public class ParserStringToDouble extends ErrorMessengerRequesterAbstract<String, Double> {

    public ParserStringToDouble(Requester<String> requester, Messenger errorMessenger) {
        super(requester, errorMessenger);
    }

    @Override
    public Double request() {
        try {
          return Double.parseDouble(requester.request());
        } catch (NumberFormatException e) {
            errorMessenger.send();
            return this.request();
        }
    }
}
