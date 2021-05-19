
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.messaging.MessengerConnectRequester;
import com.epam.brest.price.DeliveryPriceCalculator;
import com.epam.brest.requesters.*;
import com.epam.brest.price.money.Dollar;
import com.epam.brest.requesters.repeater.RequestRepeater;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        try {
            new MessengerConnectRequester<>(
                    "Final price is ",
                    System.out,
                    new DeliveryPriceCalculator(

                            new RequestRepeater<>(
                                    new DistanceFactoryPriceCalculator(
                                            new RequesterFactoryDistance(
                                                    new BasicConsoleRequestFactory()
                                            )
                                    ).create()
                            ),
                            new RequestRepeater<>(
                                    new WeightFactoryPriceCalculator(
                                            new RequesterFactoryWeight(
                                                    new BasicConsoleRequestFactory()
                                            )
                                    ).create()
                            )
                    ),
                    (m, v) -> m + new Dollar(v).asString()
            ).request();

        } catch (RequestInterruptedException re) {
            System.out.print(re.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
    }
}
