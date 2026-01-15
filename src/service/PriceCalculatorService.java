package service;

import model.Promotion;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ListIterator;

public class PriceCalculatorService {

    private static final Month PROMO_MONTH = Month.DECEMBER;
    private static final int PROMO_START_DAY = 1;
    private static final int PROMO_END_DAY = 30;

    public double computeOriginalPrice(
            double finalPrice,
            LocalDate bookingDate,
            List<Promotion> promotions
    ) {
        if (!isPromotionPeriod(bookingDate)) {
            // No promotions applied → original price equals final price
            return finalPrice;
        }

        double currentPrice = finalPrice;

        // Reverse promotions in reverse order
        ListIterator<Promotion> iterator =
                promotions.listIterator(promotions.size());

        while (iterator.hasPrevious()) {
            Promotion promotion = iterator.previous();
            currentPrice = reversePromotion(currentPrice, promotion);
        }

        return roundToTwoDecimals(currentPrice);
    }

    private boolean isPromotionPeriod(LocalDate bookingDate) {
        return bookingDate.getMonth() == PROMO_MONTH
                && bookingDate.getDayOfMonth() >= PROMO_START_DAY
                && bookingDate.getDayOfMonth() <= PROMO_END_DAY;
    }

    private double reversePromotion(double price, Promotion promotion) {
        switch (promotion.getType()) {
            case FIXED:
                return price + promotion.getValue();

            case PERCENTAGE:
                return price / (1 - promotion.getValue());

            default:
                throw new IllegalArgumentException(
                        "Unsupported promotion type: " + promotion.getType());
        }
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
