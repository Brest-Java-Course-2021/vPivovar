package com.epam.brest.selector;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

//Requirements
//        1) Tests should not be nested within other tests -> Tests must be unique.
//             ( Don't write tests that depend on each other )
//        2) To read about: a rollback transaction returns the previous state of the database
//        3) To read about Mock-testing
//        4) Then use decomposition

public class PriceSelectorTest {

//        Map<Integer, BigDecimal> values = new HashMap<>();
//        values.put(1, new BigDecimal(100));
//        values.put(2, new BigDecimal(200));
//        values.put(3, new BigDecimal(300));

    Map<Integer, BigDecimal> valuesForTesting = Map.of(
            1, new BigDecimal(100),
            2, new BigDecimal(200),
            3, new BigDecimal(300));

    PriceSelector priceSelector = new PriceSelector();

    @Test
    public void selectPriceValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("2"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTesting, new BigInteger("2"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(200)));

    }

    @Test
    public void selectPriceZeroValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("2"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTesting, new BigInteger("0"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(100)));

    }

    @Test
    public void selectPriceMaxValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("2"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTesting, new BigInteger("4"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(300)));

    }

    @Test
    public void selectPriceIncorrectValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("2"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTesting, new BigInteger("-1"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(100)));

    }

}