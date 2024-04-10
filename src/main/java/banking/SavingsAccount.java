package banking;

public class SavingsAccount implements Account {
    private double balance;
    private static final double INTEREST_RATE = 0.03; // Example rate

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
        if (amount <= balance) {
            balance -= amount;
        }
        return true;
    }

    @Override
    public boolean deposit(double amount) {
        balance += amount;
        return true;
    }

    // Additional methods specific to SavingsAccount if needed
}
