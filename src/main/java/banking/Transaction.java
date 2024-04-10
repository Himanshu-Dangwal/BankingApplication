package banking;

import java.util.Date;

import org.json.JSONObject;

public class Transaction {
    private String transactionId;
    private String date;
    private double amount;
    private String type; // e.g., DEPOSIT, WITHDRAW

    public Transaction(String transactionId, String date, double amount, String type) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }



    /**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}



	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}



	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}



	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}



	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}



	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}



	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	 public Transaction(JSONObject jsonObj) {
	        this.transactionId = jsonObj.optString("transactionId", "unknown");
	        this.date = jsonObj.optString("date", "unknown");
	        this.amount = jsonObj.optDouble("amount", 0.0);
	        this.type = jsonObj.optString("type", "unknown");
	    }


	@Override
    public String toString() {
        return "Transaction{" +
               "transactionId='" + transactionId + '\'' +
               ", date=" + date +
               ", amount=" + amount +
               ", type='" + type + '\'' +
               '}';
    }
}
