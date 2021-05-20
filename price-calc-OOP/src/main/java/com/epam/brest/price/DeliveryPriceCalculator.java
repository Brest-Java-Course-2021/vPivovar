package com.epam.brest.price;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
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
    public Long request() throws RequestFailureException, RequestInterruptedException {
        return weightPrice.request() + distancePrice.request();
    }
}
