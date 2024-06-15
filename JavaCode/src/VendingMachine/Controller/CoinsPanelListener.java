package VendingMachine.Controller;

import VendingMachine.Model.CoinsPanelEvent;

import java.util.EventListener;

public interface CoinsPanelListener extends EventListener {

    void coinsPanelEventOccurred(CoinsPanelEvent coinsPanelEvent);
}
