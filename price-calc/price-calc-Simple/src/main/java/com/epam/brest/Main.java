package com.epam.brest;

import com.epam.brest.files.CSVFileReader;
import com.epam.brest.files.FileReader;
import com.epam.brest.model.AbstractStatus;
import com.epam.brest.model.ReadData;
import com.epam.brest.model.Status;
import com.epam.brest.model.StatusType;
import com.epam.brest.selector.PriceSelector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void theFirstCalcLogic() {

//      /usr/lib/jvm/java-11-openjdk-amd64/bin/java -javaagent:/snap/intellij-idea-ultimate/285/lib/idea_rt.jar=34507
//     :/snap/intellij-idea-ultimate/285/bin -Dfile.encoding=UTF-8
//      -classpath /home/panvaukan/IdeaProjects/vPivovar/price-calc-Simple/target/classes com.epam.brest.Main

//      Distance
//      Price per km
//      Price per kg
//      Result = d * pr1 + w * pr2

        double d = -10;
        double pr1 = 20.05;
        double w = 30;
        double pr2 = 30.5;

        double result = d * pr1 + w * pr2;
        System.out.println("Result: " + result);

    }

    private static void theFirstCalcLogic_with_simple_dialog() {
        Double[] enteredValues = new Double[4];

        Scanner scanner = new Scanner(System.in);
        String inputValue;

        int counter = 0;

        do {

            if (counter == 0) {
                System.out.println("Please, enter distance");
            } else if (counter == 1) {
                System.out.println("Please, enter price per km");
            } else if (counter == 2) {
                System.out.println("Please, enter weight");
            } else if (counter == 3) {
                System.out.println("Please, enter price per kg");
            }

            inputValue = scanner.next();
            if (inputValue.equalsIgnoreCase("Q")) {
                break;
            } else if (isCorrectDoubleValue(inputValue)) {
                enteredValues[counter] = Double.parseDouble(inputValue);
                counter++;
            } else {
                System.out.println("Incorrect value: " + inputValue);
            }

            if (counter == 4) {

                Double result = enteredValues[0] * enteredValues[1] + enteredValues[2] * enteredValues[3];
                System.out.println("Result = " + result);

            }

        } while (counter < 4);
    }

    public static void theSecondCalcLogic_with_simple_dialog() throws IOException {


        FileReader distancePriceFileReader = new CSVFileReader();
        // At the moment, here we assign the name of the interface, and we set the implementation later.
        // In the future, the specific implementation will be chosen by Spring, depending on the configuration
        Map<Integer, BigDecimal> distancePriceMap =
                distancePriceFileReader.readData("price-calc-Simple/src/main/resources/price_distance.csv");

        Map<Integer, BigDecimal> weightPriceMap =
                distancePriceFileReader.readData("price-calc-Simple/src/main/resources/price_weight.csv");

        PriceSelector priceSelector = new PriceSelector();

        BigDecimal[] enteredValues = new BigDecimal[2];

        Scanner scanner = new Scanner(System.in);
        String inputValue;

        int counter = 0;

        do {

            if (counter == 0) {
                System.out.println("Please, enter distance");
            } else if (counter == 1) {
                System.out.println("Please, enter weight");
            }

            inputValue = scanner.next();
            if (inputValue.equalsIgnoreCase("Q")) {
                break;
            } else if (isCorrectDoubleValue(inputValue)) {
                enteredValues[counter] = new BigDecimal(inputValue);
                counter++;
            } else {
                System.out.println("Incorrect value: " + inputValue);
            }

//          BigDecimal price = priceSelector.selectPriceValue(distancePriceMap, new BigInteger(enteredValues[0].toString()));

            if (counter == 2) {

                BigDecimal distancePrice = priceSelector.selectPriceValue(distancePriceMap,
                        (BigDecimal) (enteredValues[0]));
                System.out.println("Distance price: " + distancePrice);

                BigDecimal weightPrice = priceSelector.selectPriceValue(weightPriceMap,
                        (BigDecimal) (enteredValues[1]));
                System.out.println("Weight price: " + weightPrice);

                BigDecimal result = enteredValues[0].multiply(distancePrice).add(
                        enteredValues[1].multiply(weightPrice));
                System.out.println("Result: " + result);

                counter = 0;

            }

        } while (true);


    }

    public static void theThirdCalcLogic_with_simple_dialog() throws IOException {

        /*

        FileReader distancePriceFileReader = new CSVFileReader();
        // At the moment, here we assign the name of the interface, and we set the implementation later.
        // In the future, the specific implementation will be chosen by Spring, depending on the configuration
        Map<Integer, BigDecimal> distancePriceMap =
                distancePriceFileReader.readData("price_distance.csv");

        Map<Integer, BigDecimal> weightPriceMap =
                distancePriceFileReader.readData("price_weight.csv");

        PriceSelector priceSelector = new PriceSelector();

        */


//      The main method is converted to read the state of the system:
//      The state pattern is used here:

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("price-calc-Simple/src/main/resources/spring-config.xml");
        FileReader fileReader = applicationContext.getBean(FileReader.class);
        PriceSelector priceSelector = applicationContext.getBean(PriceSelector.class);

        Scanner scanner = new Scanner(System.in);

        Status currentStatus = new ReadData(priceSelector, fileReader, scanner);
        while (currentStatus.getType() != StatusType.EXIT) {
            currentStatus = currentStatus.handle();
        }


    }

    public static void theFourthCalcLogic_with_Spring() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("price-calc-Simple/src/main/resources/spring-config.xml");
        FileReader fileReader = applicationContext.getBean(FileReader.class);
        PriceSelector priceSelector = applicationContext.getBean(PriceSelector.class);

        Scanner scanner = new Scanner(System.in);

        Status currentStatus = new ReadData(priceSelector, fileReader, scanner);
        while (currentStatus.getType() != StatusType.EXIT) {
            currentStatus = currentStatus.handle();
        }

    }

    public static boolean isCorrectDoubleValue(String value) {
        boolean checkResult;

        try {
            Double enteredValue = Double.parseDouble(value);
            checkResult = enteredValue >= 0;
        } catch (NumberFormatException ex) {
            checkResult = false;
        }

        return checkResult;
    }

    public static void main(String[] args) throws IOException {

//      TODO: Next steps:

//      TODO - for the future : 1) Put the project status in a branch.

//      TODO - immediately : 2) Develop the evolution of our application:

//      TODO:   a) to replace variables of primitive types with business variables ( with classes - wrappers ).
//      TODO:   b) to add simple dialog data input from keyboard ( There is no absolute user interface ).
//      TODO:   с) to replace possible multiple if-else.
//      TODO:   d) to replace possible multiple if-else using such patterns as "State".
//      TODO:   e) to add ability to interrupt the data entry process.
//      TODO:   f) to solve a problem: "the magic push button" when all logic is in one class.
//      TODO:   g) to add file(s) for prices ( *.csv ) The price for transportation may vary
//      TODO:   h) to change startup template ( profile ) from "Main.main()" to Maven configuration

//      TODO: 3) to resolve existing problems: There is no validation of input data, for example: user can enter negative values.
//      TODO: 4) no Unit - tests ( The code is not testable ): we have to restart the application for testing. ( must restart )
//      TODO: This is very inconvenient, especially when we have integration tests.
//      TODO: 5) to make another calculator class: try to create a calculations class

//      Changes: A directory resources has been marked as "Resources root"

        System.out.println("Launching the application from the development environment:");

//      theFirstCalcLogic_with_simple_dialog();
//      theSecondCalcLogic_with_simple_dialog();
//      theThirdCalcLogic_with_simple_dialog();
        theFourthCalcLogic_with_Spring();

        return;

    }


}
