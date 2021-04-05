package com.epam.brest;

public class Main {

    public static void main(String[] args) {

//      TODO: 1) Put the project status in a branch

        System.out.println("Launching the application from the development environment:");

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

//     What are the disadvantages here?

//     1) There is no absolute user interface.
//     2) There is no validation of input data: user can enter negative values.

    }
}
