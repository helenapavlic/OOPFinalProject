package VendingMachine.Controller;

import VendingMachine.Model.DisplayPanelEvent;

import java.util.EventListener;

public interface DisplayPanelListener extends EventListener {
    void displayPanelEventOccurred(DisplayPanelEvent displayPanelEvent);
}
