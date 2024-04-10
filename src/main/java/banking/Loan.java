package banking;

import org.json.JSONObject;

public abstract class Loan {
    protected double principalAmount;
    protected double interestRate;
    protected int loanPeriod;

    public Loan(double principalAmount, double interestRate, int loanPeriod) {
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.loanPeriod = loanPeriod;
    }

    
    public static Loan createLoanFromJson(JSONObject jsonObj) {
        String loanType = jsonObj.optString("type", "unknown");
        double principalAmount = jsonObj.optDouble("principalAmount", 0.0);
        double interestRate = jsonObj.optDouble("interestRate", 0.0);
        int loanPeriod = jsonObj.optInt("tenure", 0);

        switch(loanType) {
            case "EducationLoan":
                return new EducationLoan(principalAmount, interestRate, loanPeriod);
            // case for other types of loans...
            default:
                return null; // or some default loan type
        }
    }
    public abstract double calculateEMI();

    // Additional common methods related to loans
}
