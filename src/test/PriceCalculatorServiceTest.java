package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import model.Promotion;
import org.junit.jupiter.api.Test;
import service.PriceCalculatorService;

public class PriceCalculatorServiceTest {

    @Test
    void shouldReversePromotionsCorrectly() {
        PriceCalculatorService service = new PriceCalculatorService();

        List<Promotion> promotions = List.of(
                new Promotion(Promotion.Type.FIXED, 100),
                new Promotion(Promotion.Type.PERCENTAGE, 0.30)
        );

        double result = service.computeOriginalPrice(
                350.00,
                LocalDate.of(2025, 12, 15),
                promotions
        );

        assertEquals(600.00, result);
    }

    @Test
    void shouldNotApplyPromotionsOutsideDateRange() {
        PriceCalculatorService service = new PriceCalculatorService();

        List<Promotion> promotions = List.of(
                new Promotion(Promotion.Type.FIXED, 100)
        );

        double result = service.computeOriginalPrice(
                500.00,
                LocalDate.of(2025, 11, 25),
                promotions
        );

        assertEquals(500.00, result);
    }
}
