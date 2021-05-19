package com.epam.brest.validation;

import com.epam.brest.requesters.RequestTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorRequestTest extends RequestTester {

    @Test
    void validTest() {
        var validator = new ValidatorRequest<>(() -> 10d, x -> x > 0, () -> "error");
        validator.request();
    }

}