package banking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.util.Random;

import org.json.*;

public class TransferServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User sender = (User) request.getSession().getAttribute("user");
        String recipientAccNo = request.getParameter("recipientAccNo");
        double amount = Double.parseDouble(request.getParameter("amount"));

        String realPathToUsersFile = getServletContext().getRealPath("/users.json");
        File file = new File(realPathToUsersFile);

        try {
            String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            JSONArray users = new JSONArray(content);

            JSONObject senderJson = null;
            JSONObject receiverJson = null;

            // Find sender in JSON array
            for (int i = 0; i < users.length(); i++) {
                JSONObject tempUserJson = users.getJSONObject(i);
                if (tempUserJson.getString("username").equals(sender.getUsername())) {
                    senderJson = tempUserJson;
                }
                if (tempUserJson.getString("accNo").equals(recipientAccNo)) {
                    receiverJson = tempUserJson;
                }

                if (senderJson != null && receiverJson != null) {
                    break;
                }
            }

            if (senderJson != null && receiverJson != null) {
                User receiver = new User(receiverJson); // Assuming User class can be initialized with JSONObject

                performTransferOperation(senderJson, receiverJson, amount, sender, receiver);

                Files.write(file.toPath(), users.toString().getBytes(StandardCharsets.UTF_8));
                request.getSession().setAttribute("user", senderJson); // Update sender's session
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("failure.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("failure.jsp");
        }
    }

    private synchronized void performTransferOperation(JSONObject senderJson, JSONObject receiverJson, double amount, User sender, User receiver) throws Exception {
        // Check if sender has enough balance
        double senderBalance = senderJson.getDouble("balance");
        if (senderBalance < amount) {
            throw new Exception("Insufficient funds for transfer");
        }

        // Update sender's balance
        senderJson.put("balance", senderBalance - amount);
        addTransaction(senderJson, amount, "transfer out");

        // Update receiver's balance
        double receiverBalance = receiverJson.getDouble("balance");
        receiverJson.put("balance", receiverBalance + amount);
        addTransaction(receiverJson, amount, "transfer in");
        
        // Updating User objects if necessary
        sender.setBalance(senderBalance - amount);
        receiver.setBalance(receiverBalance + amount);
    }

    private void addTransaction(JSONObject userJson, double amount, String type) throws JSONException {
        JSONArray transactions = userJson.getJSONArray("transactions");
        JSONObject newTransaction = new JSONObject();
        newTransaction.put("transactionId", generateTransactionId());
        newTransaction.put("date", new Date().toString()); // Use a proper date format
        newTransaction.put("amount", amount);
        newTransaction.put("type", type);
        transactions.put(newTransaction);
    }

    private String generateTransactionId() {
        // Generate a unique transaction ID (adjust this method as necessary)
        return "T" + new Random().nextInt(100000);
    }

}
