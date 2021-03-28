package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationEventPublisher;

import java.time.Instant;

@Log
//@Scope("singleton") // singleton ins default, does not have to be specified
//@Scope(BeanDefinition.SCOPE_SINGLETON)
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
//@Service("paymentService")
@RequiredArgsConstructor
public class FakePaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;
    private final ApplicationEventPublisher eventPublisher;

    @LogPayments
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        eventPublisher.publishEvent(new PaymentsStatusChangedEvent(this, payment));
        return paymentRepository.save(payment);
    }

}
