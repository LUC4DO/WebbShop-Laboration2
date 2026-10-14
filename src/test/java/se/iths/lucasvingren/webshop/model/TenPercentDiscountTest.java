package se.iths.lucasvingren.webshop.model;

import org.junit.jupiter.api.Test;

public class TenPercentDiscountTest {
    @Test
    void testPriceAfterDiscount() {
        TenPercentDiscount discount = new TenPercentDiscount("10% Discount\n");
        double price = 350;
        double priceAfterDiscount = discount.calculatePrice(price);

        if (priceAfterDiscount == price * 0.9) IO.println("Test klar: Rabatten har applicerats korrekt!\n");
        else IO.println("Test klar: Rabatten är fel!\n");
    }
}
