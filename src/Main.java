import model.Promotion;
import service.PriceCalculatorService;

import java.time.LocalDate;
import java.util.List;

public class Main {

        public static void main(String[] args) {

            List<Promotion> promotions = List.of(
                    new Promotion(Promotion.Type.FIXED, 100),
                    new Promotion(Promotion.Type.PERCENTAGE, 0.30)
            );

            PriceCalculatorService service = new PriceCalculatorService();

            double originalPrice = service.computeOriginalPrice(
                    350.00,
                    LocalDate.of(2025, 12, 10),
                    promotions
            );

            System.out.println("Original Price: " + originalPrice);

        }
    }
