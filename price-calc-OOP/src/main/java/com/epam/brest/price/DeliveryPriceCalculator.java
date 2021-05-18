package com.epam.brest.price;

import com.epam.brest.price.factories.requesters.RequesterFactory;
import com.epam.brest.requesters.Requester;

public class DeliveryPriceCalculator implements Requester<Long> {

    private final Requester<Long> weightPrice;
    private final Requester<Long> distancePrice;

    public DeliveryPriceCalculator(Requester<Long> weightPrice,
                                   Requester<Long> distancePrice) {
        this.weightPrice = weightPrice;
        this.distancePrice = distancePrice;
    }

    @Override
    public Long request() {
        return weightPrice.request() + distancePrice.request();
    }
}
