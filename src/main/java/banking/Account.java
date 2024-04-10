package banking;

public interface Account {
    double getInterestRate();
    double getBalance();
    boolean withdraw(double amount);
    boolean deposit(double amount);
}
