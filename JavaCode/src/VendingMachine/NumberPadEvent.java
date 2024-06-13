package VendingMachine;

import java.util.EventObject;

public class NumberPadEvent extends EventObject {

    private String action;
    private boolean okButtonPressed;
    private boolean clearButtonPressed;
    private boolean isIntNumPressed;

    private float inputMoney;
    private int inputItemID;


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public NumberPadEvent(Object source) {
        super(source);
    }

    public NumberPadEvent(Object source, String action, boolean okButtonPressed, boolean clearButtonPressed, boolean isIntNumPressed) {
        super(source);
        this.action = action;
        this.okButtonPressed = okButtonPressed;
        this.clearButtonPressed = clearButtonPressed;
        this.isIntNumPressed = isIntNumPressed;
    }

    public String getAction() {
        return action;
    }

    public boolean isOkButtonPressed() {
        return okButtonPressed;
    }

    public boolean isClearButtonPressed() {
        return clearButtonPressed;
    }

    public boolean isIntNumPressed() {
        return isIntNumPressed;
    }
}
