package banking;

public class PersonalLoan extends Loan {
    public PersonalLoan(double principalAmount, double interestRate, int loanPeriod) {
        super(principalAmount, interestRate, loanPeriod);
    }

    @Override
    public double calculateEMI() {
        double monthlyInterestRate = interestRate / 12 / 100;
        double emi = (principalAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanPeriod))
                     / (Math.pow(1 + monthlyInterestRate, loanPeriod) - 1);
        return emi;
    }

    // Additional methods specific to PersonalLoan
}
