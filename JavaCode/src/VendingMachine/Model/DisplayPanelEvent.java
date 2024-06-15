package VendingMachine.Model;

import java.util.EventObject;

public class DisplayPanelEvent extends EventObject {
    private String action;


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public DisplayPanelEvent(Object source) {
        super(source);
    }

    public DisplayPanelEvent(Object source, String action) {
        super(source);
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
