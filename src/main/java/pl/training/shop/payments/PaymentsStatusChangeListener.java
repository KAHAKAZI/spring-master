package pl.training.shop.payments;

import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Log
public class PaymentsStatusChangeListener {

    @EventListener
    public void onPaymentStatusChange(PaymentsStatusChangedEvent statusChangeEvent) {
        log.info("Payment changed status " + statusChangeEvent.getPayment());
    }
}
