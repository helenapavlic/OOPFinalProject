package VendingMachine;

import java.util.EventListener;

public interface DisplayPanelListener extends EventListener {
    public void displayPanelEventOccurred(DisplayPanelEvent displayPanelEvent);
}
