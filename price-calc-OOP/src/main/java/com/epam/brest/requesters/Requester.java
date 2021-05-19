package com.epam.brest.requesters;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;

public interface Requester<R> {

    R request() throws RequestFailureException, RequestInterruptedException;

}
