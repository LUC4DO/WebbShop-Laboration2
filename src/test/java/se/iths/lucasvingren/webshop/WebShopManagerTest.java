package se.iths.lucasvingren.webshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.iths.lucasvingren.webshop.model.Product;

public class WebShopManagerTest {

    private final OutInputHandler testHandler = new OutInputHandler() {
        @Override
        public String prompt(String message) {
            return "";
        }

        @Override
        public void info(String message) {

        }

        @Override
        public String menu() {
            return "";
        }
    };

    @Test
    void savesTwoProducts() {
        ProductListStorage storage = new ProductListStorage();


        WebShopManager webShopManager = new WebShopManager(storage, testHandler);

        Product firstProduct = new Product("B0010", "Första produkten", 100, "Beskrivning 1");
        Product secondProduct = new Product("002", "Andra produkten", 200, "Beskrivning 2");

        webShopManager.saveProductToStorage(firstProduct);
        webShopManager.saveProductToStorage(secondProduct);

        Assertions.assertEquals(2, webShopManager.getProductsFromStorage().size());
    }

    @Test
    void getTwoSavedProducts() {
        ProductListStorage storage = new ProductListStorage();
        WebShopManager webShopManager = new WebShopManager(storage, testHandler);

        Product product = new Product("B0015", "Exempel produkt", 100, "Beskrivning");

        webShopManager.saveProductToStorage(product);
        Product gottenProduct = webShopManager.getProductFromStorage(product.getArticleNumber());

        Assertions.assertEquals(product, gottenProduct);
    }

    @Test
    void searchedProduct() {
        ProductListStorage storage = new ProductListStorage();
        WebShopManager webShopManager = new WebShopManager(storage, testHandler);

        Product gottenProduct = webShopManager.getProductFromStorage("gg");

        Assertions.assertNull(gottenProduct);
    }
}
