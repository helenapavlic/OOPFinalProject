package VendingMachine.View;

import java.util.EventListener;

public interface AdminMenuBarListener extends EventListener {

    void menuBarEventOccurred(String menuBarActionCommandString);
}
