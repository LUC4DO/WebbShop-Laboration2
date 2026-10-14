package se.iths.lucasvingren.webshop;

import se.iths.lucasvingren.webshop.model.Discount;
import se.iths.lucasvingren.webshop.model.HalfPriceDiscount;
import se.iths.lucasvingren.webshop.model.Product;
import se.iths.lucasvingren.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {
    private final ProductStorage productStorage;
    private Discount discount;

    public WebShopManager(ProductStorage productStorage) {
        this.productStorage = productStorage;
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

        String discountAnswer = IO.readln("Snälla mata in din rabattkod, annars mata in N för att fortsätta utan:\n");
        if (discountAnswer.equalsIgnoreCase("halfprice")) {
            discount = new HalfPriceDiscount("50% Rabatt!");
        } else {
            discount = new TenPercentDiscount("10% Rabatt!");
        }

        boolean exit = false;
        while (!exit) {
            String menyChoice = IO.readln("Mata in 1: Lägg till produkt\n"
                    + "Mata in 2: Lista alla produkter\n"
                    + "Mata in 3: Visa information om en produkt\n"
                    + "Mata in 4: Avsluta applikationen\n");
            switch (menyChoice) {
                case "1" -> alternative1();
                case "2" -> alternative2();
                case "3" -> alternative3();
                case "4" -> exit = true;
                default -> IO.println("Felaktig Input, Försök igen\n");
            }
        }
    }

    private void alternative1() {
        String articleNumber = IO.readln("Mata in artikelnummer:\n");
        String title = IO.readln("Mata in titeln:\n");
        double price = Double.parseDouble(IO.readln("Mata in priset:\n"));
        String description = IO.readln("Mata in beskrivningen\n");

        Product product = new Product(articleNumber, title, price, description);
        saveProductToStorage(product);
    }

    private void alternative2() {
        List<Product> productList = getProductsFromStorage();

        for (Product product : productList) {
            IO.println("Artikelnummer: " + product.getArticleNumber());
            IO.println("Titel: " + product.getTitle());
            IO.println("Pris: " + product.getPrice() + " kr");
            IO.println("Beskrivning; " + product.getDescription());

            double discountedPrice = discount.calculatePrice(product.getPrice());
            IO.println("Rabatterade pris: " + discountedPrice + " kr\n");
        }
    }

    private void alternative3() {
        String articleNumber = IO.readln("Vänligen mata in ett artikelnummer:\n");
        Product product = getProductFromStorage(articleNumber);

        if (product == null) {
            IO.println("Det finns ingen produkt med motsvarande artikelnummer :(\n");
            return;
        }
        IO.println("Artikelnummer: " + product.getArticleNumber());
        IO.println("Titel: " + product.getTitle());
        IO.println("Pris: " + product.getPrice());
        IO.println("Beskrivning; " + product.getDescription());

        double discountedPrice = discount.calculatePrice(product.getPrice());
        IO.println("Rabatterade pris: " + discountedPrice + " kr\n");
    }
}
