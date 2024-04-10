package banking;

public class MobileBanking {
    private User user;
    private boolean isEnabled;

    public MobileBanking(User user) {
        this.user = user;
        this.isEnabled = false; // Default disabled, can be enabled after KYC checks
    }

    public void enableMobileBanking() {
        // Assume KYC check is done elsewhere
        this.isEnabled = true;
    }

    public boolean isMobileBankingEnabled() {
        return isEnabled;
    }

    // Additional functionalities like fund transfer, account balance check, etc., can be added
}
