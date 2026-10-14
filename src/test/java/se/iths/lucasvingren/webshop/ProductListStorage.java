package se.iths.lucasvingren.webshop;

import se.iths.lucasvingren.webshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListStorage implements ProductStorage {
    private final List<Product> productList = new ArrayList<>();

    @Override
    public void saveProduct(Product product) {
        productList.add(product);
    }

    @Override
    public List<Product> getProducts() {
        return productList;
    }

    @Override
    public Product getProduct(String articleNumber) {
        for (Product product : productList) {
            if (product.getArticleNumber().equalsIgnoreCase(articleNumber)) {
                return product;
            }
        }
        return null;
    }
}
