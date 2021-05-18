
import com.epam.brest.request.RequesterExternalValue;
import com.epam.brest.messaging.MessageRequester;
import com.epam.brest.messengers.MessengerOutput;
import com.epam.brest.parsing.ParserStringToDouble;
import com.epam.brest.validation.ValidatorRequest;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var a =
                new ValidatorRequest<>(
                        new ValidatorRequest<>(
                                new ParserStringToDouble(
                                        new MessageRequester<>(
                                                "Enter double value:\n",
                                                System.out,
                                                new RequesterExternalValue(new Scanner(System.in))
                                        ),
                                        new MessengerOutput(
                                                "Value not Valid\n",
                                                System.out)
                                ),
                                (x -> x > 0),
                                new MessengerOutput(
                                        "Value cannot be less than 0\n",
                                        System.out)
                        ),
                        (x -> x < 10000),
                        new MessengerOutput(
                                "Value cannot be bigger than 10000\n",
                                System.out)
                );

        System.out.println(a.request());

    }
}
