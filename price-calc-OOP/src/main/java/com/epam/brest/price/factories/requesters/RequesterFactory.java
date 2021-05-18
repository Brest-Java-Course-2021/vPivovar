package com.epam.brest.price.factories.requesters;

import com.epam.brest.price.factories.Factory;
import com.epam.brest.requesters.Requester;

public interface RequesterFactory<R> extends Factory<Requester<R>> {
    Requester<R> create();
}
