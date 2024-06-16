package Admin.Controller;

import Admin.Model.ItemFilterPanelEvent;
import Admin.Model.TransactionFilterPanelEvent;

import java.util.EventListener;

public interface FilterPanelListener extends EventListener {
    void transactionFilterPanelEventOccurred(TransactionFilterPanelEvent transactionFilterPanelEvent);
    void itemFilterPanelEventOccurred(ItemFilterPanelEvent itemFilterPanelEvent);
}
