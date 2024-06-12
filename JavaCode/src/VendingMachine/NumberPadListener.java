package VendingMachine;

import java.util.EventListener;

public interface NumberPadListener extends EventListener{
    void numberPadButtonPressed(String buttonAction);

}
