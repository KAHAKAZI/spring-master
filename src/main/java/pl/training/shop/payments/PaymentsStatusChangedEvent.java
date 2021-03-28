package pl.training.shop.payments;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class PaymentsStatusChangedEvent extends ApplicationEvent {

    @Getter
    private final Payment payment;

    public PaymentsStatusChangedEvent(Object source, Payment payment) {
        super(source);
        this.payment = payment;
    }


}
