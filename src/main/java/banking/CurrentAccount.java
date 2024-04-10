package banking;

public class CurrentAccount implements Account {
    private double balance;
    private static final double INTEREST_RATE = 0.01; // Lower rate for current accounts

    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public boolean withdraw(double amount) {
        // Assuming no minimum balance required for salary accounts
        balance -= amount;
        return true;
    }

    @Override
    public boolean deposit(double amount) {
        balance += amount;
        return true;
    }

    // Additional methods specific to CurrentAccount if needed
}
