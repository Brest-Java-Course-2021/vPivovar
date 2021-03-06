package com.epam.brest.price.factories.requesters;

import com.epam.brest.messaging.MessageRequester;
import com.epam.brest.messengers.MessengerBasic;
import com.epam.brest.parsing.ParserStringToDouble;
import com.epam.brest.requesters.Requester;
import com.epam.brest.requesters.RequesterExternalValue;
import com.epam.brest.validation.ValidatorRequesterFailureMessenger;

import java.util.Scanner;

public class RequesterFactoryWeightConsole implements RequesterFactory<Double> {

    @Override
    public Requester<Double> create() {
        return
                new ValidatorRequesterFailureMessenger<>(
                        new ValidatorRequesterFailureMessenger<>(
                                new ParserStringToDouble(
                                        new MessageRequester<>(
                                                "Enter weight, kg\n",
                                                System.out,
                                                new RequesterExternalValue(new Scanner(System.in))
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