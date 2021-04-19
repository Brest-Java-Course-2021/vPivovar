package com.epam.brest.selector;

import org.junit.Test;

import java.math.BigDecimal;
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

    Map<Integer, BigDecimal> valuesForTestingPriceSelection = Map.of(
            1, new BigDecimal(100),
            2, new BigDecimal(200),
            3, new BigDecimal(300));

    Map<Integer, BigDecimal> valuesForTestingWeightSelection = Map.of(
            1, new BigDecimal(1000),
            2, new BigDecimal(2000),
            3, new BigDecimal(3000));

    PriceSelector priceSelector = new PriceSelector();

    @Test
    public void selectPriceValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("2"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTestingPriceSelection, new BigDecimal("2"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(200)));

    }

    @Test
    public void selectPriceZeroValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("2"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTestingPriceSelection, new BigDecimal("0"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(100)));

    }

    @Test
    public void selectPriceMaxValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("2"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTestingPriceSelection, new BigDecimal("4"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(300)));

    }

    @Test
    public void selectPriceIncorrectValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("2"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTestingPriceSelection, new BigDecimal("-1"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(100)));

    }

    @Test
    public void selectWeightValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("2"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTestingWeightSelection, new BigDecimal("2"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(2000)));

    }

    @Test
    public void selectWeightZeroValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("2"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTestingWeightSelection, new BigDecimal("0"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(1000)));

    }

    @Test
    public void selectWeightMaxValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("3"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTestingWeightSelection, new BigDecimal("3"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(3000)));

    }

    @Test
    public void selectWeightIncorrectValue() {

//      BigDecimal result = priceSelector.selectPriceValue(values, new BigInteger("2"));
        BigDecimal result = priceSelector.selectPriceValue(valuesForTestingWeightSelection, new BigDecimal("-1"));

        assertTrue("Result value is incorrect", result.equals(new BigDecimal(1000)));

    }

}