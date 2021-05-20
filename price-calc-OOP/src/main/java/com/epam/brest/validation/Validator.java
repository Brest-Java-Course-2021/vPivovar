package com.epam.brest.validation;

public interface Validator<T> {

    boolean isValid(T value);

}
