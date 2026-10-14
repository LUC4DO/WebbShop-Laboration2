package se.iths.lucasvingren.webshop;

import se.iths.lucasvingren.webshop.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ProductFileStorage implements ProductStorage {

    private final Path file = Path.of("products.txt");

    @Override
    public void saveProduct(Product product) {
        String line = product.toFileLine() + System.lineSeparator();

        try {
            Files.writeString(
                    file,
                    line,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            IO.println("Kan inte spara produkten till " + file + e.getMessage());
        }
    }

    @Override
    public List<Product> getProducts() {
        try {
            List<Product> products = new ArrayList<>();

            for (String line : Files.readAllLines(file)) {
                if (!line.isBlank()) {
                    products.add(Product.fromFileLine(line));
                }
            }
            return products;
        } catch (IOException e) {
            IO.println("Kan inte läsa produkterna från " + file + e.getMessage());
        }
        return List.of();
    }

    @Override
    public Product getProduct(String articleNumber) {
        for (Product product : getProducts()) {
            if (product.getArticleNumber().equalsIgnoreCase(articleNumber)) {
                return product;
            }
        }
        return null;
    }
}
