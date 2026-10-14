package se.iths.lucasvingren.webshop;

import se.iths.lucasvingren.webshop.model.Product;

import java.util.List;

// Sparar och hämtar produkter
public interface ProductStorage {

    void saveProduct(Product product);              // Sparar den angivna produkten

    List<Product> getProducts();                    // Returnerar alla sparade produkter

    Product getProduct(String articleNumber);       // Hämtar en produkt utifrån artikelnummer, annars returnera null
}
