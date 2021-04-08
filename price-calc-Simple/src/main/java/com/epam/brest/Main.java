package com.epam.brest;

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

    public static void main(String[] args) {

//      TODO: Next steps:

//      TODO - for the future : 1) Put the project status in a branch.

//      TODO - immediately : 2) Develop the evolution of our application:

//      TODO:   a) to replace variables of primitive types with business variables ( with classes - wrappers ).
//      TODO:   b) to add simple dialog data input from keyboard ( There is no absolute user interface ).
//      TODO:   с) to replace possible multiple if-else.
//      TODO:   d) to replace possible multiple if-else using such patterns as "State".
//      TODO:   e) to add ability to interrupt the data entry process.
//      TODO:   f) to solve a problem: "the magic pushbutton" when all logic is in one class.
//      TODO:   g) to add file(s) for prices ( *.csv )
//      TODO:   h) to change startup template ( profile ) from "Main.main()" to Maven configuration

//      TODO: 3) to resolve existing problems: There is no validation of input data, for example: user can enter negative values.
//      TODO: 4) no Unit - tests: we have to restart the application for testing. ( must restart )
//      TODO: This is very inconvenient, especially when we have integration tests.

        System.out.println("Launching the application from the development environment:");

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


        return;

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

}
