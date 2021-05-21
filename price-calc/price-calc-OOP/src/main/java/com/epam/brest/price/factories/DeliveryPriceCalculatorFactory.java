package com.epam.brest.price.factories;

import com.epam.brest.messaging.MessengerConnectRequester;
import com.epam.brest.price.DeliveryPriceCalculator;
import com.epam.brest.price.factories.requesters.RequesterFactory;
import com.epam.brest.price.factories.requesters.RequesterSCVFileFactory;
import com.epam.brest.price.money.Dollar;
import com.epam.brest.requesters.ConsoleRequesterBasicFactory;
import com.epam.brest.requesters.DistancePriceCalculatorFactory;
import com.epam.brest.requesters.Requester;
import com.epam.brest.requesters.WeightPriceCalculatorFactory;

public class DeliveryPriceCalculatorFactory implements RequesterFactory<Long> {
    @Override
    public Requester<Long> create() {
        var consoleRequester = new ConsoleRequesterBasicFactory().create();
        return new MessengerConnectRequester<>(
                "Final price is ",
                System.out,
                new DeliveryPriceCalculator(
                        new DistancePriceCalculatorFactory(
                                new DistanceRequesterFactory(
                                        consoleRequester
                                ).create(),
                                new RequesterSCVFileFactory(
                                        getClass().getClassLoader().getResourceAsStream("price_distance.csv")
                                ).create()
                        ).create(),
                        new WeightPriceCalculatorFactory(
                                new WeightRequesterFactory(
                                        consoleRequester
                                ).create(),
                                new RequesterSCVFileFactory(
                                        getClass().getClassLoader().getResourceAsStream("price_weight.csv")
                                ).create()
                        ).create()
                ),
                (m, v) -> m + new Dollar(v).asString()
        );
    }
}