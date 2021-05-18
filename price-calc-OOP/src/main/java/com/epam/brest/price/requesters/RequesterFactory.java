package com.epam.brest.price.requesters;

import com.epam.brest.price.factories.Factory;
import com.epam.brest.request.Requester;

public interface RequesterFactory<R> extends Factory<Requester<R>> {
    Requester<R> create();
}
