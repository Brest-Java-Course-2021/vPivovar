package com.epam.brest.requesters;

import com.epam.brest.price.PriceCalculator;
import com.epam.brest.price.factories.requesters.RequesterFactory;
import com.epam.brest.price.factories.requesters.RequesterFactoryDistanceConsole;
import com.epam.brest.requesters.Requester;

import java.util.TreeMap;

public class DistanceFactoryPriceCalculator extends RequesterFactoryAbstract<Double, Long> {

    public DistanceFactoryPriceCalculator(RequesterFactory<Double> innerFactory) {
        super(innerFactory);
    }

    @Override
    public Requester<Long> create() {
        var priceCurve = new TreeMap<Double, Long>();
        priceCurve.put(0D, 200L);
        priceCurve.put(1000D, 500L);
        priceCurve.put(6000D, 700L);
        return new PriceCalculator(innerFactory.create(), priceCurve);
    }
}