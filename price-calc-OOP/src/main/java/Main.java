
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
                            new DistancePriceCalculatorFactory(
                                    new RepeaterRequest<>(
                                            new DistanceRequesterFactory(
                                                    new ConsoleRequesterBasicFactory().create()

                                            ).create()
                                    ),
                                    new RequesterSCVFileFactory(
                                            getClass().getResourceAsStream("/distance_price.scv")
                                    ).create()

                            ).create(),
                            new WeightPriceCalculatorFactory(
                                    new RepeaterRequest<>(
                                            new WeightRequesterFactory(
                                                    new ConsoleRequesterBasicFactory().create()

                                            ).create()
                                    ),
                                    new RequesterSCVFileFactory(
                                            getClass().getResourceAsStream("/weight_price.scv")
                                    ).create()

                            ).create()
                    ),
                    (m, v) -> m + new Dollar(v).asString()
            ).request();
        } catch (RequestInterruptedException e) {
            System.out.print("bye");
        } catch (Exception e) {
            System.out.print(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
    }
}