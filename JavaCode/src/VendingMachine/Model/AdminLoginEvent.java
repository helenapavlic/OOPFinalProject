package VendingMachine.Model;

public class AdminLoginEvent {
    private boolean loginSuccessful;

    public AdminLoginEvent(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}
