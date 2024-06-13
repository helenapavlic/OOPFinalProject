package VendingMachine;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ItemsPanel itemsPanel;
    private CoinsPanel coinsPanel;
    private DisplayPanel displayPanel;

    public MainFrame() {
        super("Vending machine");
        initComps();
        layoutComps();
        activateFrame();
    }

    private void activateFrame() {
        coinsPanel.setCoinsPanelListener(new CoinsPanelListener() {
            @Override
            public void coinsPanelEventOccurred(CoinsPanelEvent coinsPanelEvent) {
                String action = coinsPanelEvent.getCoinButtonPressed();
                float valueInput = coinsPanelEvent.getValueOfCoin();
                float totalInput = coinsPanelEvent.getTotalInputValue();
//                test!
                System.out.println(action);
                System.out.println(valueInput);
                System.out.println(totalInput);
                displayPanel.printAddedMoney(totalInput);
            }
        });

        displayPanel.setDisplayPanelListener(new DisplayPanelListener() {
            @Override
            public void displayPanelToolBrEventOccurred(DisplayPanelToolBarEvent displayPanelToolBarEvent) {

            }

            @Override
            public void numberPadEventOccurred(NumberPadEvent numberPadEvent) {
                boolean isNumPressed = numberPadEvent.isIntNumPressed();
                boolean isOkButtonPressed = numberPadEvent.isOkButtonPressed();
                boolean isDelButtonPressed = numberPadEvent.isOkButtonPressed();

                if (isNumPressed) {
                    String textToPrint = numberPadEvent.getAction();
                    System.out.println(textToPrint);
                    displayPanel.printInputNumber(textToPrint);
                    displayPanel.activateButtons();
                } else if (isOkButtonPressed) {
                    int userInput = displayPanel.getUserInput();
//                    System.out.println(userInput);
                    Item item = Item.getItemById(userInput);
                    if (item == null) {
                        System.out.println("item not found");
                    } else {
                        Transaction transaction = new Transaction(item, displayPanel.getInputMoney());
                        System.out.println(transaction);
                        itemsPanel.updatePanel(item);
                        coinsPanel.resetCoinsCounter();
                    }


//                    todo: akcija za todo -> transakcij, spremanje i ispis
//                    Transaction transaction = new Transaction()
//                    pogledati input i ubačen novac i spremiti to u var
//                    pronaći item po idu -> spremiti u item
//                    napraviti transakciju
//                    spremiti transakciju u csv
//                    pop up prozor sa change i statusom transakcije
//                    resetirati display panel

//                    nakok klika na ok mora se resetirat brojac unosa
                    displayPanel.reset();
                } else {
                    displayPanel.deleteLastInput();
                }
            }
        });


    }

    private void layoutComps() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;

        // Add the DisplayPanel to the main frame
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 0.6;
        add(displayPanel, gbc);

        // Add the CoinsPanel to the main frame
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.8;
        gbc.weighty = 0.4;
        add(coinsPanel, gbc);

        // Add the DrinksAndSnacksPanel to the main frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 1.2;
        gbc.weighty = 1.0;
        add(itemsPanel, gbc);

        // Set frame size, location, and make it visible
//        setSize(800, 800);
//        setLocationRelativeTo(null);
//        setVisible(true);

    }

    private void initComps() {
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        itemsPanel = new ItemsPanel();
        coinsPanel = new CoinsPanel();
        displayPanel = new DisplayPanel();

    }
}
