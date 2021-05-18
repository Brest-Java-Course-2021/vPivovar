package com.epam.brest.model;

import com.epam.brest.files.FileReader;
import com.epam.brest.selector.PriceSelector;

import java.util.Scanner;

abstract public class AbstractStatus implements Status {

    PriceSelector priceSelector;
    FileReader fileReader;
    Scanner scanner;

    public AbstractStatus(PriceSelector priceSelector, FileReader fileReader, Scanner scanner) {
        this.priceSelector = priceSelector;
        this.fileReader = fileReader;
        this.scanner = scanner;
    }
}
