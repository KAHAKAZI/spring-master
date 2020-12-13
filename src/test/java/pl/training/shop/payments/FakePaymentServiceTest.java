package pl.training.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FakePaymentServiceTest {

    private static final String PAYMENT_ID = "1";

    private static final FastMoney MONEY = LocalMoney.of(1_000);

    private static final PaymentRequest PAYMENT_REQUEST = PaymentRequest.builder()
            .money(MONEY)
            .build();

    @Mock
    private PaymentIdGenerator paymentIdGenerator;
    @Mock
    private PaymentRepository paymentRepository;
    private Payment payment;

    @BeforeEach
    void setUp() {
        Mockito.when(paymentIdGenerator.getNext()).thenReturn(PAYMENT_ID);
        Mockito.when(paymentRepository.save(any(Payment.class))).then(AdditionalAnswers.returnsFirstArg());
        FakePaymentService fakePaymentService = new FakePaymentService(paymentIdGenerator, paymentRepository);
        payment = fakePaymentService.process(PAYMENT_REQUEST);
    }

    @DisplayName("Should assign generated id to created payment")
    @Test
    void shouldAssignGeneratedIdToCreatedPayment() {
        var paymentId = payment.getId();
        var expected = PAYMENT_ID;
        Assertions.assertEquals(paymentId, expected);
    }

    @DisplayName("Should assign money from payment request to created payment")
    @Test
    void shouldAssignMoneyFromPaymentRequestToCreatedPayment() {
        var money = payment.getMoney();
        var expected = MONEY;
        Assertions.assertEquals(money, expected);
    }

    @DisplayName("Should assign timestamp to created payment")
    @Test
    void shouldAssignTimestampToCreatedPayment() {
        assertNotNull(payment.getTimestamp());
    }

    @DisplayName("Should assign started status to created payment")
    @Test
    void shouldAssignStartedStatusToCreatedPayment() {
        var paymentStatus = payment.getStatus();
        var expected = PaymentStatus.STARTED;
        Assertions.assertEquals(paymentStatus, expected);
    }

    @DisplayName("Should save created payment")
    @Test
    void shouldSaveCreatedPayment() {
        Mockito.verify(paymentRepository).save(payment);
    }

}