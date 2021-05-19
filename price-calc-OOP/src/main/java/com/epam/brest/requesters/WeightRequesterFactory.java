package com.epam.brest.requesters;

import com.epam.brest.exceptions.RequesterCreationException;
import com.epam.brest.messaging.MessengerBeforeRequester;
import com.epam.brest.messengers.MessengerBasic;
import com.epam.brest.parsing.ParserStringToDouble;
import com.epam.brest.price.factories.requesters.RequesterFactory;
import com.epam.brest.validation.ValidatorRequesterFailureMessenger;

public class WeightRequesterFactory extends RequesterFactoryAbstract<String, Double> {

    public WeightRequesterFactory(RequesterFactory<String> innerFactory) {
        super(innerFactory);
    }

    @Override
    public Requester<Double> create() throws RequesterCreationException {
        return
                new ValidatorRequesterFailureMessenger<>(
                        new ValidatorRequesterFailureMessenger<>(
                                new ParserStringToDouble(
                                        new MessengerBeforeRequester<>(
                                                "Enter weight, kg\n",
                                                System.out,
                                                innerFactory.create()
                                        ),
                                        new MessengerBasic(
                                                "You must enter a number\n",
                                                System.out)
                                ),
                                (x -> x > 0),
                                new MessengerBasic(
                                        "Weight cannot be less than 0\n",
                                        System.out)
                        ),
                        (x -> x <= 500),
                        new MessengerBasic(
                                "We don't deliver so heavy products (500 max)\n",
                                System.out)
                );
    }
}