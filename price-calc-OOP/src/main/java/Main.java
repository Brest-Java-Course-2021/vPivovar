
import com.epam.brest.messaging.MessengerConnectRequester;
import com.epam.brest.price.DeliveryPriceCalculator;
import com.epam.brest.requesters.DistanceFactoryPriceCalculator;
import com.epam.brest.requesters.WeightFactoryPriceCalculator;
import com.epam.brest.price.money.Dollar;

public class Main {

    public static void main(String[] args) {

        new MessengerConnectRequester<>(
                "Final price is ",
                System.out,
                new DeliveryPriceCalculator(
                        new DistanceFactoryPriceCalculator(),
                        new WeightFactoryPriceCalculator()
                ),
                (m, v) -> m + new Dollar(v).asString()
        ).request();

    }
}
