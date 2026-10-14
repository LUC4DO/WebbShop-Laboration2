package se.iths.lucasvingren.webshop;

public class Main {
    static void main() {

        ProductFileStorage productFileStorage = new ProductFileStorage();
        WebShopManager webShopManager = new WebShopManager(productFileStorage);

        webShopManager.startWebshop();
    }
}
