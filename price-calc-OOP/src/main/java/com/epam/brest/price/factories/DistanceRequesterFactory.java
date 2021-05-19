package com.epam.brest.price.factories;

import com.epam.brest.messaging.MessengerBeforeRequester;
import com.epam.brest.messengers.MessengerBasic;
import com.epam.brest.parsing.ParserStringToDouble;
import com.epam.brest.requesters.Requester;
import com.epam.brest.requesters.RequesterFactoryAbstract;
import com.epam.brest.requesters.repeaters.RepeaterRequest;
import com.epam.brest.validation.ValidatorRequester;

public class DistanceRequesterFactory extends RequesterFactoryAbstract<String, Double> {

    public DistanceRequesterFactory(Requester<String> innerRequester) {
        super(innerRequester);
    }

    @Override
    public Requester<Double> create() {
        return
                new RepeaterRequest<>(
                        new ValidatorRequester<>(
                                new ValidatorRequester<>(
                                        new ParserStringToDouble(
                                                new MessengerBeforeRequester<>(
                                                        "Enter distance, km\n",
                                                        System.out,
                                                        innerRequester
                                                ),
                                                new MessengerBasic(
                                                        "You must enter a number\n",
                                                        System.out)
                                        ),
                                        (x -> x > 0),
                                        new MessengerBasic(
                                                "Distance cannot be less than 0\n",
                                                System.out)
                                ),
                                (x -> x <= 10000),
                                new MessengerBasic(
                                        "We don't deliver products so far (10000 max)\n",
                                        System.out)
                        )
                );
    }
}