package pl.training.shop.payments;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class FakePaymentRepository implements PaymentRepository {

    private static FakePaymentRepository instance = null;

    private Map repository = Collections.synchronizedMap(new HashMap<String, Payment>());

    private FakePaymentRepository() {};

    public static FakePaymentRepository getInstance(){
        if(instance == null)
            instance = new FakePaymentRepository();
        return instance;
    }

    @Override
    public Payment save(Payment payment) {
        repository.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Payment get(String id) {
        return (Payment) repository.get(id);
    }

    @Override
    public int count() {
        return repository.size();
    }
}
