package com.epam.brest.files;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

// The description of class comma separate file reader:

public class CSVFileReader implements FileReader {

    @Override
    public Map<Integer, BigDecimal> readData(String filePath) throws IOException {

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
