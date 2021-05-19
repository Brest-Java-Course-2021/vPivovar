package com.epam.brest.requesters;

import com.epam.brest.price.factories.requesters.RequesterFactory;

public abstract class RequesterFactoryAbstract<I, R> implements RequesterFactory<R> {

    protected final RequesterFactory<I> innerFactory;

    public RequesterFactoryAbstract(RequesterFactory<I> innerFactory) {
        this.innerFactory = innerFactory;
    }
}