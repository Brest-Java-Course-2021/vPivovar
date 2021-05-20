import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.price.factories.DeliveryPriceCalculatorFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) throws RequestFailureException {
        new Main().run();
    }

    public void run() throws RequestFailureException {

        var appContext = new ClassPathXmlApplicationContext("spring-config.xml");

        try {

            appContext.getBean(DeliveryPriceCalculatorFactory.class).create().request();

        } catch (RequestInterruptedException | RequestFailureException e) {
            System.out.print("bye");
        }
    }
}
