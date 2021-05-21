package com.epam.brest.requesters.commands;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.requesters.Requester;
import com.epam.brest.requesters.RequesterAbstract;

public class CommandEscapeInterrupter extends RequesterAbstract<String, String> {

    public CommandEscapeInterrupter(Requester<String> requester) {
        super(requester);
    }

    @Override
    public String request() throws RequestFailureException, RequestInterruptedException {
        var request = requester.request();
        if (request.equalsIgnoreCase("q")) {
            throw new RequestInterruptedException();
        }
        return request;
    }
}