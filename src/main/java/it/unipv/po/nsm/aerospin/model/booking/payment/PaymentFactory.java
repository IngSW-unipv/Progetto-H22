package model.booking.payment;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Classe che si occupa di istanziare il payment strategy indicato nel file properties
 *
 * @author GruppoNoSuchMethod
 */
public class PaymentFactory {
    private IPaymentStrategy payment;

    public IPaymentStrategy getPaymentStrategy() {
        if(payment==null) {
            Properties p = new Properties(System.getProperties());
            String PaymentClassName;

            try {
                p.load(new FileInputStream("src/main/resources/properties/properties"));
                String PROPERTYNAME = "payment.strategy.class.name";
                PaymentClassName = p.getProperty(PROPERTYNAME);

                Constructor<?> c = Class.forName(PaymentClassName).getConstructor();
                payment = (IPaymentStrategy) c.newInstance();

            } catch (Exception e) {
                payment = null;
            }
        }
        return payment;
    }
}
