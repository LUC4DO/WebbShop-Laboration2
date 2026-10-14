package se.iths.lucasvingren.webshop;

public class Main {
    static void main() {

        ProductStorage productStorage = new ProductFileStorage();
        //OutInputHandler outInputHandler = new ConsoleOutInputHandler();
        OutInputHandler outInputHandler = new JOptionPaneOutInputHandler();
        WebShopManager webShopManager = new WebShopManager(productStorage, outInputHandler);

        webShopManager.startWebshop();
    }
}
