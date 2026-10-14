package se.iths.lucasvingren.webshop;

import se.iths.lucasvingren.webshop.model.Discount;
import se.iths.lucasvingren.webshop.model.HalfPriceDiscount;
import se.iths.lucasvingren.webshop.model.Product;
import se.iths.lucasvingren.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {
    private final ProductStorage productStorage;
    private Discount discount;
    private final OutInputHandler outInputHandler;

    public WebShopManager(ProductStorage productStorage, OutInputHandler outInputHandler) {
        this.productStorage = productStorage;
        this.outInputHandler = outInputHandler;
    }

    void saveProductToStorage(Product product) {
        productStorage.saveProduct(product);
    }

    List<Product> getProductsFromStorage() {
        return productStorage.getProducts();
    }

    Product getProductFromStorage(String articleNumber) {
        return productStorage.getProduct(articleNumber);
    }

    void startWebshop() {

        String discountAnswer = outInputHandler.prompt("Snälla mata in din rabattkod, annars mata in N för att fortsätta utan:\n");
        if (discountAnswer.equalsIgnoreCase("halfprice")) {
            discount = new HalfPriceDiscount("50% Rabatt!");
        } else {
            discount = new TenPercentDiscount("10% Rabatt!");
        }

        boolean exit = false;
        while (!exit) {
            String menyChoice = outInputHandler.menu();

            switch (menyChoice) {
                case "1" -> alternative1();
                case "2" -> alternative2();
                case "3" -> alternative3();
                case "4" -> exit = true;
                default -> outInputHandler.info("Felaktig Input, Försök igen\n");
            }
        }
    }

    private void alternative1() {
        String articleNumber = outInputHandler.prompt("Mata in artikelnummer:\n");
        String title = outInputHandler.prompt("Mata in titeln:\n");
        double price = Double.parseDouble(outInputHandler.prompt("Mata in priset:\n"));
        String description = outInputHandler.prompt("Mata in beskrivningen\n");

        Product product = new Product(articleNumber, title, price, description);
        saveProductToStorage(product);
    }

    private void alternative2() {
        List<Product> productList = getProductsFromStorage();

        if (productList.isEmpty()) {
            outInputHandler.info("Det finns inga produkter att visa");
            return;
        }

        StringBuilder message = new StringBuilder();

        for (Product product : productList) {
            double discountedPrice = discount.calculatePrice(product.getPrice());

            message.append("Artikelnummer: ").append(product.getArticleNumber()).append("\n")
                    .append("Titel: ").append(product.getTitle()).append("\n")
                    .append("Pris: ").append(product.getPrice()).append(" kr\n")
                    .append("Beskrivning: ").append(product.getDescription()).append("\n")
                    .append("Rabatterat pris: ").append(discountedPrice).append(" kr\n\n");
        }

        outInputHandler.info(message.toString());
    }

    private void alternative3() {
        String articleNumber = outInputHandler.prompt("Vänligen mata in ett artikelnummer:\n");
        Product product = getProductFromStorage(articleNumber);

        if (product == null) {
            outInputHandler.info("Det finns ingen produkt med motsvarande artikelnummer :(\n");
            return;
        }

        double discountedPrice = discount.calculatePrice(product.getPrice());
        String message = "Artikelnummer: " + product.getArticleNumber() + "\n"
                + "Titel: " + product.getTitle() + "\n"
                + "Pris: " + product.getPrice() + " kr\n"
                + "Beskrivning: " + product.getDescription() + "\n"
                + "Rabatterat pris: " + discountedPrice + " kr\n";

        outInputHandler.info(message);
    }
}
