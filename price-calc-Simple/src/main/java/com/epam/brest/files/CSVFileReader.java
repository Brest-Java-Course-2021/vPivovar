package com.epam.brest.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

// The description of class comma separate file reader:

public class CSVFileReader implements FileReader {

    @Override
    public Map<Integer, BigDecimal> readData(String filePath) throws IOException {
//      Here all the data will be sorted by key:
        Map<Integer, BigDecimal> resultMap = new TreeMap<>();
//      To get data from a file:
        InputStream inputStream = getClass().getResourceAsStream("/" + filePath);
//      Class for reading text files:
//      After exiting the method, this stream needs to be closed
//      In order not to write code with a certain number of checks, we will use the construction:
//      try with resources
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//          We do not know the file size, so we use the class BufferReader:
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);) {
            String line;
            String[] values;
            while ( ( line = bufferReader.readLine()) != null ){
                values = line.split(",");
                resultMap.put(Integer.valueOf(values[0]), new BigDecimal(values[1]));
            }


        }
//      inputStreamReader and bufferReader will be closed automatically


        return null;

    }

//    @Override
//    public Map<Integer, BigDecimal> readData(String filePath) throws IOException {
    // TODO: to change strings with numbers like BigDecimal
//        Map<String, String> result = Files.lines(Path.of(filePath)).map(s -> s.split(",")).collect(Collectors.toMap(s -> s[0], s -> s[1]));
//        result.entrySet().stream().forEach(System.out::println);
//        return null;
//    }

}
