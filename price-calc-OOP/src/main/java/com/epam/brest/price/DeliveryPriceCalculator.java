package com.epam.brest.price;

import com.epam.brest.price.functions.FunctionFactory;
import com.epam.brest.price.requesters.RequesterFactory;
import com.epam.brest.request.Requester;

import java.util.function.Function;

public class DeliveryPriceCalculator implements Requester<Long> {

    private final Requester<Double> weightKg;
    private final Requester<Double> distanceKm;
    private final Function<Double, Long> pricePerKg;
    private final Function<Double, Long> pricePerKm;

    public DeliveryPriceCalculator(Requester<Double> weightKg,
                                   Requester<Double> distanceKm,
                                   Function<Double, Long> pricePerKg,
                                   Function<Double, Long> pricePerKm) {

        this.weightKg = weightKg;
        this.distanceKm = distanceKm;
        this.pricePerKg = pricePerKg;
        this.pricePerKm = pricePerKm;
    }

    public DeliveryPriceCalculator(RequesterFactory<Double> weightKgFactory,
                                   RequesterFactory<Double> distanceKmFactory,
                                   FunctionFactory<Double, Long> pricePerKgFactory,
                                   FunctionFactory<Double, Long> pricePerKmFactory) {
        this.weightKg = weightKgFactory.create();
        this.distanceKm = distanceKmFactory.create();
        this.pricePerKg = pricePerKgFactory.create();
        this.pricePerKm = pricePerKmFactory.create();
    }

    @Override
    public Long request() {
        return pricePerKg.apply(weightKg.request()) + pricePerKm.apply(distanceKm.request());
    }
}
