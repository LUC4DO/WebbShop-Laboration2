package se.iths.lucasvingren.webshop.model;

public abstract class Discount {

    protected String description;

    public Discount(String description) {
        this.description = description;
    }

    public abstract double calculatePrice(double originalPrice);
}
