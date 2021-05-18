
import com.epam.brest.price.DeliveryPriceCalculator;
import com.epam.brest.price.functions.DistanceFactoryPriceCalculator;
import com.epam.brest.price.functions.WeightFactoryPriceCalculator;
import com.epam.brest.price.requesters.RequesterFactoryDistanceConsole;
import com.epam.brest.price.requesters.RequesterFactoryWeightConsole;
import com.epam.brest.request.RequesterExternalValue;
import com.epam.brest.messaging.MessageRequester;
import com.epam.brest.messengers.MessengerOutput;
import com.epam.brest.parsing.ParserStringToDouble;
import com.epam.brest.validation.ValidatorRequest;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var deliveryPriceCalculator = new DeliveryPriceCalculator(
                new RequesterFactoryWeightConsole(),
                new RequesterFactoryDistanceConsole(),
                new WeightFactoryPriceCalculator(),
                new DistanceFactoryPriceCalculator());

    }
}
