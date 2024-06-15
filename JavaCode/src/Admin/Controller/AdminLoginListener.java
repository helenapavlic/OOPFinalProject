package Admin.Controller;


import Admin.Model.AdminLoginEvent;

public interface AdminLoginListener {
    void adminLoginEventOccurred(AdminLoginEvent event);
}
