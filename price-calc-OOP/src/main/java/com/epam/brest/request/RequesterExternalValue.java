package com.epam.brest.request;

import com.epam.brest.request.Requester;

import java.util.Scanner;

public class RequesterExternalValue implements Requester<String> {

    private final Scanner scanner;

    public RequesterExternalValue(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String request() {
        return scanner.next();
    }

}
