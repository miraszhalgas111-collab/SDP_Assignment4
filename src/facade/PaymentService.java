
package facade;

public class PaymentService {

    public boolean processPayment(int orderId, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be positive");
        }

        System.out.println("Processing payment for order #" + orderId);
        System.out.println("Amount: " + amount + " KZT");
        System.out.println("Payment successful!");

        return true;
    }

    public void refundPayment(int orderId, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Refund amount must be positive");
        }

        System.out.println("Refunding " + amount
                + " KZT for order #" + orderId);
        System.out.println("Refund completed!");
    }
}
