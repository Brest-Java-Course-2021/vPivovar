package com.epam.brest.requesters;

import com.epam.brest.price.factories.requesters.RequesterFactory;

public abstract class RequesterFactoryAbstract<I, R> implements RequesterFactory<R> {

//  protected final RequesterFactory<I> innerFactory;
    protected final Requester<I> innerRequester;

    /*
    public RequesterFactoryAbstract(RequesterFactory<I> innerFactory) {
        this.innerFactory = innerFactory;
    }
    */

    public RequesterFactoryAbstract(Requester<I> innerRequester) {
        this.innerRequester = innerRequester;
    }

}