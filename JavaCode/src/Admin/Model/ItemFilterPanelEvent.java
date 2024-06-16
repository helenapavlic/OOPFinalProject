package Admin.Model;

import java.util.Arrays;

public class ItemFilterPanelEvent extends java.util.EventObject {
    private String[] filters;

    public ItemFilterPanelEvent(Object source, String[] filters) {
        super(source);
        this.filters = filters;
    }

    public String[] getFilters() {
        return filters;
    }
}
