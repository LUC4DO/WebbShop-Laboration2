package se.iths.lucasvingren.webshop;

public class ConsoleOutInputHandler implements OutInputHandler {
    @Override
    public String prompt(String message) {
        while (true) {      //tillsynes oändlig loop men den avbryts så fort metoden har returnerat ett värde.
            try {
                String input = IO.readln(message);
                if (input.isBlank()) {
                    throw new IllegalArgumentException("Blank Sträng, avbryter");  //Använder try catch throw för att skapa ett problem vid blank input.
                }
                return input;                                      //returnerar endast om ingen exception har skapats (då exceptions stoppar try koden)
            } catch (IllegalArgumentException e) {
                IO.println(e.getMessage());
                IO.println("Ej giltigt svar, försök igen!");        //vid tom/blank Sträng så printar catch felmeddelande, därefter fortsätter Loopen.
            }
        }
    }

    @Override
    public void info(String message) {
        IO.println(message);
    }

    @Override
    public String menu() {
        String menyChoice;
        return menyChoice = prompt("Mata in 1: Lägg till produkt\n"
                + "Mata in 2: Lista alla produkter\n"
                + "Mata in 3: Visa information om en produkt\n"
                + "Mata in 4: Avsluta applikationen\n");
    }
}
