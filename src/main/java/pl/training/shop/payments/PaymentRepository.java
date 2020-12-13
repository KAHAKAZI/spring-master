package pl.training.shop.payments;

public interface PaymentRepository {

    Payment save(Payment payment);

    Payment get(String id);

    int count();

}
