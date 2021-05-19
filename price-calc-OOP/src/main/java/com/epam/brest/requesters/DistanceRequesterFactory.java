package com.epam.brest.requesters;

import com.epam.brest.exceptions.RequesterCreationException;
import com.epam.brest.messaging.MessengerBeforeRequester;
import com.epam.brest.messengers.MessengerBasic;
import com.epam.brest.parsing.ParserStringToDouble;
import com.epam.brest.price.factories.requesters.RequesterFactory;
import com.epam.brest.validation.ValidatorRequesterFailureMessenger;

public class DistanceRequesterFactory extends RequesterFactoryAbstract<String, Double> {

        public DistanceRequesterFactory(RequesterFactory<String> innerFactory) {
            super(innerFactory);
        }

        @Override
        public Requester<Double> create() throws RequesterCreationException {
            return
                    new ValidatorRequesterFailureMessenger<>(
                            new ValidatorRequesterFailureMessenger<>(
                                    new ParserStringToDouble(
                                            new MessengerBeforeRequester<>(
                                                    "Enter distance, km\n",
                                                    System.out,
                                                    innerFactory.create()
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
                    );
        }
    }