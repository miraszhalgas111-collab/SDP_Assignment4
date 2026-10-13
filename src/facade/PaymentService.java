
package facade;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {

    private final Map<Integer, Integer> payments = new HashMap<>();

    public boolean processPayment(int orderId, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be positive");
        }

        if (payments.containsKey(orderId)) {
            throw new IllegalStateException(
                    "Order has already been paid");
        }

        payments.put(orderId, amount);

        System.out.println("Processing payment for order #" + orderId);
        System.out.println("Amount: " + amount + " KZT");
        System.out.println("Payment successful!");

        return true;
    }

    public void refundPayment(int orderId, int amount) {
        Integer paidAmount = payments.get(orderId);

        if (paidAmount == null) {
            throw new IllegalStateException(
                    "No payment found for order #" + orderId);
        }

        if (amount != paidAmount) {
            throw new IllegalArgumentException(
                    "Refund amount does not match payment");
        }

        payments.remove(orderId);

        System.out.println("Refunding " + amount
                + " KZT for order #" + orderId);
        System.out.println("Refund completed!");
    }
}
