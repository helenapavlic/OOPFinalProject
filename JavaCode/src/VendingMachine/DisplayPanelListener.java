package VendingMachine;

import java.util.EventListener;

public interface DisplayPanelListener extends EventListener {
    void displayPanelToolBrEventOccurred(DisplayPanelToolBarEvent displayPanelToolBarEvent);
    void numberPadEventOccurred(NumberPadEvent numberPadEvent);


}
