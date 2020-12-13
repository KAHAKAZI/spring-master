package pl.training.shop.payments;

import org.aspectj.lang.annotation.After;
import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FakePaymentRepositoryTest {

    private static final String PAYMENT_ID = "1";

    private static final FastMoney MONEY = LocalMoney.of(1_000);

    private static final PaymentRequest PAYMENT_REQUEST = PaymentRequest.builder()
            .money(MONEY)
            .build();

    private PaymentRepository paymentRepository = FakePaymentRepository.getInstance();

    private Payment payment;

    @Mock
    private PaymentIdGenerator paymentIdGenerator;

    @BeforeEach
    void setUp(){
        Mockito.when(paymentIdGenerator.getNext()).thenReturn(PAYMENT_ID);
        FakePaymentService fakePaymentService = new FakePaymentService(paymentIdGenerator, paymentRepository);
        payment = fakePaymentService.process(PAYMENT_REQUEST);
    }

    @DisplayName("Should increase size by one after addition of element")
    @Test
    void test(){
        var init = paymentRepository.count();
        paymentRepository.save(payment);
        var after = paymentRepository.count();
        System.out.println("Counters: " + init + ", " + after);
        assertEquals(init + 1, after);
    }

    @DisplayName("Should return saved payment")
    @Test
    void test2() {
        paymentRepository.save(payment);
        var paymentOut = paymentRepository.get(payment.getId());
        System.out.println("Count: " + paymentRepository.count());
        assertEquals(payment, paymentOut);
    }

    @DisplayName("Should save 2 elements")
    @Test
    void test3(){
        var initCount = paymentRepository.count();
        Payment payment1 = Payment.builder()
                .id("2")
                .money(MONEY)
                .status(PaymentStatus.STARTED)
                .timestamp(Instant.now())
                .build();
        paymentRepository.save(payment1);
        Payment payment2 = Payment.builder()
                .id("3")
                .money(MONEY)
                .status(PaymentStatus.STARTED)
                .timestamp(Instant.now())
                .build();
        paymentRepository.save(payment2);
        var finalCount = paymentRepository.count();
        Assertions.assertEquals(2, (finalCount - initCount));
    }

}