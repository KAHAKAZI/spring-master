package pl.training.shop.payments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HashMapPaymentRepositoryTest {

    private static final String PAYMENT_ID = "1";
    private static final Payment PAYMENT = Payment.builder()
            .id(PAYMENT_ID)
            .build();

    private final HashMapPaymentRepository paymentRepository = new HashMapPaymentRepository();

    @Mock
    private Map<String, Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository.setPayments(payments);
    }

    @DisplayName("Should add payment to the hashMap under the payment id key")
    @Test
    void shouldAddPaymentToTheHashMapUnderThePaymentIdKey() {
        paymentRepository.save(PAYMENT);
        verify(payments).put(PAYMENT_ID, PAYMENT);
    }

}
