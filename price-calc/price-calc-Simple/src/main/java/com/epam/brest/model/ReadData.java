package com.epam.brest.model;

import com.epam.brest.files.FileReader;
import com.epam.brest.selector.PriceSelector;

import java.math.BigDecimal;
import java.util.Scanner;

public class ReadData extends AbstractStatus {
    public ReadData(PriceSelector priceSelector, FileReader fileReader, Scanner scanner) {
        super(priceSelector, fileReader, scanner);
    }

    @Override
    public Status handle() {

        if ( userData.size() < 2 ) {
            System.out.println(messages.get(userData.size()));
            String inputValue = scanner.next();

            if (inputValue.equalsIgnoreCase("Q")) {
                return new Exit();
            } else if (isCorrectValue(inputValue)) {
                userData.add(new BigDecimal(inputValue));
            }

        } else {
            return new Calc(priceSelector, fileReader, scanner);
        }
        return this;
    }

    @Override
    public StatusType getType() {
        return StatusType.READ_DATA;
    }

    private boolean isCorrectValue(String value) {
        try {
            BigDecimal enteredValue = new BigDecimal(value);
            return enteredValue.doubleValue() > 0;
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value: " + value);
        }
        return false;
    }

}
