package com.epam.brest.requesters;

import com.epam.brest.price.PriceCalculator;
import com.epam.brest.price.factories.requesters.RequesterFactory;
import com.epam.brest.price.factories.requesters.RequesterFactoryWeightConsole;
import com.epam.brest.requesters.Requester;

import java.util.TreeMap;

public class WeightFactoryPriceCalculator implements RequesterFactory<Long> {
    @Override
    public Requester<Long> create() {
        var priceCurve = new TreeMap<Double, Long>();
        priceCurve.put(0D, 1000L);
        priceCurve.put(100D, 2000L);
        priceCurve.put(400D, 5000L);
        return new PriceCalculator(priceCurve, new RequesterFactoryWeightConsole().create());
    }
}
