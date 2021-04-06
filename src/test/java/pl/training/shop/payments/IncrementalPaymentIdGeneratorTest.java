package pl.training.shop.payments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncrementalPaymentIdGeneratorTest {

    private static final String ID_FORMAT = "\\d{10}";

    private final IncrementalPaymentIdGenerator paymentIdGenerator = new IncrementalPaymentIdGenerator();

    @DisplayName("Should generate valid id")
    @Test
    void shouldGenerateValidId() {
        var id = paymentIdGenerator.getNext();
        assertTrue(id.matches(ID_FORMAT));
    }

    @DisplayName("Should generate id by increasing the value of previous one")
    @Test
    void shouldGenerateIdByIncreasingTheValueOfPreviousOne() {
        var firstIdValue = parseLong(paymentIdGenerator.getNext());
        var secondIdValue = parseLong(paymentIdGenerator.getNext());
        assertEquals(firstIdValue + 1, secondIdValue);
    }

}
