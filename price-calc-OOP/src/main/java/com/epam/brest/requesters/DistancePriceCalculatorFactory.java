package com.epam.brest.requesters;

import com.epam.brest.exceptions.RequesterCreationException;
import com.epam.brest.price.PriceCalculator;
import com.epam.brest.price.factories.requesters.RequesterFactory;
import com.epam.brest.price.factories.requesters.RequesterFactoryDistanceConsole;
import com.epam.brest.requesters.Requester;

import java.util.SortedMap;
import java.util.TreeMap;

public class DistancePriceCalculatorFactory extends RequesterFactoryAbstract<Double, Long> {

    private final RequesterFactory<SortedMap<Double, Long>> priceCurveFactory;

    public DistancePriceCalculatorFactory(RequesterFactory<Double> innerFactory,
                                          RequesterFactory<SortedMap<Double, Long>> priceCurveFactory) {
        super(innerFactory);
        this.priceCurveFactory = priceCurveFactory;
    }

    @Override
    /*
    public Requester<Long> create() throws RequesterCreationException {
        var priceCurve = new TreeMap<Double, Long>();
        priceCurve.put(0D, 200L);
        priceCurve.put(1000D, 500L);
        priceCurve.put(6000D, 700L);
        return new PriceCalculator(innerFactory.create(), priceCurve);
    */
    public Requester<Long> create() throws RequesterCreationException {
//        var priceCurve = new TreeMap<Double, Long>();
//        priceCurve.put(0D, 200L);
//        priceCurve.put(1000D, 500L);
//        priceCurve.put(6000D, 700L);
        return new PriceCalculator(innerFactory.create(), priceCurveFactory.create());
    }
}