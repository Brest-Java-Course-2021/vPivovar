package com.epam.brest.price.functions;

import com.epam.brest.price.factories.Factory;

import java.util.function.Function;

public interface FunctionFactory<T, R> extends Factory<Function<T, R>> {
    Function<T, R> create();
}
