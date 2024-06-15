package VendingMachine.Model;

import java.util.EventListener;

public interface CoinsPanelListener extends EventListener {

    void coinsPanelEventOccurred(CoinsPanelEvent coinsPanelEvent);
}
