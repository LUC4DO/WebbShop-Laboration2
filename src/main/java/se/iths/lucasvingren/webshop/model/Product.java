package se.iths.lucasvingren.webshop.model;

public class Product {
    private final String articleNumber;
    private final String title;
    private final double price;
    private final String description;

    public Product(String articleNumber, String title, double price, String description) {
        this.articleNumber = articleNumber;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String toFileLine() {
        return articleNumber + ";" + title + ";" + price + ";" + description;
    }

    public static Product fromFileLine(String line) {
        String[] parts = line.split(";", 4);

        String articleNumber = parts[0];
        String title = parts[1];
        double price = Double.parseDouble(parts[2]);
        String description = parts[3];

        return new Product(articleNumber, title, price, description);
    }
}
