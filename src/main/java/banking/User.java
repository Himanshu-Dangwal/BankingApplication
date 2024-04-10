package banking;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String accNo;
    private String username;
    private String password; // assuming you need to store password
    private String email;
    private String phone;
    private boolean isActive;
    private double balance;
    private boolean haveTakenLoan;
    private Loan loanDetails; // Using your existing Loan class
    private Account typeOfAccount;
    private Transaction[] transactions; // Assuming Transaction is a separate class
    
    public User(String accNo, String username, String password, String email, String phone, boolean isActive,
			double balance, boolean haveTakenLoan, Loan loanDetails, Account typeOfAccount, Transaction[] transactions,
			boolean isAdmin) {
		super();
		this.accNo = accNo;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.isActive = isActive;
		this.balance = balance;
		this.haveTakenLoan = haveTakenLoan;
		this.loanDetails = loanDetails;
		this.typeOfAccount = typeOfAccount;
		this.transactions = transactions;
		this.isAdmin = isAdmin;
	}

	private boolean isAdmin;

    // Constructor for new user
    public User(String username, String email, String phone, String password) {
        this.accNo = generateUniqueAccNo();
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.isActive = true;
        this.balance = 0;
        this.haveTakenLoan = false;
        this.loanDetails = null; // Default Loan, modify as per your Loan class structure
        this.typeOfAccount = new SavingsAccount();
        this.transactions = new Transaction[0]; // Empty transaction array
        this.isAdmin = false;
    }

    // Generate a unique and random account number
    private String generateUniqueAccNo() {
        Random random = new Random();
        // Assuming account number format as "ACC" followed by 4 random digits
        int number = 1000 + random.nextInt(9000); // generates a number between 1000 and 9999
        return "ACC" + number;
    }
    
    public void takeLoan(Loan loan) {
        this.loanDetails = loan;
        this.haveTakenLoan = true;
    }

	/**
	 * @return the accNo
	 */
	public String getAccNo() {
		return accNo;
	}

	/**
	 * @param accNo the accNo to set
	 */
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the haveTakenLoan
	 */
	public boolean isHaveTakenLoan() {
		return haveTakenLoan;
	}

	/**
	 * @param haveTakenLoan the haveTakenLoan to set
	 */
	public void setHaveTakenLoan(boolean haveTakenLoan) {
		this.haveTakenLoan = haveTakenLoan;
	}

	/**
	 * @return the loanDetails
	 */
	public Loan getLoanDetails() {
		return loanDetails;
	}

	/**
	 * @param loanDetails the loanDetails to set
	 */
	public void setLoanDetails(Loan loanDetails) {
		this.loanDetails = loanDetails;
	}

	/**
	 * @return the typeOfAccount
	 */
	public String getTypeOfAccount() {
	    if (typeOfAccount instanceof SavingsAccount) {
	        return "Savings";
	    } else if (typeOfAccount instanceof SalaryAccount) {
	        return "Salary";
	    } else if (typeOfAccount instanceof CurrentAccount) {
	        return "Current";
	    } else {
	        // Handle other account types if needed
	        return "Other"; // Default value if typeOfAccount is not one of the known types
	    }
	}

	/**
	 * @param typeOfAccount the typeOfAccount to set
	 */
	public void setTypeOfAccount(Account typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

	/**
	 * @return the transactions
	 */
	public Transaction[] getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(Transaction[] transactions) {
		this.transactions = transactions;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	 public boolean performDeposit(double amount) {
	        if (typeOfAccount != null) {
	        	if(typeOfAccount.deposit(amount)) {
	        		return true;
	        	}else {
	        		return false;
	        	}
	        } else {
	            // Handle case where user doesn't have an account
	        }
			return false;
	    }

	public boolean performWithdraw(double amount) {
		if (typeOfAccount != null) {
        	if(typeOfAccount.withdraw(amount)) {
        		return true;
        	}else {
        		return false;
        	}
        } else {
            // Handle case where user doesn't have an account
        }
		return false;
    }
	
	
	public User(JSONObject jsonObj) throws JSONException {
        this.accNo = jsonObj.getString("accNo");
        this.username = jsonObj.getString("username");
        this.password = jsonObj.getString("password");
        this.email = jsonObj.getString("email");
        this.phone = jsonObj.getString("phone");
        this.isActive = jsonObj.getBoolean("isActive");
        this.balance = jsonObj.getDouble("balance");
        this.haveTakenLoan = jsonObj.getBoolean("haveTakenLoan");

        // Handling loanDetails and typeOfAccount
        if (jsonObj.has("loanDetails") && !jsonObj.isNull("loanDetails")) {
            JSONObject loanDetailsJson = jsonObj.getJSONObject("loanDetails");
            this.loanDetails = Loan.createLoanFromJson(loanDetailsJson);
        } else {
            this.loanDetails = null;
        }


        // Handling typeOfAccount - this depends on how your Account hierarchy is set up
        String accountType = jsonObj.getString("typeOfAccount");
        switch(accountType) {
            case "Savings":
                this.typeOfAccount = new SavingsAccount();
                break;
            case "Salary":
                this.typeOfAccount = new SalaryAccount();
                break;
            case "Current":
                this.typeOfAccount = new CurrentAccount();
                break;
            default:
                this.typeOfAccount = null; // or some default account type
        }

        // Handling transactions - assuming Transaction class has a constructor that takes a JSONObject
        if (jsonObj.has("transactions")) {
            JSONArray transactionsJson = jsonObj.getJSONArray("transactions");
            this.transactions = new Transaction[transactionsJson.length()];
            for (int i = 0; i < transactionsJson.length(); i++) {
                this.transactions[i] = new Transaction(transactionsJson.getJSONObject(i));
            }
        } else {
            this.transactions = new Transaction[0];
        }

        this.isAdmin = jsonObj.getBoolean("isAdmin");
    }

}
