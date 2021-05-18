
import com.epam.brest.messaging.MessengerConnectRequester;
import com.epam.brest.price.DeliveryPriceCalculator;
import com.epam.brest.price.functions.DistanceFactoryPriceCalculator;
import com.epam.brest.price.functions.WeightFactoryPriceCalculator;
import com.epam.brest.price.factories.requesters.RequesterFactoryDistanceConsole;
import com.epam.brest.price.factories.requesters.RequesterFactoryWeightConsole;
import com.epam.brest.price.money.Dollar;

public class Main {

    public static void main(String[] args) {

        var deliveryPriceCalculator = new DeliveryPriceCalculator(
                new DistanceFactoryPriceCalculator(),
                new WeightFactoryPriceCalculator()
        );

        var messengerConnect = new MessengerConnectRequester<>(
                "Final price is ",
                System.out,
                deliveryPriceCalculator,
                (m, v) -> m + new Dollar(v).asString()
        );

        messengerConnect.request();
    }
}
