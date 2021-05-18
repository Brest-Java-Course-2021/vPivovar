package com.epam.brest.model;

import com.epam.brest.files.CSVFileReader;
import com.epam.brest.files.FileReader;
import com.epam.brest.selector.PriceSelector;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class Calc extends AbstractStatus {

    public Calc(PriceSelector priceSelector, FileReader fileReader, Scanner scanner) {
        super(priceSelector, fileReader, scanner);
    }

    @Override
    public Status handle() {
//      TODO: to find the best place for this function
        FileReader distancePriceFileReader = new CSVFileReader();
        try {

            Map<Integer, BigDecimal> distancePriceMap =
                    fileReader.readData("price_distance.csv");

            Map<Integer, BigDecimal> weightPriceMap =
                    fileReader.readData("price_weight.csv");

            System.out.println("Result: " + userData.get(0).multiply(priceSelector.selectPriceValue(distancePriceMap, userData.get(0)))
                    .add(userData.get(1).multiply(priceSelector.selectPriceValue(weightPriceMap,userData.get(1)))));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            userData.clear();
        }

        return new ReadData(priceSelector, fileReader, scanner);
    }

    @Override
    public StatusType getType() {
        return StatusType.CALC;
    }
}
