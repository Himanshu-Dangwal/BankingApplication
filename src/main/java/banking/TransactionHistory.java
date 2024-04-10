package banking;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions); // Return a copy to maintain encapsulation
    }

    // Method to print transaction history or other relevant methods
    public void printTransactionHistory() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
