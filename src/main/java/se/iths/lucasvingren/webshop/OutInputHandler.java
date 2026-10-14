package se.iths.lucasvingren.webshop;

public interface OutInputHandler {
    String prompt(String message);

    void info(String message);

    String menu();
}
