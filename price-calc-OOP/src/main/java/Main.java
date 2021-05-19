
import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.messaging.MessengerConnectRequester;
import com.epam.brest.price.DeliveryPriceCalculator;
import com.epam.brest.price.factories.requesters.RequesterSCVFileFactory;
import com.epam.brest.requesters.*;
import com.epam.brest.price.money.Dollar;
import com.epam.brest.requesters.repeaters.RepeaterRequest;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws RequestFailureException {
        new Main().run();
    }

    public void run() throws RequestFailureException {
        try {
            new MessengerConnectRequester<>(
                    "Final price is ",
                    System.out,
                    new DeliveryPriceCalculator(
                            new DistancePriceCalculatorFactory(

                                    new DistanceRequesterFactory(
                                            new ConsoleRequesterBasicFactory().create()
                                    ).create(),
                                    new RequesterSCVFileFactory(
                                            getClass().getResourceAsStream("/price_distance.csv")
                                    ).create()
                            ).create(),
                            new WeightPriceCalculatorFactory(

                                    new WeightRequesterFactory(
                                            new ConsoleRequesterBasicFactory().create()
                                    ).create(),
                                    new RequesterSCVFileFactory(
                                            getClass().getResourceAsStream("/price_weight.csv")
                                    ).create()
                            ).create()
                    ),
                    (m, v) -> m + new Dollar(v).asString()
            ).request();
        } catch (RequestInterruptedException | RequestFailureException e) {
            System.out.print("bye");
        }
    }
}
