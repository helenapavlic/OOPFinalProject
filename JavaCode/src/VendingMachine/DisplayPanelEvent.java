package VendingMachine;

import java.util.EventObject;

public class DisplayPanelEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public DisplayPanelEvent(Object source) {
        super(source);
    }
}
