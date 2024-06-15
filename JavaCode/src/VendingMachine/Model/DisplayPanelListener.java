package VendingMachine.Model;

import java.util.EventListener;

public interface DisplayPanelListener extends EventListener {
    void displayPanelEventOccurred(DisplayPanelEvent displayPanelEvent);
}
