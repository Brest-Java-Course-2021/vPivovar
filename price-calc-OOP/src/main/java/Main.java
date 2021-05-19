
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.messaging.MessengerConnectRequester;
import com.epam.brest.price.DeliveryPriceCalculator;
import com.epam.brest.price.factories.requesters.RequesterSCVFileFactory;
import com.epam.brest.requesters.*;
import com.epam.brest.price.money.Dollar;
import com.epam.brest.requesters.repeaters.RepeaterRequest;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {

        try {
            new MessengerConnectRequester<>(
                    "Final price is ",
                    System.out,
                    new DeliveryPriceCalculator(
                            new RepeaterRequest<>(
                                    new DistancePriceCalculatorFactory(
                                            new DistanceRequesterFactory(
                                                    new ConsoleRequesterBasicFactory()
                                            ),
                                            new RequesterSCVFileFactory(getClass().getResourceAsStream("/distance_price.scv"))
                                    ).create()
                            ),
                            new RepeaterRequest<>(
                                    new WeightPriceCalculatorFactory(
                                            new WeightRequesterFactory(
                                                    new ConsoleRequesterBasicFactory()
                                            ),
                                            new RequesterSCVFileFactory(getClass().getResourceAsStream("/weight_price.scv"))
                                    ).create()
                            )
                    ),
                    (m, v) -> m + new Dollar(v).asString()
            ).request();
        } catch (Exception e) {
            System.out.print(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }

    }

}