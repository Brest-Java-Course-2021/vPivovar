package com.epam.brest.requesters;


import com.epam.brest.price.factories.requesters.RequesterFactory;
import com.epam.brest.requesters.commands.CommandEscapeInterrupter;

import java.util.Scanner;


public class BasicConsoleRequestFactory implements RequesterFactory<String> {
    @Override
    public Requester<String> create() {
        return
                new CommandEscapeInterrupter(
                        new RequesterExternalValue(new Scanner(System.in))
                );
    }
}