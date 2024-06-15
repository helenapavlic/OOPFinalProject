package VendingMachine.Model;

import java.util.EventObject;

public class CoinsPanelEvent extends EventObject {
    private static float totalInputValue = 0;
    private String coinButtonPressed;
    private float valueOfCoin;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CoinsPanelEvent(Object source, String coinButtonPressed, float valueOfCoin) {
        super(source);
        this.valueOfCoin = valueOfCoin;
        this.coinButtonPressed = coinButtonPressed;
        totalInputValue += valueOfCoin;
    }

    public String getCoinButtonPressed() {
        return coinButtonPressed;
    }

    public float getValueOfCoin() {
        return valueOfCoin;
    }

    public float getTotalInputValue() {
        return totalInputValue;
    }

    public static void setTotalInputValue(float totalInputValue) {
        CoinsPanelEvent.totalInputValue = totalInputValue;
    }
}
