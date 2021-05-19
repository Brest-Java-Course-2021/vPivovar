package com.epam.brest.price;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.exceptions.RequesterCreationException;
import com.epam.brest.requesters.Requester;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

public class RequesterSCVFile implements Requester<SortedMap<Double, Long>> {

    private final BufferedReader reader;

    public RequesterSCVFile(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public SortedMap<Double, Long> request() throws RequestFailureException, RequestInterruptedException {

        var map = new TreeMap<Double, Long>();
        try {
            var values = reader.readLine().split(",");
            map.put(Double.parseDouble(values[0]), Long.parseLong(values[1]));
        } catch (IOException io) {
            throw new RequesterCreationException(io.getMessage() + Arrays.toString(io.getStackTrace()));
        }
        return map;
    }
}