package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

//    @Autowired
//    public FakePaymentService(@IdGenerator("uuid") PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository) {
//        this.paymentIdGenerator = paymentIdGenerator;
//        this.paymentRepository = paymentRepository;
//    }

    @LogPayments
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        return paymentRepository.save(payment);
    }

    public void init() {
        log.info("PaymentService init");
    }

    public void destroy() {
        log.info("PaymentService is going down");

    }

}
