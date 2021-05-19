package com.epam.brest.requesters;

import com.epam.brest.exceptions.RequesterCreationException;
import com.epam.brest.price.PriceCalculator;
import com.epam.brest.price.factories.requesters.RequesterFactory;
import com.epam.brest.price.factories.requesters.RequesterFactoryDistanceConsole;
import com.epam.brest.requesters.Requester;

import java.util.SortedMap;
import java.util.TreeMap;

public class DistancePriceCalculatorFactory extends RequesterFactoryAbstract<Double, Long> {

    // private final RequesterFactory<SortedMap<Double, Long>> priceCurveFactory;
    private final Requester<SortedMap<Double, Long>> priceCurveFactory;


    public DistancePriceCalculatorFactory(Requester<Double> innerRequester,
                                          Requester<SortedMap<Double, Long>> priceCurveFactory) {
        super(innerRequester);
        this.priceCurveFactory = priceCurveFactory;
    }

    @Override
        /*
        public Requester<Long> create() throws RequesterCreationException {
            return new PriceCalculator(innerFactory.create(), priceCurveFactory.create());
        */

    public Requester<Long> create() {
        return new PriceCalculator(innerRequester, priceCurveFactory);
    }
}