package VendingMachine.Model;

import java.util.EventObject;

public class DisplayPanelEvent extends EventObject {
//    private float inputMoney;
//    private int itemIdInput;
    private String action;
//    private Transaction transaction;


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public DisplayPanelEvent(Object source) {
        super(source);
    }

//    public DisplayPanelEvent(Object source, String action, float inputMoney, int itemIdInput) {
//        super(source);
//        this.inputMoney = inputMoney;
//        this.itemIdInput = itemIdInput;
//        this.action = action;
//        if (action.equalsIgnoreCase("ok")){
//            transaction = new Transaction(itemIdInput,inputMoney);
//        } else {
//            transaction = new Transaction(inputMoney);
//        }
//    }

    public DisplayPanelEvent(Object source, String action) {
        super(source);
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
