package com.epam.brest.price.factories.requesters;

import com.epam.brest.messaging.MessengerBeforeRequester;
import com.epam.brest.messengers.MessengerBasic;
import com.epam.brest.parsing.ParserStringToDouble;
import com.epam.brest.requesters.Requester;
import com.epam.brest.requesters.RequesterExternalValue;
import com.epam.brest.validation.ValidatorRequesterFailureMessenger;

import java.util.Scanner;

public class RequesterFactoryDistanceConsole implements RequesterFactory<Double> {

    @Override
    public Requester<Double> create() {
        return
                new ValidatorRequesterFailureMessenger<>(
                        new ValidatorRequesterFailureMessenger<>(
                                new ParserStringToDouble(
                                        new MessengerBeforeRequester<>(
                                                "Enter distance, km\n",
                                                System.out,
                                                new RequesterExternalValue(new Scanner(System.in))
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