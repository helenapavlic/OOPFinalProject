package Admin.Model;

import java.util.EventObject;

public class TransactionFilterPanelEvent extends EventObject {
    private String[] filters;

    public TransactionFilterPanelEvent(Object source, String[] filters) {
        super(source);
        this.filters = filters;
    }

    public String[] getFilters() {
        return filters;
    }
}
