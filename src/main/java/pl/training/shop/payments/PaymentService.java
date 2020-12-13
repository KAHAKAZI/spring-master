package pl.training.shop.payments;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);

    void save(Payment payment);

    int getNumberOfAllSaved();
}
