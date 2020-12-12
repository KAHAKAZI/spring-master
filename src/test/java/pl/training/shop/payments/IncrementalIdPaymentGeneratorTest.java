package pl.training.shop.payments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.*;

public class IncrementalIdPaymentGeneratorTest {

    private static final String ID_FORMAT = "\\d{10}";

    private final IncrementalIdPaymentGenerator idPaymentGenerator = new IncrementalIdPaymentGenerator();

    @DisplayName("Should generate valid id")
    @Test
    void shouldGenerateValidId(){
        var id= idPaymentGenerator.getNext();
        Assertions.assertTrue(id.matches(ID_FORMAT));
    }

    @DisplayName("Should generate id by incrementing value of previous one")
    @Test
    void shouldGenerateIdByIncrementingValueOfPreviousOne() {
        long firstIdValue = parseLong(idPaymentGenerator.getNext());
        long secondIdValue = parseLong(idPaymentGenerator.getNext());
        long expexted = firstIdValue + 1;
        Assertions.assertEquals(secondIdValue, expexted);
    }

}