package com.epam.brest.requesters;

import com.epam.brest.exceptions.RequesterCreationException;
import com.epam.brest.price.PriceCalculator;
import com.epam.brest.price.factories.requesters.RequesterFactory;
import com.epam.brest.price.factories.requesters.RequesterFactoryWeightConsole;
import com.epam.brest.requesters.Requester;

import java.util.SortedMap;
import java.util.TreeMap;

public class WeightPriceCalculatorFactory extends RequesterFactoryAbstract<Double, Long> {

//  private final RequesterFactory<SortedMap<Double, Long>> priceCurveFactory;
    private final Requester<SortedMap<Double, Long>> priceCurveFactory;

    public WeightPriceCalculatorFactory(Requester<Double> innerRequester,
                                        Requester<SortedMap<Double, Long>> priceCurveFactory) {
        super(innerRequester);
        this.priceCurveFactory = priceCurveFactory;
    }

    @Override
    /*
    public Requester<Long> create() {
        var priceCurve = new TreeMap<Double, Long>();
        priceCurve.put(0D, 1000L);
        priceCurve.put(100D, 2000L);
        priceCurve.put(400D, 5000L);
        return new PriceCalculator(innerFactory.create(), priceCurve);
    */
    public Requester<Long> create() {
        return new PriceCalculator(innerRequester, priceCurveFactory);
    }
}