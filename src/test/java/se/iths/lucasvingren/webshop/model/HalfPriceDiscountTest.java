package se.iths.lucasvingren.webshop.model;

import org.junit.jupiter.api.Test;

public class HalfPriceDiscountTest {
    @Test
    void testPriceAfterDiscount() {
        HalfPriceDiscount discount = new HalfPriceDiscount("50% Discount\n");
        double price = 650;
        double priceAfterDiscount = discount.calculatePrice(price);

        if (priceAfterDiscount == price * 0.5) IO.println("Test klar: Rabatten har applicerats korrekt!\n");
        else IO.println("Test klar: Rabatten är fel!\n");
    }
}
