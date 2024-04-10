package banking;

public class Admin {
    private String adminId;
    private String name;

    public Admin(String adminId, String name) {
        this.adminId = adminId;
        this.name = name;
    }

    // Methods for admin activities like audit, reporting, etc.
    public void auditAccount(Account account) {
        // Implement auditing functionality
    }

    // Getters and setters
    // ...
}
