package se.iths.lucasvingren.webshop;

import javax.swing.*;

public class JOptionPaneOutInputHandler implements OutInputHandler {
    @Override
    public String prompt(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message);
            if (input != null && !input.isBlank()) {
                return input;
            }
            JOptionPane.showMessageDialog(null, "Svaret får inte vara tomt. Försök igen.");
        }
    }

    @Override
    public void info(String message) {
        //JOptionPane.showMessageDialog(null, message);
        JTextArea textArea = new JTextArea(message.toString(), 20, 30);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JOptionPane.showMessageDialog(
                null,
                new JScrollPane(textArea),
                "Produkter",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    public String menu() {
        Object[] options = {
                "Lägg till en produkt",
                "Visa alla produkter",
                "Visa en specifik produkt",
                "Avsluta programmet"
        };
        int selected = JOptionPane.showOptionDialog(
                null, "Menyval", "Input",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]
        );

        return selected < 0 ? "" : Integer.toString(selected + 1);
    }
}
