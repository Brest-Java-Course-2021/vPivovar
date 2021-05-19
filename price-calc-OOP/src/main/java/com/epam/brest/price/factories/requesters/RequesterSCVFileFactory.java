package com.epam.brest.price.factories.requesters;

import com.epam.brest.exceptions.RequesterCreationException;
import com.epam.brest.price.RequesterSCVFile;
import com.epam.brest.requesters.Requester;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.SortedMap;

public class RequesterSCVFileFactory implements RequesterFactory<SortedMap<Double, Long>> {
    private final InputStream inputStream;

    public RequesterSCVFileFactory(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public Requester<SortedMap<Double, Long>> create() throws RequesterCreationException {
        return new RequesterSCVFile(
                new BufferedReader(
                        new InputStreamReader(inputStream)
                )
        );
    }
}