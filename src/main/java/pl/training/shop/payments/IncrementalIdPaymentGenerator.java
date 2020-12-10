package pl.training.shop.payments;

import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class IncrementalIdPaymentGenerator implements PaymentIdGenerator {

    private static String ID_FORMAT = "%010d";

    @Setter
    private long index;

    @Override
    public String getNext() {
        return String.format(ID_FORMAT, ++index);
    }
}
